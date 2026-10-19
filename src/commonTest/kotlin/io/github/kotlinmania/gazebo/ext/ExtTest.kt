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

import io.github.kotlinmania.gazebo.dupe.Dupe
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ExtTest {
    @Test
    fun testOptionTryMap() {
        val someStr = "foo"
        val noneStr: String? = null
        val okRes = someStr.asOption().tryMap { Result.success(it.length) }
        assertEquals(3, okRes.getOrNull())

        val errRes = someStr.asOption().tryMap { Result.failure<Int>(Exception("fail")) }
        assertTrue(errRes.isFailure)

        val noneRes = noneStr.asOption().tryMap { Result.success(it.length) }
        assertNull(noneRes.getOrNull())
    }

    private fun trueIfEvenThrowOnZero(x: Int): Result<Boolean> =
        if (x == 0) Result.failure(IllegalArgumentException("zero"))
        else Result.success(x % 2 == 0)

    @Test
    fun testIterTryAnyAndTryAll() {
        val list1 = listOf(1, 3, 2)
        assertEquals(true, list1.tryAny(::trueIfEvenThrowOnZero).getOrNull())

        val list2 = listOf(1, 3, 5)
        assertEquals(false, list2.tryAny(::trueIfEvenThrowOnZero).getOrNull())

        val list3 = listOf(1, 0, 2)
        assertTrue(list3.tryAny(::trueIfEvenThrowOnZero).isFailure)

        val list4 = listOf(2, 4, 2)
        assertEquals(true, list4.tryAll(::trueIfEvenThrowOnZero).getOrNull())

        val list5 = listOf(1, 3, 5)
        assertEquals(false, list5.tryAll(::trueIfEvenThrowOnZero).getOrNull())

        val list6 = listOf(2, 0, 2)
        assertTrue(list6.tryAll(::trueIfEvenThrowOnZero).isFailure)
    }

    @Test
    fun testIterTryEqAndCmp() {
        fun doubleEqThrowOnZero(x: Int, y: Int): Result<Boolean> =
            if (x == 0 || y == 0) Result.failure(IllegalArgumentException("zero"))
            else Result.success(x * 2 == y)

        assertEquals(true, listOf(1, 4, 2).tryEqBy(listOf(2, 8, 4), ::doubleEqThrowOnZero).getOrNull())
        assertTrue(listOf(1, 4, 2).tryEqBy(listOf(2, 0, 4), ::doubleEqThrowOnZero).isFailure)

        fun doubleCmpThrowOnZero(x: Int, y: Int): Result<Int> =
            if (x == 0 || y == 0) Result.failure(IllegalArgumentException("zero"))
            else Result.success((x * 2).compareTo(y))

        assertEquals(0, listOf(1, 4, 2).tryCmpBy(listOf(2, 8, 4), ::doubleCmpThrowOnZero).getOrNull())
        assertEquals(-1, listOf(1, 2, 2).tryCmpBy(listOf(2, 8, 4), ::doubleCmpThrowOnZero).getOrNull())
        assertEquals(1, listOf(1, 4, 4).tryCmpBy(listOf(2, 8, 4), ::doubleCmpThrowOnZero).getOrNull())
    }

    @Test
    fun testIterTryUnzipAndSingleton() {
        val pairs = listOf(Result.success(Pair(1, "a")), Result.success(Pair(2, "b")))
        val unzipped = pairs.tryUnzip().getOrThrow()
        assertEquals(listOf(1, 2), unzipped.first)
        assertEquals(listOf("a", "b"), unzipped.second)

        assertEquals(1, listOf(1).intoSingleton())
        assertNull(listOf<Int>().intoSingleton())
        assertNull(listOf(1, 2).intoSingleton())
    }

    data class MyDupe(val v: Int) : Dupe

    @Test
    fun testVecExt() {
        val xs = listOf(1, 2, 3)
        assertEquals(listOf(1, 4, 9), xs.mapSlice { it * it })

        val tryRes = xs.tryMap { Result.success(it * it) }.getOrThrow()
        assertEquals(listOf(1, 4, 9), tryRes)

        val errTry = xs.tryMap { if (it > 1) Result.success(it) else Result.failure(Exception()) }
        assertTrue(errTry.isFailure)

        assertEquals(1, listOf(1).asSingleton())
        assertNull(listOf('a', 'b', 'c').asSingleton())

        val src = listOf(MyDupe(1), MyDupe(2), MyDupe(3), MyDupe(4))
        val dst = mutableListOf(MyDupe(0), MyDupe(0))
        dst.dupeFromSlice(src.drop(2))
        assertEquals(listOf(MyDupe(3), MyDupe(4)), dst)

        assertEquals(listOf(1, 2, 3), xs.cloned())
        assertEquals(listOf(1, 2, 3), xs.copied())
        assertEquals(listOf(1, 2, 3), xs.owned())
        assertEquals(listOf(1, 4, 9), xs.intoMap { it * it })
        assertEquals(listOf(1, 4, 9), xs.intoTryMap { Result.success(it * it) }.getOrThrow())
    }
}
