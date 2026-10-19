// port-lint: tests cast.rs
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

class CastTest {
    class Foo<T>(
        var count: Int,
        val value: T,
    ) {
        fun drop() {
            count -= 1
        }
    }

    private fun downcastString(x: String): String =
        transmuteUnchecked<String>(x)

    @Test
    fun testTransmute() {
        assertEquals("test", downcastString("test"))
    }

    private fun <T> magic(x: T): Foo<List<Byte>> =
        transmuteUnchecked<Foo<List<Byte>>>(x)

    @Test
    fun testTransmuteUnchecked() {
        val input = Foo(8, "magic".encodeToByteArray().toList())
        val output = magic(input)
        assertEquals(8, output.count)
        assertEquals("magic".encodeToByteArray().toList(), output.value)
        output.drop()
        assertEquals(7, output.count)
    }

    private fun toBytes(x: Long): ByteArray {
        val b = ByteArray(8)
        for (i in 0 until 8) {
            b[i] = ((x ushr (i * 8)) and 0xFF).toByte()
        }
        return b
    }

    private fun fromBytes(b: ByteArray): Long {
        var x = 0L
        for (i in 0 until 8) {
            x = x or ((b[i].toLong() and 0xFF) shl (i * 8))
        }
        return x
    }

    @Test
    fun testTransmuteUncheckedBytes() {
        val x = 8932489732L
        val y = fromBytes(toBytes(x))
        assertEquals(x, y)
    }

    @Test
    fun testPointerHelpers() {
        val s = "hello"
        val addr = ptrToUsize(s)
        assertEquals(s.hashCode().toLong().toULong(), addr)
        assertEquals(s, ptr<String>(s))
        assertEquals(s, ptrMut<String>(s))
        assertEquals(s, ptrLifetime(s))
    }
}
