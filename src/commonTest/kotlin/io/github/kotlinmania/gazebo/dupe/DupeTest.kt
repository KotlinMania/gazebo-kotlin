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

import kotlin.test.Test
import kotlin.test.assertEquals

class DupeTest {
    private data class Foo(
        val unit: Unit = Unit,
    ) : Dupe

    private data class FooT<T>(
        val foo: T,
    ) : Dupe

    private data object Faz : Dupe

    @Test
    fun testDupeGeneric() {
        val x1 = Foo()
        assertEquals(x1, x1.dupe())

        val x2 = FooT(foo = 1)
        assertEquals(x2, x2.dupe())

        val x3 = Faz
        assertEquals(x3, x3.dupe())
    }

    private class NoClone {
        override fun equals(other: Any?): Boolean = other is NoClone

        override fun hashCode(): Int = 0
    }

    private data class ArcFooT<T>(
        val foo: T,
    ) : Dupe

    @Test
    fun testDupeWithoutInnerClone() {
        val x = ArcFooT(foo = NoClone())
        assertEquals(x, x.dupe())
    }

    private data object Foo2 : Dupe

    private data class Bar(
        val a: Long,
        val b: Boolean,
        val c: Foo2,
    ) : Dupe

    private data class Baz(
        val foo: ULong,
    ) : Dupe

    private sealed class Qux : Dupe {
        data class FooVariant(
            val u: Unit = Unit,
        ) : Qux()

        data object Foo2Variant : Qux()

        data class BarVariant(
            val foo: Foo,
            val bar: Bar,
        ) : Qux()

        data class BazVariant(
            val foo: Foo,
            val bar: Bar,
            val baz: Baz,
        ) : Qux()
    }

    @Test
    fun testDupeEnum() {
        val x1: Qux = Qux.BarVariant(Foo(), Bar(8L, true, Foo2))
        assertEquals(x1, x1.dupe())

        val x2: Qux =
            Qux.BazVariant(
                foo = Foo(),
                bar = Bar(7L, false, Foo2),
                baz = Baz(foo = 9uL),
            )
        assertEquals(x2, x2.dupe())

        val x3: Qux = Qux.FooVariant()
        assertEquals(x3, x3.dupe())

        val x4: Qux = Qux.Foo2Variant
        assertEquals(x4, x4.dupe())
    }

    @Test
    fun testDupeFnAndContainers() {
        assertDupe<Foo>()
        assertDupe<Faz>()

        val fn1: (ULong) -> Unit = {}
        assertEquals(fn1, fn1.dupe())

        val fn2: (String, List<ULong>) -> Boolean = { s, l -> s.isNotEmpty() && l.isNotEmpty() }
        assertEquals(fn2("a", listOf(1uL)), fn2.dupe()("a", listOf(1uL)))

        val inputs = listOf(FooT("Hello"), FooT("World"))
        val outputs =
            inputs
                .iterator()
                .duped()
                .asSequence()
                .toList()
        assertEquals(inputs, outputs)

        val someVal: FooT<String>? = FooT("test")
        val noneVal: FooT<String>? = null
        assertEquals(someVal, someVal.duped())
        assertEquals(null, noneVal.duped())
    }
}
