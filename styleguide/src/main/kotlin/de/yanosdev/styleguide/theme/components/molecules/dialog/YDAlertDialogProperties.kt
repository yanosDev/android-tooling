@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dialog

import androidx.compose.runtime.Immutable
import androidx.compose.ui.window.SecureFlagPolicy
import de.yanosdev.annotation.YDRevisionIn

/**
 * Behavior properties for [YDAlertDialog].
 *
 * @param dismissOnBackPress Whether a back gesture dismisses the dialog.
 * @param dismissOnClickOutside Whether tapping the scrim outside the dialog dismisses it.
 * @param securePolicy Screen-capture policy applied to the dialog window.
 * @param usePlatformDefaultWidth When true the platform imposes a standard dialog width
 *   (~280–560 dp). When false the dialog sizes to its content; useful when embedding wide
 *   custom content.
 */
@Immutable
data class YDAlertDialogProperties(
    val dismissOnBackPress: Boolean = true,
    val dismissOnClickOutside: Boolean = true,
    val securePolicy: SecureFlagPolicy = SecureFlagPolicy.Inherit,
    val usePlatformDefaultWidth: Boolean = true,
)
