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
 * A wrapper that can be used to implement `Hash` using the inner type's [StrongHash].
 */
class UseStrongHashing<out T : StrongHash>(
    val value: T,
) {
    fun hash(state: StrongHasher) {
        value.strongHash(state)
    }

    companion object {
        fun <T : StrongHash> refCast(value: T): UseStrongHashing<T> = UseStrongHashing(value)
    }
}
