// port-lint: source ext/option.rs
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
 * Extension traits on Option where it holds any value or reference.
 */
interface OptionExt<T> {
    /**
     * Like map, but returning a Result.
     */
    fun <U> tryMap(f: (T) -> Result<U>): Result<U?>
}

/**
 * Wrapper for nullable values representing Option to provide [OptionExt].
 */
class OptionValue<T>(
    val value: T?,
) : OptionExt<T> {
    override fun <U> tryMap(f: (T) -> Result<U>): Result<U?> =
        if (value == null) Result.success(null) else f(value)
}

fun <T> T?.asOption(): OptionValue<T> = OptionValue(this)
