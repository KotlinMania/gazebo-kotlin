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

/**
 * Like `Clone`, but should only be available if `Clone` is
 * constant time and zero allocation (e.g. a few reference count bumps).
 * The implementation of [dupe] should _always_ call `clone`.
 */
interface Dupe

fun <T : Dupe> T.dupe(): T = this
