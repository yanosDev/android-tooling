package com.chrono24.mobile.lint.util.stubs

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

internal fun composableStub() = kotlin(
    """
        package androidx.compose.runtime

        @Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.PROPERTY_GETTER)     
        public annotation class Composable
    """
).indented()