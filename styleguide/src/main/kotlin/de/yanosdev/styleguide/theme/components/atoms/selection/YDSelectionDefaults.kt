@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.selection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
internal object YDSelectionDefaults {

    private const val DisabledAlpha = 0.38f

    @Composable
    fun selectionColors(
        activeColor: Color = colorScheme.primary,
        activeContentColor: Color = colorScheme.onPrimary,
        disabledActiveColor: Color = activeColor.copy(alpha = DisabledAlpha),
        disabledActiveContentColor: Color = activeContentColor.copy(alpha = DisabledAlpha),
        disabledInactiveColor: Color = colorScheme.line.copy(alpha = DisabledAlpha),
        disabledInactiveContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
        inactiveColor: Color = colorScheme.line,
        inactiveContentColor: Color = colorScheme.onSurface,
    ) = YDSelectionColors(
        activeColor = activeColor,
        activeContentColor = activeContentColor,
        disabledActiveColor = disabledActiveColor,
        disabledActiveContentColor = disabledActiveContentColor,
        disabledInactiveColor = disabledInactiveColor,
        disabledInactiveContentColor = disabledInactiveContentColor,
        inactiveColor = inactiveColor,
        inactiveContentColor = inactiveContentColor,
    )
}
