@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker.model

import androidx.compose.runtime.Immutable
import de.yanosdev.annotation.YDRevisionIn

/**
 * Represents a date value selected by [YDDatePickerDialog].
 *
 * @param day Day of the month. 1–28/29/30/31 depending on [month] and [year].
 * @param month Month of the year. 1–12.
 * @param year Full calendar year (e.g. 2026).
 */
@Immutable
data class YDPickerDate(
    val day: Int,
    val month: Int,
    val year: Int,
)
