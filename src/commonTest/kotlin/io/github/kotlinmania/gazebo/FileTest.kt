// port-lint: tests file.rs
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
import io.github.kotlinmania.io.readString
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class FileTest {
    class RemoveDir(val path: Path) {
        fun drop() {
            try {
                if (SystemFileSystem.exists(path)) {
                    SystemFileSystem.delete(path, mustExist = false)
                }
            } catch (_: Exception) {
                // Ignore cleanup errors
            }
        }
    }

    private fun randomPart(): String =
        Random.nextInt().toUInt().toString(16)

    @Test
    fun testCreateAllAndWrite() {
        val temp = Path("build/test-temp-gazebo-" + randomPart())
        val removeDir = RemoveDir(temp)
        try {
            val target = Path("$temp/foo/bar/test.txt")
            createDirsAndWrite(target, "contents")
            val source = SystemFileSystem.source(target).buffered()
            val readBack =
                try {
                    source.readString()
                } finally {
                    source.close()
                }
            assertEquals("contents", readBack)
        } catch (e: Exception) {
            // Node WASI environment does not preopen host directories for filesystem modification.
            // Browser environments do not have filesystem access or the path module.
            if (e is UnsupportedOperationException ||
                e.message?.contains("preopened") == true ||
                e.message?.contains("could not be imported") == true
            ) {
                return
            }
            throw e
        } finally {
            removeDir.drop()
        }
    }
}
