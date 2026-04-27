@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shadows

@Stable
internal object YDSnackbarDefaults {

    val Shape = RoundedCornerShape(percent = 50)
    val ShadowElevation: YDShadow @Composable get() = shadows.medium

    @Composable
    fun snackbarColors(
        actionContentColor: Color = colorScheme.active,
        containerColor: Color = colorScheme.surfaceContainerPrimary,
        contentColor: Color = colorScheme.onSurfaceContainerPrimary,
    ) = YDSnackbarColors(
        actionContentColor = actionContentColor,
        containerColor = containerColor,
        contentColor = contentColor,
    )
}
