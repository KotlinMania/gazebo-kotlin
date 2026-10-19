// port-lint: source ext/vec.rs
package io.github.kotlinmania.gazebo.ext

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

import io.github.kotlinmania.gazebo.dupe.Dupe

/**
 * Optimised collect iterator into list, which might be a Result.
 */
fun <T> collectResult(it: Iterator<Result<T>>): Result<List<T>> {
    val res = mutableListOf<T>()
    while (it.hasNext()) {
        val next = it.next()
        if (next.isFailure) {
            val exception = next.exceptionOrNull() ?: Exception("collectResult failed")
            return Result.failure(exception)
        }
        res.add(next.getOrThrow())
    }
    return Result.success(res)
}

/**
 * Extension traits on slices and lists.
 */
interface SliceExt<T> {
    fun <B> map(f: (T) -> B): List<B>

    fun <B> tryMap(f: (T) -> Result<B>): Result<List<B>>

    fun asSingleton(): T?
}

interface SliceClonedExt<T> {
    fun cloned(): List<T>
}

interface SliceDupedExt<T : Dupe> {
    fun duped(): List<T>
}

interface SliceCopiedExt<T> {
    fun copied(): List<T>
}

interface VecExt<T> {
    fun <B> intoMap(f: (T) -> B): List<B>

    fun <B> intoTryMap(f: (T) -> Result<B>): Result<List<B>>
}

fun <T, B> List<T>.mapSlice(f: (T) -> B): List<B> =
    this.map(f)

fun <T, B> List<T>.tryMap(f: (T) -> Result<B>): Result<List<B>> {
    val res = ArrayList<B>(size)
    for (item in this) {
        val mapped = f(item)
        if (mapped.isFailure) {
            val exception = mapped.exceptionOrNull() ?: Exception("tryMap failed")
            return Result.failure(exception)
        }
        res.add(mapped.getOrThrow())
    }
    return Result.success(res)
}

fun <T> List<T>.owned(): List<T> =
    this.toList()

fun <T> List<T>.asSingleton(): T? =
    if (size == 1) this[0] else null

fun <T : Dupe> MutableList<T>.dupeFromSlice(src: List<T>) {
    require(size == src.size) { "Source slice length must match target slice length" }
    for (i in indices) {
        this[i] = src[i]
    }
}

fun <T> List<T>.cloned(): List<T> =
    this.toList()

fun <T : Dupe> List<T>.duped(): List<T> =
    this.toList()

fun <T> List<T>.copied(): List<T> =
    this.toList()

fun <T, B> List<T>.intoMap(f: (T) -> B): List<B> =
    this.map(f)

fun <T, B> List<T>.intoTryMap(f: (T) -> Result<B>): Result<List<B>> =
    this.tryMap(f)
