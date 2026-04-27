@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.searchbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
internal object YDSearchBarDefaults {

    private const val DisabledAlpha = 0.38f

    val Shape = RoundedCornerShape(percent = 50)

    @Composable
    fun searchBarColors(
        containerColor: Color = colorScheme.surfaceContainerNeutral,
        contentColor: Color = colorScheme.onSurface,
        disabledContainerColor: Color = colorScheme.surfaceContainerNeutral.copy(alpha = DisabledAlpha),
        disabledContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
        leadingIconColor: Color = colorScheme.onSurface,
        placeholderColor: Color = colorScheme.placeholder,
        trailingIconColor: Color = colorScheme.onSurface,
    ) = YDSearchBarColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        leadingIconColor = leadingIconColor,
        placeholderColor = placeholderColor,
        trailingIconColor = trailingIconColor,
    )
}
