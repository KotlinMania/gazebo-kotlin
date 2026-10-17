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

import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.Instant

fun Boolean.dupe(): Boolean = this

internal fun Char.dupe(): Char = this

fun UByte.dupe(): UByte = this

fun UShort.dupe(): UShort = this

fun UInt.dupe(): UInt = this

fun ULong.dupe(): ULong = this

fun Byte.dupe(): Byte = this

fun Short.dupe(): Short = this

fun Int.dupe(): Int = this

fun Long.dupe(): Long = this

fun Float.dupe(): Float = this

fun Double.dupe(): Double = this

fun String.dupe(): String = this

fun <T : Any> KClass<T>.dupe(): KClass<T> = this

fun Instant.dupe(): Instant = this

fun Duration.dupe(): Duration = this
