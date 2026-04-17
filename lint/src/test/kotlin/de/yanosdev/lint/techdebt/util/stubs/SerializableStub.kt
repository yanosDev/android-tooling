package com.chrono24.mobile.lint.util.stubs

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

fun serializableStub(): TestFile = kotlin(
    """
    package kotlinx.serialization

    @Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS, AnnotationTarget.TYPE)
    annotation class Serializable(
        val with: KClass<out KSerializer<*>> = KSerializer::class
    )
"""
).indented()