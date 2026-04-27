@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
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
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerYearMonth
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings

private val MonthNames = listOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)

@Composable
internal fun YDMonthYearPicker(
    value: YDPickerYearMonth,
    onValueChange: (YDPickerYearMonth) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDPickerColors = YDPickerDefaults.pickerColors(),
    yearRange: IntRange = 1900..2100,
) {
    val years = remember(yearRange) { yearRange.map { it.toString() } }
    var selectedMonth by remember { mutableIntStateOf((value.month - 1).coerceIn(0, 11)) }
    var selectedYear by remember { mutableIntStateOf((value.year - yearRange.first).coerceIn(0, years.lastIndex)) }

    val indicatorColor = colors.selectionIndicatorColor
    val itemHeight = YDPickerDefaults.ItemHeight

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDWheelPickerColumn(
                items = MonthNames,
                selectedIndex = selectedMonth,
                onSelectedIndexChange = { index ->
                    selectedMonth = index
                    onValueChange(value.copy(month = index + 1, year = yearRange.first + selectedYear))
                },
                modifier = Modifier.weight(weight = 3f),
                colors = colors,
                showSelectionIndicator = false,
            )

            Box(modifier = Modifier.width(width = spacings.small))

            YDWheelPickerColumn(
                items = years,
                selectedIndex = selectedYear,
                onSelectedIndexChange = { index ->
                    selectedYear = index
                    onValueChange(value.copy(month = selectedMonth + 1, year = yearRange.first + index))
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
