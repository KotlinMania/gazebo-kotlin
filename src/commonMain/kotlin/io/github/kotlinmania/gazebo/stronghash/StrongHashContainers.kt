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

fun <T : StrongHash> List<T>.strongHash(state: StrongHasher) {
    state.writeUsize(size.toULong())
    state.writeUsize(size.toULong())
    for (item in this) {
        item.strongHash(state)
    }
}

fun <T : StrongHash> Array<out T>.strongHash(state: StrongHasher) {
    state.writeUsize(size.toULong())
    for (item in this) {
        item.strongHash(state)
    }
}

fun <T : StrongHash> T?.strongHash(state: StrongHasher) {
    (this != null).strongHash(state)
    if (this != null) {
        this.strongHash(state)
    }
}

fun Unit.strongHash(state: StrongHasher) {
    // No-op for unit.
}

fun <A : StrongHash, B : StrongHash> Pair<A, B>.strongHash(state: StrongHasher) {
    2.strongHash(state)
    first.strongHash(state)
    second.strongHash(state)
}

fun <A : StrongHash, B : StrongHash, C : StrongHash> Triple<A, B, C>.strongHash(state: StrongHasher) {
    3.strongHash(state)
    first.strongHash(state)
    second.strongHash(state)
    third.strongHash(state)
}

fun <K : StrongHash, V : StrongHash> Map<K, V>.strongHash(state: StrongHasher) {
    state.writeUsize(size.toULong())
    for ((k, v) in entries) {
        k.strongHash(state)
        v.strongHash(state)
    }
}
