@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.fab

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shadows

@Stable
internal object YDFabDefaults {

    private const val DisabledAlpha = 0.38f

    val FabSize = 56.dp
    val SmallFabSize = 40.dp

    // Fully rounded — a fixed-size FAB becomes a circle; an extended FAB becomes a pill.
    val FabShape = RoundedCornerShape(percent = 50)

    val ShadowElevation @Composable get() = shadows.medium

    @Composable
    fun fabColors(
        containerColor: Color = colorScheme.primary,
        contentColor: Color = colorScheme.onPrimary,
        disabledContainerColor: Color = containerColor.copy(alpha = DisabledAlpha),
        disabledContentColor: Color = contentColor.copy(alpha = DisabledAlpha),
    ) = YDFabColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}
