@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.slider

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDSlider].
 *
 * Obtain instances via [YDSliderDefaults.sliderColors].
 *
 * @param activeTickColor Tick mark color on the filled (active) side of a discrete slider.
 * @param activeTrackColor Color of the filled portion of the track.
 * @param disabledActiveTrackColor [activeTrackColor] when disabled.
 * @param disabledInactiveTrackColor [inactiveTrackColor] when disabled.
 * @param disabledThumbColor [thumbColor] when disabled.
 * @param inactiveTickColor Tick mark color on the unfilled (inactive) side of a discrete slider.
 * @param inactiveTrackColor Color of the unfilled portion of the track.
 * @param thumbColor Color of the draggable thumb circle.
 */
@Immutable
class YDSliderColors internal constructor(
    val activeTickColor: Color,
    val activeTrackColor: Color,
    val disabledActiveTrackColor: Color,
    val disabledInactiveTrackColor: Color,
    val disabledThumbColor: Color,
    val inactiveTickColor: Color,
    val inactiveTrackColor: Color,
    val thumbColor: Color,
) {
    internal fun activeTrackColor(enabled: Boolean) = if (enabled) activeTrackColor else disabledActiveTrackColor
    internal fun inactiveTrackColor(enabled: Boolean) = if (enabled) inactiveTrackColor else disabledInactiveTrackColor
    internal fun thumbColor(enabled: Boolean) = if (enabled) thumbColor else disabledThumbColor
    internal fun activeTickColor(enabled: Boolean) = if (enabled) activeTickColor else disabledActiveTrackColor
    internal fun inactiveTickColor(enabled: Boolean) = if (enabled) inactiveTickColor else disabledInactiveTrackColor
}
