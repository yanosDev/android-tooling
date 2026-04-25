@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.chip

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
internal object YDChipDefaults {

    private const val DisabledAlpha = 0.38f

    val Shape = RoundedCornerShape(percent = 50)
    val BorderWidth = 1.dp

    @Composable
    fun filterChipColors(
        borderColor: Color = colorScheme.line,
        containerColor: Color = colorScheme.surface,
        contentColor: Color = colorScheme.onSurface,
        disabledBorderColor: Color = colorScheme.line.copy(alpha = DisabledAlpha),
        disabledContainerColor: Color = colorScheme.surface,
        disabledContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
        selectedBorderColor: Color = colorScheme.primary,
        selectedContainerColor: Color = colorScheme.primary,
        selectedContentColor: Color = colorScheme.onPrimary,
    ) = YDChipColors(
        borderColor = borderColor,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledBorderColor = disabledBorderColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        selectedBorderColor = selectedBorderColor,
        selectedContainerColor = selectedContainerColor,
        selectedContentColor = selectedContentColor,
    )

    @Composable
    fun assistChipColors(
        borderColor: Color = colorScheme.line,
        containerColor: Color = colorScheme.surface,
        contentColor: Color = colorScheme.onSurface,
        disabledBorderColor: Color = colorScheme.line.copy(alpha = DisabledAlpha),
        disabledContainerColor: Color = colorScheme.surface,
        disabledContentColor: Color = colorScheme.onSurface.copy(alpha = DisabledAlpha),
    ) = YDChipColors(
        borderColor = borderColor,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledBorderColor = disabledBorderColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        selectedBorderColor = borderColor,
        selectedContainerColor = containerColor,
        selectedContentColor = contentColor,
    )

    @Composable
    fun inputChipColors(
        borderColor: Color = colorScheme.surfaceContainerPrimary,
        containerColor: Color = colorScheme.surfaceContainerPrimary,
        contentColor: Color = colorScheme.onSurfaceContainerPrimary,
        disabledBorderColor: Color = colorScheme.surfaceContainerPrimary.copy(alpha = DisabledAlpha),
        disabledContainerColor: Color = colorScheme.surfaceContainerPrimary.copy(alpha = DisabledAlpha),
        disabledContentColor: Color = colorScheme.onSurfaceContainerPrimary.copy(alpha = DisabledAlpha),
    ) = YDChipColors(
        borderColor = borderColor,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledBorderColor = disabledBorderColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        selectedBorderColor = borderColor,
        selectedContainerColor = containerColor,
        selectedContentColor = contentColor,
    )
}
