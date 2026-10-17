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

import kotlin.test.Test
import kotlin.test.assertEquals

class DisplayContainerTest {
    private class WrappedList(
        val items: List<UInt>,
    ) : Displayable {
        override fun fmt(f: DisplayFormatter) {
            fmtContainer(f, "prefix[", "]", items)
        }

        fun formatNormal(): String {
            val f = DisplayFormatter(alternate = false)
            fmt(f)
            return f.toString()
        }

        fun formatAlternate(): String {
            val f = DisplayFormatter(alternate = true)
            fmt(f)
            return f.toString()
        }
    }

    @Test
    fun testContainer() {
        assertEquals("prefix[]", WrappedList(emptyList()).formatNormal())
        assertEquals("prefix[1]", WrappedList(listOf(1u)).formatNormal())
        assertEquals("prefix[1, 2]", WrappedList(listOf(1u, 2u)).formatNormal())
        assertEquals("prefix[1, 2, 3]", WrappedList(listOf(1u, 2u, 3u)).formatNormal())

        assertEquals("prefix[]", WrappedList(emptyList()).formatAlternate())
        assertEquals("prefix[ 1 ]", WrappedList(listOf(1u)).formatAlternate())
        assertEquals(
            "prefix[\n  1,\n  2\n]",
            WrappedList(listOf(1u, 2u)).formatAlternate(),
        )
        assertEquals(
            "prefix[\n  1,\n  2,\n  3\n]",
            WrappedList(listOf(1u, 2u, 3u)).formatAlternate(),
        )
    }

    private class WrappedKeyed(
        val items: List<Pair<UInt, String>>,
    ) : Displayable {
        override fun fmt(f: DisplayFormatter) {
            fmtKeyedContainer(
                f,
                "prefix[",
                "]",
                ": ",
                items.map { (k, v) -> Pair(k, "\"$v\"") },
            )
        }

        fun formatNormal(): String {
            val f = DisplayFormatter(alternate = false)
            fmt(f)
            return f.toString()
        }

        fun formatAlternate(): String {
            val f = DisplayFormatter(alternate = true)
            fmt(f)
            return f.toString()
        }
    }

    @Test
    fun testKeyedContainer() {
        assertEquals("prefix[]", WrappedKeyed(emptyList()).formatNormal())
        assertEquals("prefix[1: \"1\"]", WrappedKeyed(listOf(1u to "1")).formatNormal())
        assertEquals(
            "prefix[1: \"1\", 2: \"2\"]",
            WrappedKeyed(listOf(1u to "1", 2u to "2")).formatNormal(),
        )
        assertEquals(
            "prefix[1: \"1\", 2: \"2\", 3: \"3\"]",
            WrappedKeyed(listOf(1u to "1", 2u to "2", 3u to "3")).formatNormal(),
        )

        assertEquals("prefix[]", WrappedKeyed(emptyList()).formatAlternate())
        assertEquals(
            "prefix[ 1: \"1\" ]",
            WrappedKeyed(listOf(1u to "1")).formatAlternate(),
        )
        assertEquals(
            "prefix[\n  1: \"1\",\n  2: \"2\"\n]",
            WrappedKeyed(listOf(1u to "1", 2u to "2")).formatAlternate(),
        )
        assertEquals(
            "prefix[\n  1: \"1\",\n  2: \"2\",\n  3: \"3\"\n]",
            WrappedKeyed(listOf(1u to "1", 2u to "2", 3u to "3")).formatAlternate(),
        )
    }

    @Test
    fun testCombinators() {
        val items = listOf("hello" to 1, "world" to 2)
        val f = DisplayFormatter(alternate = false)
        fmtContainer(
            f,
            "{",
            "}",
            iterDisplayChain(
                listOf("magic"),
                items.map { (k, v) -> displayPair(k, "=", v) },
            ).iterator(),
        )
        assertEquals("{magic, hello=1, world=2}", f.toString())
    }

    @Test
    fun testDisplayContainer() {
        assertEquals("[1]", displayContainer("[", "]", listOf(1)).toString())
    }
}
