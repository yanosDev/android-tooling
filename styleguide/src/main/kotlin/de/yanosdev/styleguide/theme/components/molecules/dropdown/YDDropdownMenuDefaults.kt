@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shadows
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes

@Stable
internal object YDDropdownMenuDefaults {

    private const val DisabledAlpha = 0.38f

    val Shape @Composable get() = shapes.medium
    val ShadowElevation: YDShadow @Composable get() = shadows.medium

    @Composable
    fun menuColors(
        containerColor: Color = colorScheme.surfaceContainerNeutral,
        contentColor: Color = colorScheme.onSurface,
        disabledContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
    ) = YDDropdownMenuColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor,
    )
}
