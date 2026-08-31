// port-lint: source gazebo/cmp_any/src/ord.rs
package io.github.kotlinmania.gazebo.cmpany

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

import kotlin.reflect.KClass

/** Ordering between arbitrary types. */
class OrdAny
    @PublishedApi
    internal constructor(
        private val typeId: KClass<*>,
        private val typeName: String,
        private val value: Any,
        private val cmp: (Any, Any) -> Int,
    ) : Comparable<OrdAny> {
        /** Get [KClass] of the referenced type. */
        fun typeId(): KClass<*> = typeId

        /** Compare by type id first, then by value. */
        override fun compareTo(other: OrdAny): Int {
            val typeCmp = typeName.compareTo(other.typeName)
            if (typeCmp != 0) {
                return typeCmp
            }
            return cmp(value, other.value)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is OrdAny) return false
            return compareTo(other) == 0
        }

        /** Compares for equality with another [OrdAny]. */
        fun eq(other: OrdAny): Boolean = compareTo(other) == 0

        /** Compare by type id first, then by value. */
        fun cmp(other: OrdAny): Int = compareTo(other)

        /** Partial comparison returning an [Int] ordering result. */
        fun partialCmp(other: OrdAny): Int? = compareTo(other)

        override fun hashCode(): Int = typeName.hashCode() * 31 + value.hashCode()

        companion object {
            inline fun <reified A : Comparable<A>> new(a: A): OrdAny {
                val typeId = a::class
                // Kotlin/JS does not support `KClass.qualifiedName`, so use `toString()`
                // as the portable, stable-ish discriminator for type ordering.
                val typeName = typeId.toString()
                val capturedCmp: (Any, Any) -> Int = { lhs, rhs ->
                    // The cmp closure is invoked only after the typeId-equal branch
                    // in [compareTo] above.
                    val typedLhs =
                        lhs as? A
                            ?: throw IllegalStateException("OrdAny comparator received mismatched left value")
                    val typedRhs =
                        rhs as? A
                            ?: throw IllegalStateException("OrdAny comparator received mismatched right value")
                    typedLhs.compareTo(typedRhs)
                }
                return OrdAny(
                    typeId = typeId,
                    typeName = typeName,
                    value = a,
                    cmp = capturedCmp,
                )
            }
        }
    }
