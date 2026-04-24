@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker.model

import androidx.compose.runtime.Immutable
import de.yanosdev.annotation.YDRevisionIn

/**
 * Represents a time value selected by [YDTimePickerDialog].
 *
 * @param hour Hour value. 1–12 in 12-hour format, 0–23 in 24-hour format.
 * @param minute Minute value. 0–59.
 * @param isAm Whether the time is in the AM half of the day. Ignored in 24-hour mode.
 */
@Immutable
data class YDPickerTime(
    val hour: Int,
    val minute: Int,
    val isAm: Boolean = true,
)
