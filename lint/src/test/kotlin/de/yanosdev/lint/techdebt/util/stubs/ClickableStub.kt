package com.chrono24.mobile.lint.util.stubs

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin

internal fun clickableStub() = kotlin(
    """
        package androidx.compose.foundation
        
        import androidx.compose.ui.Modifier
        
         fun Modifier.clickable(
            enabled: Boolean = true,
            onClickLabel: String? = null,
            role: Role? = null,
            onClick: () -> Unit
        ) = this

        fun Modifier.clickable(
            interactionSource: MutableInteractionSource?,
            indication: Indication?,
            enabled: Boolean = true,
            onClickLabel: String? = null,
            role: Role? = null,
            onClick: () -> Unit
        ) = this
        
        interface Indication
        interface MutableInteractionSource
        interface Role
    """
).indented()