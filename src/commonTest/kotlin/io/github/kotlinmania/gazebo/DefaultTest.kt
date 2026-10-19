// port-lint: tests test.rs
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

class DefaultTest {
    enum class NoDefault

    data class Foo<K, V, Z>(
        val mp: Map<K, V> = emptyMap(),
        val xs: List<Z> = emptyList(),
    )

    data object Bar

    @Test
    fun testDefault() {
        val x: Foo<NoDefault, NoDefault, NoDefault> = Foo()
        assertEquals(0, x.mp.size)
        assertEquals(0, x.xs.size)

        assertEquals(Bar, Bar)
    }
}
