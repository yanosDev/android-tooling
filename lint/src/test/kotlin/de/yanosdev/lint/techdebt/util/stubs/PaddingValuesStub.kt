package de.yanosdev.lint.techdebt.util.stubs

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

internal fun paddingValuesStub() = kotlin(
    """
        package androidx.compose.foundation.layout

        interface PaddingValues
    """
).indented()