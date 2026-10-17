// port-lint: source dupe/src/option.rs
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
 * Extension operations on nullable values holding a [Dupe] value.
 */
interface OptionDupedExt<out T : Dupe> {
    fun duped(): T?
}

/**
 * Like `cloned`, but with a [Dupe] constraint.
 */
fun <T : Dupe> T?.duped(): T? = this?.dupe()
