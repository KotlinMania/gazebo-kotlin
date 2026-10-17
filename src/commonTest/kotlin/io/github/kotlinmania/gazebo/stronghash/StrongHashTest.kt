package io.github.kotlinmania.gazebo.stronghash

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
import kotlin.test.assertNotEquals

class StrongHashTest {
    private class FnvStrongHasher : StrongHasher {
        private var state: ULong = 0xcbf29ce484222325uL

        override fun write(bytes: ByteArray) {
            for (b in bytes) {
                state = state xor (b.toUByte().toULong())
                state *= 0x100000001b3uL
            }
        }

        override fun finish(): ULong = state
    }

    private class TestHashable(
        val weakHash: ULong,
        val strongHashVal: ULong,
    ) : StrongHash {
        fun hash(state: StrongHasher) {
            weakHash.strongHash(state)
        }

        override fun strongHash(state: StrongHasher) {
            strongHashVal.strongHash(state)
        }
    }

    private fun weakHashOf(t: TestHashable): ULong {
        val hasher = FnvStrongHasher()
        t.hash(hasher)
        return hasher.finish()
    }

    private fun <T : StrongHash> strongHashOf(t: T): ULong {
        val hasher = FnvStrongHasher()
        t.strongHash(hasher)
        return hasher.finish()
    }

    private fun <T : StrongHash> useStrongHashOf(t: UseStrongHashing<T>): ULong {
        val hasher = FnvStrongHasher()
        t.hash(hasher)
        return hasher.finish()
    }

    @Test
    fun testUseStrongHashing() {
        val x = TestHashable(weakHash = 1uL, strongHashVal = 2uL)
        val y = TestHashable(weakHash = 1uL, strongHashVal = 3uL)

        assertEquals(weakHashOf(x), weakHashOf(y))
        assertEquals(strongHashOf(x), useStrongHashOf(UseStrongHashing.refCast(x)))
        assertNotEquals(
            useStrongHashOf(UseStrongHashing.refCast(x)),
            useStrongHashOf(UseStrongHashing.refCast(y)),
        )
    }

    private data class Foo(
        val a: UByte,
        val b: String,
        val c: ByteArray,
    ) : StrongHash {
        override fun strongHash(state: StrongHasher) {
            a.strongHash(state)
            b.strongHash(state)
            c.strongHash(state)
        }
    }

    private data class Bar(
        val foo: Foo,
        val size: ULong,
    ) : StrongHash {
        override fun strongHash(state: StrongHasher) {
            foo.strongHash(state)
            size.strongHash(state)
        }
    }

    private sealed class FooBar : StrongHash {
        data class EnumFoo(
            val foo: Foo,
        ) : FooBar() {
            override fun strongHash(state: StrongHasher) {
                0.strongHash(state)
                foo.strongHash(state)
            }
        }

        data class EnumBar(
            val bar: Bar,
            val size: ULong,
        ) : FooBar() {
            override fun strongHash(state: StrongHasher) {
                1.strongHash(state)
                bar.strongHash(state)
                size.strongHash(state)
            }
        }
    }

    @Test
    fun testStrongHashDeriveParity() {
        val foo = Foo(1u, "hello", byteArrayOf())
        val bar = Bar(foo = foo.copy(), size = 1uL)
        val fooBarFoo: FooBar = FooBar.EnumFoo(foo.copy())
        val fooBarBar: FooBar = FooBar.EnumBar(bar = bar.copy(), size = 1uL)

        val fooHash = strongHashOf(foo)
        val barHash = strongHashOf(bar)
        val fooBarFooHash = strongHashOf(fooBarFoo)
        val fooBarBarHash = strongHashOf(fooBarBar)

        assertNotEquals(fooHash, barHash)
        assertNotEquals(fooHash, fooBarFooHash)
        assertNotEquals(fooHash, fooBarBarHash)
        assertNotEquals(barHash, fooBarFooHash)
        assertNotEquals(barHash, fooBarBarHash)
        assertNotEquals(fooBarFooHash, fooBarBarHash)
    }
}
