@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomappbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
object YDBottomAppBarDefaults {

    @Composable
    fun bottomAppBarColors(
        containerColor: Color = colorScheme.surface,
        contentColor: Color = colorScheme.onSurface,
    ) = YDBottomAppBarColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )
}
