package de.yanosdev.lint.techdebt.util.stubs

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

internal fun modifierStub() = kotlin(
    """
        package androidx.compose.ui

        interface Modifier {
            companion object : Modifier
        }
    """
).indented()