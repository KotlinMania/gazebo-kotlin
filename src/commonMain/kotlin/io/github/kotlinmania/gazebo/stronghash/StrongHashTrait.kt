package io.github.kotlinmania.gazebo.stronghash

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
 * [StrongHash] is a trait that is notionally similar to `Hash`, but carries the
 * implicit expectation that the hash that will be produced should be as perturbed as possible.
 *
 * [StrongHash] must be implemented such that - when combined with a crypto hasher - the hash value
 * is suitable to use for equality checks. That means fields must not be omitted and pre-computed
 * hash values cannot be used if the hasher used for them is weak.
 */
interface StrongHash {
    fun strongHash(state: StrongHasher)
}
