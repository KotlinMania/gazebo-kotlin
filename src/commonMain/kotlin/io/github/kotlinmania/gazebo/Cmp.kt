// port-lint: source cmp.rs
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

/**
 * Performs a chain of comparison operation expressions yielding an integer comparison result,
 * supporting early exit upon hitting the first expression that does not yield 0 (equal)
 * and returning that result. This is useful for easily writing a sequence of expressions
 * necessary to yield a comparison result.
 */
inline fun cmpChain(e: () -> Int): Int = e()

/**
 * Performs a chain of comparison operation expressions with two comparisons.
 */
inline fun cmpChain(e1: () -> Int, e2: () -> Int): Int {
    val r1 = e1()
    if (r1 != 0) return r1
    return e2()
}

/**
 * Performs a chain of comparison operation expressions with three comparisons.
 */
inline fun cmpChain(e1: () -> Int, e2: () -> Int, e3: () -> Int): Int {
    val r1 = e1()
    if (r1 != 0) return r1
    val r2 = e2()
    if (r2 != 0) return r2
    return e3()
}

/**
 * Performs a chain of comparison operation expressions yielding an integer comparison result.
 */
fun cmpChain(comparisons: List<() -> Int>): Int {
    for (cmp in comparisons) {
        val result = cmp()
        if (result != 0) {
            return result
        }
    }
    return 0
}

/**
 * Performs a chain of equals operation expressions yielding a Boolean, supporting
 * early exit upon hitting the first expression that returns false and returning false.
 * This is useful for easily writing a sequence of equals expressions necessary to yield a Boolean.
 */
inline fun eqChain(e: () -> Boolean): Boolean = e()

/**
 * Performs a chain of equals operation expressions with two predicates.
 */
inline fun eqChain(e1: () -> Boolean, e2: () -> Boolean): Boolean =
    e1() && e2()

/**
 * Performs a chain of equals operation expressions with three predicates.
 */
inline fun eqChain(e1: () -> Boolean, e2: () -> Boolean, e3: () -> Boolean): Boolean =
    e1() && e2() && e3()

/**
 * Performs a chain of equals operation expressions yielding a Boolean.
 */
fun eqChain(predicates: List<() -> Boolean>): Boolean {
    for (pred in predicates) {
        if (!pred()) {
            return false
        }
    }
    return true
}
