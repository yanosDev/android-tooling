@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dialog

import androidx.compose.runtime.Immutable
import androidx.compose.ui.window.SecureFlagPolicy
import de.yanosdev.annotation.YDRevisionIn

@Immutable
data class YDAlertDialogProperties(
    val dismissOnBackPress: Boolean = true,
    val dismissOnClickOutside: Boolean = true,
    val securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
    val usePlatformDefaultWidth: Boolean = true,
)
