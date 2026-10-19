// port-lint: tests variants.rs
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

class VariantsTest {
    sealed class MyEnum : VariantName {
        data object Foo : MyEnum() {
            override fun variantName(): String = "Foo"
            override fun variantNameLowercase(): String = "foo"
        }

        data class Bar(val value: ULong) : MyEnum() {
            override fun variantName(): String = "Bar"
            override fun variantNameLowercase(): String = "bar"
        }

        data class FooBaz(val field: ULong) : MyEnum() {
            override fun variantName(): String = "FooBaz"
            override fun variantNameLowercase(): String = "foo_baz"
        }
    }

    @Test
    fun deriveVariantNames() {
        val x1 = MyEnum.Foo
        assertEquals("Foo", x1.variantName())
        assertEquals("foo", x1.variantNameLowercase())

        val x2 = MyEnum.Bar(1uL)
        assertEquals("Bar", x2.variantName())
        assertEquals("bar", x2.variantNameLowercase())

        val x3 = MyEnum.FooBaz(1uL)
        assertEquals("FooBaz", x3.variantName())
        assertEquals("foo_baz", x3.variantNameLowercase())

        val someOpt: String? = "test"
        val noneOpt: String? = null
        assertEquals("Some", someOpt.variantName())
        assertEquals("some", someOpt.variantNameLowercase())
        assertEquals("None", noneOpt.variantName())
        assertEquals("none", noneOpt.variantNameLowercase())

        val okRes = Result.success(42)
        val errRes = Result.failure<Int>(Exception("fail"))
        assertEquals("Ok", okRes.variantName())
        assertEquals("ok", okRes.variantNameLowercase())
        assertEquals("Err", errRes.variantName())
        assertEquals("err", errRes.variantNameLowercase())
    }
}
