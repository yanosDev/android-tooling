@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.navigationdrawer

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
object YDNavigationDrawerDefaults {

    private const val DisabledAlpha = 0.38f

    val DrawerWidth = 360.dp
    val ItemShape = RoundedCornerShape(percent = 50)

    @Composable
    fun navigationDrawerColors(
        containerColor: Color = colorScheme.surface,
        disabledItemContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
        indicatorColor: Color = colorScheme.surfaceContainerHighlight,
        selectedItemContentColor: Color = colorScheme.onSurface,
        unselectedItemContentColor: Color = colorScheme.onSurfaceVariant,
    ) = YDNavigationDrawerColors(
        containerColor = containerColor,
        disabledItemContentColor = disabledItemContentColor,
        indicatorColor = indicatorColor,
        selectedItemContentColor = selectedItemContentColor,
        unselectedItemContentColor = unselectedItemContentColor,
    )
}
