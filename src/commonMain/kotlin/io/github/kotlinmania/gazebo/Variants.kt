// port-lint: source variants.rs
package io.github.kotlinmania.gazebo

import kotlin.jvm.JvmName

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

/**
 * Trait for enums or sum types to return the name of the current variant as a string.
 * Useful for debugging messages.
 */
interface VariantName {
    fun variantName(): String

    fun variantNameLowercase(): String
}

/**
 * [VariantName] implementation for nullable values (the equivalent of Option).
 */
class OptionVariant<T>(val value: T?) : VariantName {
    override fun variantName(): String =
        if (value != null) "Some" else "None"

    override fun variantNameLowercase(): String =
        if (value != null) "some" else "none"
}

/**
 * [VariantName] implementation for [Result] values.
 */
class ResultVariant<T>(val result: Result<T>) : VariantName {
    override fun variantName(): String =
        if (result.isSuccess) "Ok" else "Err"

    override fun variantNameLowercase(): String =
        if (result.isSuccess) "ok" else "err"
}

@JvmName("optionVariantName")
fun <T> T?.variantName(): String =
    if (this != null) "Some" else "None"

@JvmName("optionVariantNameLowercase")
fun <T> T?.variantNameLowercase(): String =
    if (this != null) "some" else "none"

@JvmName("resultVariantName")
fun <T> Result<T>.variantName(): String =
    if (isSuccess) "Ok" else "Err"

@JvmName("resultVariantNameLowercase")
fun <T> Result<T>.variantNameLowercase(): String =
    if (isSuccess) "ok" else "err"

/**
 * Example enum implementing [VariantName].
 */
enum class MyEnum(private val customName: String, private val customLower: String) : VariantName {
    Foo("Foo", "foo"),
    Bar("Bar", "bar"),
    FooBaz("FooBaz", "foo_baz");

    override fun variantName(): String = customName
    override fun variantNameLowercase(): String = customLower
}
