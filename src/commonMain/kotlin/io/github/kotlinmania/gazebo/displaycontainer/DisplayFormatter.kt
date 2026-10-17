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

import io.github.kotlinmania.either.Either

internal const val INDENT: String = "  "

/**
 * Formatter state for container display, supporting normal and alternate formatting.
 */
class DisplayFormatter internal constructor(
    val alternate: Boolean,
    private val out: StringBuilder,
) {
    constructor(alternate: Boolean = false) : this(alternate, StringBuilder())

    fun writeStr(s: String): DisplayFormatter {
        out.append(s)
        return this
    }

    override fun toString(): String = out.toString()
}

/**
 * Interface for items that support both normal and alternate [DisplayFormatter] formatting.
 */
interface Displayable {
    fun fmt(f: DisplayFormatter)
}

internal fun formatValue(v: Any?, alternate: Boolean): String {
    if (v is Displayable) {
        val sub = DisplayFormatter(alternate = alternate)
        v.fmt(sub)
        return sub.toString()
    }
    if (v is Either<*, *>) {
        return when (v) {
            is Either.Left<*> -> formatValue(v.value, alternate)
            is Either.Right<*> -> formatValue(v.value, alternate)
        }
    }
    return v.toString()
}

/**
 * Used to indent a displayed item for alternate display. This helps us pretty-print deep data structures.
 */
internal fun subwriter(indent: String, f: DisplayFormatter, v: Any?) {
    if (f.alternate) {
        val formatted = formatValue(v, alternate = true)
        if (indent.isEmpty()) {
            f.writeStr(formatted)
        } else {
            val lines = formatted.split('\n')
            for ((idx, line) in lines.withIndex()) {
                if (idx > 0) {
                    f.writeStr("\n")
                }
                if (line.isNotEmpty()) {
                    f.writeStr(indent)
                    f.writeStr(line)
                }
            }
        }
    } else {
        f.writeStr(formatValue(v, alternate = false))
    }
}
