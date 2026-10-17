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
 * Iterator length for display.
 */
internal enum class Len {
    Zero,
    One,
    Many,
}

/**
 * The low-level helper for displaying containers.
 */
internal class ContainerDisplayHelper private constructor(
    private val f: DisplayFormatter,
    private val separator: String,
    private val outer: String,
    private val indent: String,
) {
    private var seenItems: Int = 0

    companion object {
        fun beginInner(f: DisplayFormatter, prefix: String, numItems: Len): ContainerDisplayHelper {
            val (separator, outer, indent) =
                when {
                    !f.alternate -> Triple(", ", "", "")
                    numItems == Len.Zero -> Triple("", "", "")
                    numItems == Len.One -> Triple("", " ", "")
                    else -> Triple(",\n", "\n", INDENT)
                }
            f.writeStr(prefix)
            f.writeStr(outer)
            return ContainerDisplayHelper(f, separator, outer, indent)
        }
    }

    fun item(v: Any?) {
        if (seenItems != 0) {
            f.writeStr(separator)
        }
        seenItems += 1
        subwriter(indent, f, v)
    }

    fun end(suffix: String) {
        f.writeStr(outer)
        f.writeStr(suffix)
    }
}
