@file:YDRevisionIn(implementedAt = "2026-04-22", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.contentColorFor

object YDButtonDefaults {
    private const val DisabledAlpha = 0.65f

    @Composable
    fun buttonColors(
        containerColor: Color = colorScheme.primary,
        contentColor: Color = contentColorFor(backgroundColor = containerColor),
        disabledContainerColor: Color = containerColor.copy(alpha = DisabledAlpha),
        disabledContentColor: Color = contentColor.copy(alpha = DisabledAlpha),
    ) = YDButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}