@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
internal object YDSliderDefaults {

    private const val DisabledAlpha = 0.38f

    @Composable
    fun sliderColors(
        activeTickColor: Color = colorScheme.onPrimary,
        activeTrackColor: Color = colorScheme.primary,
        disabledActiveTrackColor: Color = colorScheme.primary.copy(alpha = DisabledAlpha),
        disabledInactiveTrackColor: Color = colorScheme.line.copy(alpha = DisabledAlpha),
        disabledThumbColor: Color = colorScheme.primary.copy(alpha = DisabledAlpha),
        inactiveTickColor: Color = colorScheme.onSurface.copy(alpha = 0.38f),
        inactiveTrackColor: Color = colorScheme.line,
        thumbColor: Color = colorScheme.primary,
    ) = YDSliderColors(
        activeTickColor = activeTickColor,
        activeTrackColor = activeTrackColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledThumbColor = disabledThumbColor,
        inactiveTickColor = inactiveTickColor,
        inactiveTrackColor = inactiveTrackColor,
        thumbColor = thumbColor,
    )
}
