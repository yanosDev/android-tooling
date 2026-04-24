@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shadows
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes
import de.yanosdev.styleguide.theme.themes.YDTheme.tonals
import de.yanosdev.styleguide.theme.themes.contentColorFor

@Stable
internal object YDAlertDialogDefaults {

    val Properties = YDAlertDialogProperties()

    val ContainerColor: Color
        @Composable get() = colorScheme.surface

    val Shape: Shape
        @Composable get() = shapes.large

    val ShadowElevation @Composable get() = shadows.medium

    val TonalElevation @Composable get() = tonals.level1

    @Composable
    fun alertDialogColors(
        containerColor: Color = ContainerColor,
        iconContentColor: Color = contentColorFor(backgroundColor = containerColor),
        textContentColor: Color = colorScheme.onSurfaceVariant,
        titleContentColor: Color = contentColorFor(backgroundColor = containerColor),
    ) = YDAlertDialogColors(
        containerColor = containerColor,
        iconContentColor = iconContentColor,
        textContentColor = textContentColor,
        titleContentColor = titleContentColor,
    )
}
