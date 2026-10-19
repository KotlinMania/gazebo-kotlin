// port-lint: source types.rs
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

/**
 * A trait witnessing that two types are equal.
 */
interface TEq<T> {
    /** Convert between two equal types. */
    fun teq(): T

    /** Convert between references to two equal types. */
    fun teqRef(): T

    /** Convert between mutable references to two equal types. */
    fun teqMut(): T
}

/**
 * Default wrapper providing [TEq] witness for any value of type [T].
 */
open class TEqValue<T>(
    val value: T,
) : TEq<T> {
    override fun teq(): T = value

    override fun teqRef(): T = value

    override fun teqMut(): T = value
}

/** Extension functions providing [TEq] conversions directly on any instance. */
fun <T> T.teq(): T = this

fun <T> T.teqRef(): T = this

fun <T> T.teqMut(): T = this
