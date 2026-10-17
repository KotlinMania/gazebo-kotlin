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

fun <A : Dupe> A?.dupe(): A? = this?.dupe()

fun Unit.dupe(): Unit = Unit

fun <A : Dupe, B : Dupe> Pair<A, B>.dupe(): Pair<A, B> = Pair(first.dupe(), second.dupe())

fun <A : Dupe, B : Dupe, C : Dupe> Triple<A, B, C>.dupe(): Triple<A, B, C> =
    Triple(first.dupe(), second.dupe(), third.dupe())
