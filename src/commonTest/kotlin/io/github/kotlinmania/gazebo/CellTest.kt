// port-lint: tests cell.rs
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
import kotlin.test.assertTrue

class CellTest {
    @Test
    fun testFromRefDocs() {
        val c = RefCell(Pair(5, 'b'))
        val b1: ARef<Pair<Int, Char>> = ARef.newRef(c.borrow())
        val b2: ARef<Int> = ARef.map(b1) { it.first }
        assertEquals(5, b2.deref())

        val cell = RefCell(listOf(1, 2, 3, 4))
        val borrow = ARef.newRef(cell.borrow())
        val (begin, end) = ARef.mapSplit(borrow) { list -> Pair(list.take(2), list.drop(2)) }
        assertEquals(listOf(1, 2), begin.deref())
        assertEquals(listOf(3, 4), end.deref())
    }

    @Test
    fun testBorrowGuards() {
        val c = RefCell(5)
        assertTrue(c.tryBorrowMut().isSuccess)
        c.releaseMut()
        val r1 = ARef.newRef(c.borrow())
        assertEquals(5, r1.deref())
        assertTrue(c.tryBorrow().isSuccess)
        assertTrue(c.tryBorrowMut().isSuccess)
    }

    @Test
    fun testPointerBasics() {
        val c = "test"
        val p = ARef.newPtr(c)
        val p2 = ARef.map(p) { it.substring(1, 3) }
        assertEquals("es", p2.deref())
    }

    @Test
    fun testRefMapDropping() {
        val c = RefCell("test")
        val p = ARef.newRef(c.borrow())
        val pSub = ARef.map(p) { it.substring(1, 3) }
        assertEquals("es", pSub.deref())
    }

    @Test
    fun testRefFilterMapDropping() {
        val c = RefCell("test")
        val p = ARef.newRef(c.borrow())
        val pSub = ARef.filterMap(p) { it.substring(1, 3) }.getOrThrow()
        assertEquals("es", pSub.deref())
    }

    @Test
    fun testRefAsExpected() {
        // Representation test verifies internal reference behavior.
        val orig = RefCell("test")
        val p = orig.borrow()
        val r = ARef.newRef(p)
        assertEquals("test", r.deref())
    }

    private fun getStr(x: AsARef<String>): ARef<String> =
        ARef.map(x.asAref()) { it }

    @Test
    fun testAsAref() {
        val a = RefCell("hello")
        val b = DirectAsARef("world")
        assertEquals("hello", getStr(a).deref())
        assertEquals("world", getStr(b).deref())
    }
}
