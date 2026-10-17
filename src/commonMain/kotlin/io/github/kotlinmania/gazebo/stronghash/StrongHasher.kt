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
 * Hasher state receptor used by [StrongHash].
 */
interface StrongHasher {
    fun finish(): ULong

    fun write(bytes: ByteArray)

    fun writeU8(i: UByte) {
        write(byteArrayOf(i.toByte()))
    }

    fun writeI8(i: Byte) {
        writeU8(i.toUByte())
    }

    fun writeU16(i: UShort) {
        val v = i.toInt()
        write(byteArrayOf((v and 0xFF).toByte(), ((v ushr 8) and 0xFF).toByte()))
    }

    fun writeI16(i: Short) {
        writeU16(i.toUShort())
    }

    fun writeU32(i: UInt) {
        write(
            byteArrayOf(
                (i and 0xFFu).toByte(),
                ((i shr 8) and 0xFFu).toByte(),
                ((i shr 16) and 0xFFu).toByte(),
                ((i shr 24) and 0xFFu).toByte(),
            ),
        )
    }

    fun writeI32(i: Int) {
        writeU32(i.toUInt())
    }

    fun writeU64(i: ULong) {
        write(
            byteArrayOf(
                (i and 0xFFuL).toByte(),
                ((i shr 8) and 0xFFuL).toByte(),
                ((i shr 16) and 0xFFuL).toByte(),
                ((i shr 24) and 0xFFuL).toByte(),
                ((i shr 32) and 0xFFuL).toByte(),
                ((i shr 40) and 0xFFuL).toByte(),
                ((i shr 48) and 0xFFuL).toByte(),
                ((i shr 56) and 0xFFuL).toByte(),
            ),
        )
    }

    fun writeI64(i: Long) {
        writeU64(i.toULong())
    }

    fun writeUsize(i: ULong) {
        writeU64(i)
    }
}
