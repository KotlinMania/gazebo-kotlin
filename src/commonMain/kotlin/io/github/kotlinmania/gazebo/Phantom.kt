// port-lint: source phantom.rs
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

import io.github.kotlinmania.gazebo.dupe.Dupe

/**
 * A type like PhantomData, but where the contained [T] is invariant in types.
 */
class PhantomDataInvariant<T> : Dupe, Comparable<PhantomDataInvariant<T>> {

    fun fmt(): String = "PhantomDataInvariant"

    fun clone(): PhantomDataInvariant<T> = this

    fun hash(): Int = 0

    fun cmp(other: PhantomDataInvariant<T>): Int = 0

    fun eq(other: PhantomDataInvariant<T>): Boolean = true

    fun partialCmp(other: PhantomDataInvariant<T>): Int? = 0

    override fun compareTo(other: PhantomDataInvariant<T>): Int = 0

    override fun equals(other: Any?): Boolean = other is PhantomDataInvariant<*>

    override fun hashCode(): Int = 0

    override fun toString(): String = fmt()

    companion object {
        fun <T> new(): PhantomDataInvariant<T> = PhantomDataInvariant()

        fun <T> default(): PhantomDataInvariant<T> = PhantomDataInvariant()
    }
}
