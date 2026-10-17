// port-lint: source dupe/src/iter.rs
package io.github.kotlinmania.gazebo.dupe

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
 * Like `duped()`, but only works for types that implement [Dupe].
 */
interface IterDupedExt<out T> {
    fun duped(): Iterator<T>
}

fun <T : Dupe> Iterator<T>.duped(): Iterator<T> = asSequence().map { it.dupe() }.iterator()

fun <T : Dupe> Sequence<T>.duped(): Sequence<T> = map { it.dupe() }

fun <T : Dupe> Iterable<T>.duped(): List<T> = map { it.dupe() }
