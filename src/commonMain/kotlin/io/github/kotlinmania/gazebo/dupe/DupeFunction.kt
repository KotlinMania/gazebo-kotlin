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

internal fun <R> (() -> R).dupe(): () -> R = this

internal fun <A1, R> ((A1) -> R).dupe(): (A1) -> R = this

internal fun <A1, A2, R> ((A1, A2) -> R).dupe(): (A1, A2) -> R = this

internal fun <A1, A2, A3, R> ((A1, A2, A3) -> R).dupe(): (A1, A2, A3) -> R = this

internal fun <A1, A2, A3, A4, R> ((A1, A2, A3, A4) -> R).dupe(): (A1, A2, A3, A4) -> R = this

internal fun <A1, A2, A3, A4, A5, R> ((A1, A2, A3, A4, A5) -> R).dupe(): (A1, A2, A3, A4, A5) -> R = this

internal fun <A1, A2, A3, A4, A5, A6, R> ((A1, A2, A3, A4, A5, A6) -> R).dupe(): (A1, A2, A3, A4, A5, A6) -> R = this
