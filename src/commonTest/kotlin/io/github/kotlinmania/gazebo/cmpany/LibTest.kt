// port-lint: tests gazebo/cmp_any/src/lib.rs
package io.github.kotlinmania.gazebo.cmpany

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

class LibTest {
    @Test
    fun testModuleMetadata() {
        assertEquals("cmp_any", CmpAny.MODULE_NAME)
    }

    @Test
    fun testPartialEqAnyMethods() {
        val a = PartialEqAny.new("test")
        val b = PartialEqAny.new("test")
        assertTrue(a.eq(b))
        assertEquals(a.token(), a)
        assertEquals(a.hashCode(), b.hashCode())
    }
}
