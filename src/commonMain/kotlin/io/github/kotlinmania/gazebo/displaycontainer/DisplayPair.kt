package io.github.kotlinmania.gazebo.displaycontainer

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
 * Display a pair of elements with a separator in the middle.
 */
fun <K, V> displayPair(key: K, separator: String, value: V): Displayable =
    DisplayPair(key, separator, value)

internal class DisplayPair<K, V>(
    val key: K,
    val separator: String,
    val value: V,
) : Displayable {
    override fun fmt(f: DisplayFormatter) {
        f.writeStr(formatValue(key, f.alternate))
        f.writeStr(separator)
        f.writeStr(formatValue(value, f.alternate))
    }

    override fun toString(): String {
        val f = DisplayFormatter(alternate = false)
        fmt(f)
        return f.toString()
    }
}
