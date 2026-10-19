// port-lint: source cell.rs
package io.github.kotlinmania.gazebo

/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * Copyright (c) 2025 Sydney Renee, The Solace Project
 *
 * This source code is dual-licensed under either the MIT license found in the
 * LICENSE-MIT file in the root directory of this source tree or the Apache
 * License, Version 2.0 found in the LICENSE-APACHE file in the root directory
 * of this source tree. You may select, at your option, one of the
 * above-listed licenses.
 */

sealed class ARefImpl<T> {
    data class Ptr<T>(val ptr: T) : ARefImpl<T>()

    data class Ref<T>(val ref: T) : ARefImpl<T>()
}

/**
 * A reference that might not actually be borrowed.
 * Either a direct reference, or a cell reference, exposing access methods.
 */
class ARef<T> internal constructor(val inner: ARefImpl<T>) : Comparable<ARef<T>> {

    fun deref(): T =
        when (inner) {
            is ARefImpl.Ptr -> inner.ptr
            is ARefImpl.Ref -> inner.ref
        }

    fun clone(): ARef<T> =
        when (inner) {
            is ARefImpl.Ptr -> ARef(ARefImpl.Ptr(inner.ptr))
            is ARefImpl.Ref -> ARef(ARefImpl.Ref(inner.ref))
        }

    fun <U> map(f: (T) -> U): ARef<U> =
        when (inner) {
            is ARefImpl.Ptr -> ARef(ARefImpl.Ptr(f(inner.ptr)))
            is ARefImpl.Ref -> ARef(ARefImpl.Ref(f(inner.ref)))
        }

    fun <U, V> mapSplit(f: (T) -> Pair<U, V>): Pair<ARef<U>, ARef<V>> =
        when (inner) {
            is ARefImpl.Ptr -> {
                val (u, v) = f(inner.ptr)
                Pair(ARef(ARefImpl.Ptr(u)), ARef(ARefImpl.Ptr(v)))
            }
            is ARefImpl.Ref -> {
                val (u, v) = f(inner.ref)
                Pair(ARef(ARefImpl.Ref(u)), ARef(ARefImpl.Ref(v)))
            }
        }

    fun <U> filterMap(f: (T) -> U?): Result<ARef<U>> =
        when (inner) {
            is ARefImpl.Ptr -> {
                val u = f(inner.ptr)
                if (u != null) Result.success(ARef(ARefImpl.Ptr(u))) else Result.failure(NoSuchElementException("Value filtered out"))
            }
            is ARefImpl.Ref -> {
                val u = f(inner.ref)
                if (u != null) Result.success(ARef(ARefImpl.Ref(u))) else Result.failure(NoSuchElementException("Value filtered out"))
            }
        }

    fun fmt(): String = deref().toString()

    fun hash(): Int = deref().hashCode()

    fun eq(other: ARef<T>): Boolean = deref() == other.deref()

    fun partialCmp(other: ARef<T>): Int? = cmp(other)

    fun cmp(other: ARef<T>): Int {
        val a = deref()
        val b = other.deref()
        return if (a is Comparable<*> && b is Comparable<*>) {
            compareValues(a, b)
        } else {
            0
        }
    }

    override fun compareTo(other: ARef<T>): Int = cmp(other)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ARef<*>) return false
        return deref() == other.deref()
    }

    override fun hashCode(): Int = hash()

    override fun toString(): String = fmt()

    companion object {
        fun <T> newPtr(x: T): ARef<T> = ARef(ARefImpl.Ptr(x))

        fun <T> newRef(x: T): ARef<T> = ARef(ARefImpl.Ref(x))

        fun <T> clone(orig: ARef<T>): ARef<T> = orig.clone()

        fun <T, U> map(orig: ARef<T>, f: (T) -> U): ARef<U> = orig.map(f)

        fun <T, U, V> mapSplit(orig: ARef<T>, f: (T) -> Pair<U, V>): Pair<ARef<U>, ARef<V>> = orig.mapSplit(f)

        fun <T, U> filterMap(orig: ARef<T>, f: (T) -> U?): Result<ARef<U>> = orig.filterMap(f)
    }
}

/**
 * Obtain an [ARef] from either a normal pointer or a reference container.
 */
interface AsARef<T> {
    fun asAref(): ARef<T>

    fun tryAsAref(): Result<ARef<T>>

    fun asRefCell(): T?
}

/**
 * Simple container cell representing a mutable reference with borrowing semantics.
 */
class RefCell<T>(var value: T) : AsARef<T> {
    private var isBorrowedMutably: Boolean = false

    fun borrow(): T = value

    fun tryBorrow(): Result<T> =
        if (isBorrowedMutably) Result.failure(IllegalStateException("Already mutably borrowed"))
        else Result.success(value)

    fun tryBorrowMut(): Result<T> =
        if (isBorrowedMutably) Result.failure(IllegalStateException("Already borrowed"))
        else {
            isBorrowedMutably = true
            Result.success(value)
        }

    fun releaseMut() {
        isBorrowedMutably = false
    }

    override fun asAref(): ARef<T> = ARef.newRef(borrow())

    override fun tryAsAref(): Result<ARef<T>> =
        tryBorrow().map { ARef.newRef(it) }

    override fun asRefCell(): T = value
}

class DirectAsARef<T>(val value: T) : AsARef<T> {
    override fun asAref(): ARef<T> = ARef.newPtr(value)

    override fun tryAsAref(): Result<ARef<T>> = Result.success(ARef.newPtr(value))

    override fun asRefCell(): T? = null
}
