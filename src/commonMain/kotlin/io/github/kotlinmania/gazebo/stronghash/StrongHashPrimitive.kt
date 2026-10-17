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

fun Boolean.strongHash(state: StrongHasher) {
    state.writeU8(if (this) 1u else 0u)
}

fun UByte.strongHash(state: StrongHasher) {
    state.writeU8(this)
}

fun Byte.strongHash(state: StrongHasher) {
    state.writeI8(this)
}

fun UShort.strongHash(state: StrongHasher) {
    state.writeU16(this)
}

fun Short.strongHash(state: StrongHasher) {
    state.writeI16(this)
}

fun UInt.strongHash(state: StrongHasher) {
    state.writeU32(this)
}

fun Int.strongHash(state: StrongHasher) {
    state.writeI32(this)
}

fun ULong.strongHash(state: StrongHasher) {
    state.writeU64(this)
}

fun Long.strongHash(state: StrongHasher) {
    state.writeI64(this)
}

fun String.strongHash(state: StrongHasher) {
    val bytes = encodeToByteArray()
    state.writeUsize(bytes.size.toULong())
    state.write(bytes)
}

fun ByteArray.strongHash(state: StrongHasher) {
    state.writeUsize(size.toULong())
    for (b in this) {
        b.toUByte().strongHash(state)
    }
}
