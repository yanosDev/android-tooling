@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes

@Stable
internal object YDCardDefaults {

    val Shape: Shape
        @Composable get() = shapes.medium

    val ShadowElevation: YDShadow
        @Composable get() = YDShadow.Zero

    val TonalElevation: YDTonal
        @Composable get() = YDTonal.Zero

    @Composable
    fun cardColors(
        borderColor: Color = Color.Transparent,
        containerColor: Color = colorScheme.surfaceContainerDefault,
        contentColor: Color = colorScheme.onSurface,
        selectedBorderColor: Color = colorScheme.primary,
        selectedContainerColor: Color = colorScheme.surfaceContainerPrimary,
        selectedContentColor: Color = colorScheme.onSurfaceContainerPrimary,
    ) = YDCardColors(
        borderColor = borderColor,
        containerColor = containerColor,
        contentColor = contentColor,
        selectedBorderColor = selectedBorderColor,
        selectedContainerColor = selectedContainerColor,
        selectedContentColor = selectedContentColor,
    )

    @Composable
    fun outlinedCardColors(
        borderColor: Color = colorScheme.line,
        containerColor: Color = colorScheme.surface,
        contentColor: Color = colorScheme.onSurface,
        selectedBorderColor: Color = colorScheme.primary,
        selectedContainerColor: Color = colorScheme.primary,
        selectedContentColor: Color = colorScheme.onPrimary,
    ) = YDCardColors(
        borderColor = borderColor,
        containerColor = containerColor,
        contentColor = contentColor,
        selectedBorderColor = selectedBorderColor,
        selectedContainerColor = selectedContainerColor,
        selectedContentColor = selectedContentColor,
    )
}
