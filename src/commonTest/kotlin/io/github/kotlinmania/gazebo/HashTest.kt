// port-lint: tests hash.rs
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
import kotlin.test.assertNotEquals

class HashTest {
    @Test
    fun testHashed() {
        val v1 = Hashed.new("test")
        val v2 = Hashed.from("test")
        val v3 = Hashed.new("magic")
        assertEquals(v1, v2)
        assertNotEquals(v1, v3)
        assertEquals("test", v1.into())
        assertEquals("test", v1.asRef())
        assertEquals("test", v1.fmt())
        assertEquals(v1.hash, v2.hash())
    }
}
