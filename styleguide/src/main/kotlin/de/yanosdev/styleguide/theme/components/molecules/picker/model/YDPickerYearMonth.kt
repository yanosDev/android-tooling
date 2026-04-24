@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker.model

import androidx.compose.runtime.Immutable
import de.yanosdev.annotation.YDRevisionIn

/**
 * Represents a month–year value selected by [YDMonthYearPickerDialog].
 *
 * @param month Month of the year. 1–12.
 * @param year Full calendar year (e.g. 2026).
 */
@Immutable
data class YDPickerYearMonth(
    val month: Int,
    val year: Int,
)
