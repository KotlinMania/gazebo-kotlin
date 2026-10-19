// port-lint: source file.rs
package io.github.kotlinmania.gazebo

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

import io.github.kotlinmania.io.buffered
import io.github.kotlinmania.io.files.Path
import io.github.kotlinmania.io.files.SystemFileSystem

/**
 * A simple API for creating all the directories up to a path for a file, and
 * then writing the contents to that file.
 */
fun createDirsAndWrite(path: Path, contents: ByteArray) {
    path.parent?.let { parent ->
        SystemFileSystem.createDirectories(parent)
    }
    val sink = SystemFileSystem.sink(path).buffered()
    try {
        sink.write(contents)
    } finally {
        sink.close()
    }
}

fun createDirsAndWrite(path: String, contents: ByteArray) {
    createDirsAndWrite(Path(path), contents)
}

fun createDirsAndWrite(path: Path, contents: String) {
    createDirsAndWrite(path, contents.encodeToByteArray())
}

fun createDirsAndWrite(path: String, contents: String) {
    createDirsAndWrite(Path(path), contents.encodeToByteArray())
}
