// port-lint: source ext/iter.rs
package io.github.kotlinmania.gazebo.ext

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

/**
 * Extension traits on [Iterator].
 */
interface IterExt<T> {
    fun tryAny(any: (T) -> Result<Boolean>): Result<Boolean>

    fun tryAll(any: (T) -> Result<Boolean>): Result<Boolean>

    fun <O> tryEqBy(other: Iterable<O>, eq: (T, O) -> Result<Boolean>): Result<Boolean>

    fun <O> tryCmpBy(other: Iterable<O>, cmp: (T, O) -> Result<Int>): Result<Int>

    fun intoSingleton(): T?
}

/**
 * An iterator that yields the owned items of the inner iterator.
 */
class Owned<T>(val inner: Iterator<T>) : Iterator<T> {
    override fun hasNext(): Boolean = inner.hasNext()

    override fun next(): T {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        return inner.next()
    }
}

interface IterOwned<T> {
    fun owned(): Owned<T>
}

fun <T> Iterator<T>.tryAny(any: (T) -> Result<Boolean>): Result<Boolean> {
    while (hasNext()) {
        val res = any(next())
        if (res.isFailure) return res
        if (res.getOrThrow()) return Result.success(true)
    }
    return Result.success(false)
}

fun <T> Iterable<T>.tryAny(any: (T) -> Result<Boolean>): Result<Boolean> =
    iterator().tryAny(any)

fun <T> Iterator<T>.tryAll(any: (T) -> Result<Boolean>): Result<Boolean> {
    while (hasNext()) {
        val res = any(next())
        if (res.isFailure) return res
        if (!res.getOrThrow()) return Result.success(false)
    }
    return Result.success(true)
}

fun <T> Iterable<T>.tryAll(any: (T) -> Result<Boolean>): Result<Boolean> =
    iterator().tryAll(any)

fun <T, O> Iterator<T>.tryEqBy(other: Iterable<O>, eq: (T, O) -> Result<Boolean>): Result<Boolean> {
    val oIter = other.iterator()
    while (hasNext()) {
        if (!oIter.hasNext()) return Result.success(false)
        val x = next()
        val y = oIter.next()
        val res = eq(x, y)
        if (res.isFailure) return res
        if (!res.getOrThrow()) return Result.success(false)
    }
    return Result.success(!oIter.hasNext())
}

fun <T, O> Iterable<T>.tryEqBy(other: Iterable<O>, eq: (T, O) -> Result<Boolean>): Result<Boolean> =
    iterator().tryEqBy(other, eq)

fun <T, O> Iterator<T>.tryCmpBy(other: Iterable<O>, cmp: (T, O) -> Result<Int>): Result<Int> {
    val oIter = other.iterator()
    while (hasNext()) {
        if (!oIter.hasNext()) return Result.success(1)
        val x = next()
        val y = oIter.next()
        val res = cmp(x, y)
        if (res.isFailure) return res
        val c = res.getOrThrow()
        if (c != 0) return Result.success(c)
    }
    return Result.success(if (oIter.hasNext()) -1 else 0)
}

fun <T, O> Iterable<T>.tryCmpBy(other: Iterable<O>, cmp: (T, O) -> Result<Int>): Result<Int> =
    iterator().tryCmpBy(other, cmp)

fun <A, B> Iterator<Result<Pair<A, B>>>.tryUnzip(): Result<Pair<List<A>, List<B>>> {
    val listA = mutableListOf<A>()
    val listB = mutableListOf<B>()
    while (hasNext()) {
        val item = next()
        if (item.isFailure) {
            val exception = item.exceptionOrNull() ?: Exception("Unzip failed")
            return Result.failure(exception)
        }
        val (a, b) = item.getOrThrow()
        listA.add(a)
        listB.add(b)
    }
    return Result.success(Pair(listA, listB))
}

fun <A, B> Iterable<Result<Pair<A, B>>>.tryUnzip(): Result<Pair<List<A>, List<B>>> =
    iterator().tryUnzip()

fun <T> Iterator<T>.intoSingleton(): T? {
    if (!hasNext()) return null
    val first = next()
    if (hasNext()) return null
    return first
}

fun <T> Iterable<T>.intoSingleton(): T? =
    iterator().intoSingleton()

fun <T> Iterator<T>.owned(): Owned<T> =
    Owned(this)

fun <T> Iterable<T>.owned(): Owned<T> =
    Owned(iterator())
