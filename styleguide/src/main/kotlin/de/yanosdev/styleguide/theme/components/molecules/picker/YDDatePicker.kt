@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerDate
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings

private val MonthNames = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

private fun daysInMonth(month: Int, year: Int): Int {
    val isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
    return when (month) {
        2 -> if (isLeap) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
}

@Composable
internal fun YDDatePicker(
    value: YDPickerDate,
    onValueChange: (YDPickerDate) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDPickerColors = YDPickerDefaults.pickerColors(),
    yearRange: IntRange = 1900..2100,
) {
    val years = remember(yearRange) { yearRange.map { it.toString() } }
    var selectedDay by remember { mutableIntStateOf((value.day - 1).coerceIn(0, 30)) }
    var selectedMonth by remember { mutableIntStateOf((value.month - 1).coerceIn(0, 11)) }
    var selectedYear by remember { mutableIntStateOf((value.year - yearRange.first).coerceIn(0, years.lastIndex)) }

    val days by remember {
        derivedStateOf {
            val month = selectedMonth + 1
            val year = yearRange.first + selectedYear
            val maxDay = daysInMonth(month = month, year = year)
            (1..maxDay).map { it.toString().padStart(2, '0') }
        }
    }

    val indicatorColor = colors.selectionIndicatorColor
    val itemHeight = YDPickerDefaults.ItemHeight

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDWheelPickerColumn(
                items = days,
                selectedIndex = selectedDay.coerceIn(0, days.lastIndex),
                onSelectedIndexChange = { index ->
                    selectedDay = index
                    onValueChange(
                        value.copy(
                            day = index + 1,
                            month = selectedMonth + 1,
                            year = yearRange.first + selectedYear,
                        )
                    )
                },
                modifier = Modifier.weight(weight = 1f),
                colors = colors,
                showSelectionIndicator = false,
            )

            Box(modifier = Modifier.width(width = spacings.small))

            YDWheelPickerColumn(
                items = MonthNames,
                selectedIndex = selectedMonth,
                onSelectedIndexChange = { index ->
                    selectedMonth = index
                    val month = index + 1
                    val year = yearRange.first + selectedYear
                    val clampedDay = selectedDay.coerceIn(0, daysInMonth(month = month, year = year) - 1)
                    selectedDay = clampedDay
                    onValueChange(value.copy(day = clampedDay + 1, month = month, year = year))
                },
                modifier = Modifier.weight(weight = 2f),
                colors = colors,
                showSelectionIndicator = false,
            )

            Box(modifier = Modifier.width(width = spacings.small))

            YDWheelPickerColumn(
                items = years,
                selectedIndex = selectedYear,
                onSelectedIndexChange = { index ->
                    selectedYear = index
                    val year = yearRange.first + index
                    val month = selectedMonth + 1
                    val clampedDay = selectedDay.coerceIn(0, daysInMonth(month = month, year = year) - 1)
                    selectedDay = clampedDay
                    onValueChange(value.copy(day = clampedDay + 1, month = month, year = year))
                },
                modifier = Modifier.weight(weight = 2f),
                colors = colors,
                showSelectionIndicator = false,
            )
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxWidth()
                .height(height = itemHeight)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = indicatorColor,
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = size.width, y = 0f),
                        strokeWidth = strokeWidth,
                    )
                    drawLine(
                        color = indicatorColor,
                        start = Offset(x = 0f, y = size.height),
                        end = Offset(x = size.width, y = size.height),
                        strokeWidth = strokeWidth,
                    )
                },
        )
    }
}
