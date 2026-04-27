@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDSnackbar].
 *
 * Obtain instances via [YDSnackbarDefaults.snackbarColors].
 *
 * @param actionContentColor Color of the action button label.
 * @param containerColor Background color of the snackbar surface.
 * @param contentColor Default text color of the snackbar message.
 */
@Immutable
class YDSnackbarColors internal constructor(
    val actionContentColor: Color,
    val containerColor: Color,
    val contentColor: Color,
)
