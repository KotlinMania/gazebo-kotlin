// port-lint: source hash.rs
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
 * A type [T], but with the hash computed in advance, so hashing is O(1).
 */
data class Hashed<T>(
    val hash: ULong,
    val value: T,
) : Dupe {

    fun hash(): ULong = hash

    fun fmt(): String = value.toString()

    fun asRef(): T = value

    fun into(): T = value

    fun cmp(other: Hashed<T>): Int {
        val v = value
        val ov = other.value
        return if (v is Comparable<*> && ov is Comparable<*>) {
            compareValues(v, ov)
        } else {
            hash.compareTo(other.hash)
        }
    }

    fun partialCmp(other: Hashed<T>): Int? = cmp(other)

    override fun toString(): String = fmt()

    override fun hashCode(): Int = hash.toInt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Hashed<*>) return false
        return hash == other.hash && value == other.value
    }

    companion object {
        fun <T> new(value: T): Hashed<T> {
            val h = value?.hashCode()?.toLong()?.toULong() ?: 0uL
            return Hashed(h, value)
        }

        fun <T> from(value: T): Hashed<T> = new(value)
    }
}
