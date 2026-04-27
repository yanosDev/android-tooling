@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
object YDNavigationBarDefaults {

    private const val DisabledAlpha = 0.38f

    @Composable
    fun navigationBarColors(
        containerColor: Color = colorScheme.surface,
        disabledItemContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
        indicatorColor: Color = colorScheme.surfaceContainerHighlight,
        selectedItemContentColor: Color = colorScheme.onSurface,
        unselectedItemContentColor: Color = colorScheme.onSurfaceVariant,
    ) = YDNavigationBarColors(
        containerColor = containerColor,
        disabledItemContentColor = disabledItemContentColor,
        indicatorColor = indicatorColor,
        selectedItemContentColor = selectedItemContentColor,
        unselectedItemContentColor = unselectedItemContentColor,
    )
}
