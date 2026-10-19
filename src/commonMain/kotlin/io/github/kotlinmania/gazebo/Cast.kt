// port-lint: source cast.rs
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
 * Cast between types with no conversion.
 */
fun <T> ptrToUsize(x: T): ULong =
    x?.let { it.hashCode().toLong().toULong() } ?: 0uL

fun <T> usizeToPtr(x: ULong): T? = null

inline fun <reified To> ptr(x: Any?): To = x as To

inline fun <reified To> ptrMut(x: Any?): To = x as To

fun <T> ptrLifetime(x: T): T = x

inline fun <reified B> transmuteUnchecked(x: Any?): B = x as B
