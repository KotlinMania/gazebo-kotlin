package io.github.kotlinmania.gazebo.displaycontainer

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

import io.github.kotlinmania.either.Either

/**
 * Helper for display implementation of container-y types (like list, tuple).
 */
fun <T> fmtContainer(
    f: DisplayFormatter,
    prefix: String,
    suffix: String,
    items: Iterable<T>,
) {
    fmtContainer(f, prefix, suffix, items.iterator())
}

/**
 * Helper for display implementation of container-y types (like list, tuple).
 */
fun <T> fmtContainer(
    f: DisplayFormatter,
    prefix: String,
    suffix: String,
    items: Sequence<T>,
) {
    fmtContainer(f, prefix, suffix, items.iterator())
}

/**
 * Helper for display implementation of container-y types (like list, tuple).
 */
fun <T> fmtContainer(
    f: DisplayFormatter,
    prefix: String,
    suffix: String,
    items: Iterator<T>,
) {
    val helper =
        if (!items.hasNext()) {
            ContainerDisplayHelper.beginInner(f, prefix, Len.Zero)
        } else {
            val first = items.next()
            if (!items.hasNext()) {
                val h = ContainerDisplayHelper.beginInner(f, prefix, Len.One)
                h.item(first)
                h
            } else {
                val second = items.next()
                val h = ContainerDisplayHelper.beginInner(f, prefix, Len.Many)
                h.item(first)
                h.item(second)
                while (items.hasNext()) {
                    h.item(items.next())
                }
                h
            }
        }
    helper.end(suffix)
}

/**
 * Helper for display implementation of container-y types (like list, tuple).
 */
fun <T> displayContainer(prefix: String, suffix: String, items: Iterable<T>): Displayable =
    object : Displayable {
        override fun fmt(f: DisplayFormatter) {
            fmtContainer(f, prefix, suffix, items)
        }

        override fun toString(): String {
            val f = DisplayFormatter(alternate = false)
            fmt(f)
            return f.toString()
        }
    }

/**
 * Helper for display implementation of container-y types (like dict, struct).
 *
 * Equivalent to [fmtContainer] where the items have [displayPair] applied to them.
 */
fun <K, V> fmtKeyedContainer(
    f: DisplayFormatter,
    prefix: String,
    suffix: String,
    separator: String,
    items: Iterable<Pair<K, V>>,
) {
    fmtKeyedContainer(f, prefix, suffix, separator, items.iterator())
}

/**
 * Helper for display implementation of container-y types (like dict, struct).
 *
 * Equivalent to [fmtContainer] where the items have [displayPair] applied to them.
 */
fun <K, V> fmtKeyedContainer(
    f: DisplayFormatter,
    prefix: String,
    suffix: String,
    separator: String,
    items: Iterator<Pair<K, V>>,
) {
    val mapped = items.asSequence().map { (k, v) -> displayPair(k, separator, v) }.iterator()
    fmtContainer(f, prefix, suffix, mapped)
}

private class EitherDisplayable<A, B>(
    private val value: Either<A, B>,
) : Displayable {
    override fun fmt(f: DisplayFormatter) {
        when (value) {
            is Either.Left -> f.writeStr(formatValue(value.value, f.alternate))
            is Either.Right -> f.writeStr(formatValue(value.value, f.alternate))
        }
    }

    override fun toString(): String {
        val f = DisplayFormatter(alternate = false)
        fmt(f)
        return f.toString()
    }
}

/**
 * Chain two iterables together that produce displayable items.
 */
fun <A, B> iterDisplayChain(first: Iterable<A>, second: Iterable<B>): Sequence<Displayable> =
    iterDisplayChain(first.asSequence(), second.asSequence())

/**
 * Chain two sequences together that produce displayable items.
 */
fun <A, B> iterDisplayChain(first: Sequence<A>, second: Sequence<B>): Sequence<Displayable> =
    (first.map { Either.Left(it) } + second.map { Either.Right(it) }).map { EitherDisplayable(it) }
