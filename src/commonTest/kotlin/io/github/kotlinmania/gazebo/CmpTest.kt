// port-lint: tests cmp.rs
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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CmpTest {
    class FakeComparable(
        val first: () -> Int,
        val second: () -> Int,
        val third: () -> Int,
    ) : Comparable<FakeComparable> {
        fun eq(other: FakeComparable): Boolean =
            eqChain(
                { first() == 0 },
                { second() == 0 },
                { third() == 0 },
            )

        fun partialCmp(other: FakeComparable): Int? = cmp(other)

        fun cmp(other: FakeComparable): Int =
            cmpChain(
                { first() },
                { second() },
                { third() },
            )

        override fun compareTo(other: FakeComparable): Int = cmp(other)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FakeComparable) return false
            return eq(other)
        }

        override fun hashCode(): Int = first().hashCode()
    }

    @Test
    fun cmpEqChain() {
        val fakeLess =
            FakeComparable(
                { -1 },
                { error("should return less") },
                { error("should return less") },
            )
        assertEquals(-1, fakeLess.cmp(fakeLess))
        assertFalse(fakeLess.eq(fakeLess))

        val fakeGreater =
            FakeComparable(
                { 1 },
                { error("should return less") },
                { error("should return less") },
            )
        assertEquals(1, fakeGreater.cmp(fakeGreater))
        assertFalse(fakeGreater.eq(fakeGreater))

        val fakeEqualThenLess =
            FakeComparable(
                { 0 },
                { -1 },
                { error("should return less") },
            )
        assertEquals(-1, fakeEqualThenLess.cmp(fakeEqualThenLess))
        assertFalse(fakeEqualThenLess.eq(fakeEqualThenLess))

        val fakeEqualThenGreater =
            FakeComparable(
                { 0 },
                { 0 },
                { 1 },
            )
        assertEquals(1, fakeEqualThenGreater.cmp(fakeEqualThenGreater))
        assertFalse(fakeEqualThenGreater.eq(fakeEqualThenGreater))

        val fakeAllEqual =
            FakeComparable(
                { 0 },
                { 0 },
                { 0 },
            )
        assertEquals(0, fakeAllEqual.cmp(fakeAllEqual))
        assertTrue(fakeAllEqual.eq(fakeAllEqual))
    }
}
