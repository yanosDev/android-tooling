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
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerTime
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography

private val Hours12 = (1..12).map { it.toString().padStart(2, '0') }
private val Hours24 = (0..23).map { it.toString().padStart(2, '0') }
private val Minutes = (0..59).map { it.toString().padStart(2, '0') }
private val Periods = listOf("AM", "PM")

@Composable
internal fun YDTimePicker(
    value: YDPickerTime,
    onValueChange: (YDPickerTime) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDPickerColors = YDPickerDefaults.pickerColors(),
    is24HourFormat: Boolean = false,
) {
    val hours = if (is24HourFormat) Hours24 else Hours12
    val hourIndex = if (is24HourFormat) value.hour else value.hour - 1
    var selectedHour by remember { mutableIntStateOf(hourIndex.coerceIn(0, hours.lastIndex)) }
    var selectedMinute by remember { mutableIntStateOf(value.minute.coerceIn(0, 59)) }
    var selectedPeriodIndex by remember { mutableIntStateOf(if (value.isAm) 0 else 1) }

    val indicatorColor = colors.selectionIndicatorColor
    val itemHeight = YDPickerDefaults.ItemHeight

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDWheelPickerColumn(
                items = hours,
                selectedIndex = selectedHour,
                onSelectedIndexChange = { index ->
                    selectedHour = index
                    val hour = if (is24HourFormat) index else index + 1
                    onValueChange(value.copy(hour = hour, isAm = selectedPeriodIndex == 0))
                },
                modifier = Modifier.weight(weight = 1f),
                colors = colors,
                showSelectionIndicator = false,
            )

            Box(
                modifier = Modifier
                    .width(width = spacings.medium)
                    .height(height = itemHeight * YDPickerDefaults.VisibleItemCount),
                contentAlignment = Alignment.Center,
            ) {
                YDText(
                    text = ":",
                    style = typography.mdMediumBold,
                    color = colors.selectedTextColor,
                )
            }

            YDWheelPickerColumn(
                items = Minutes,
                selectedIndex = selectedMinute,
                onSelectedIndexChange = { index ->
                    selectedMinute = index
                    val hour = if (is24HourFormat) selectedHour else selectedHour + 1
                    onValueChange(value.copy(minute = index, hour = hour, isAm = selectedPeriodIndex == 0))
                },
                modifier = Modifier.weight(weight = 1f),
                colors = colors,
                showSelectionIndicator = false,
            )

            if (!is24HourFormat) {
                Box(modifier = Modifier.width(width = spacings.small))
                YDWheelPickerColumn(
                    items = Periods,
                    selectedIndex = selectedPeriodIndex,
                    onSelectedIndexChange = { index ->
                        selectedPeriodIndex = index
                        val hour = selectedHour + 1
                        onValueChange(value.copy(hour = hour, isAm = index == 0))
                    },
                    modifier = Modifier.weight(weight = 1f),
                    colors = colors,
                    showSelectionIndicator = false,
                )
            }
        }

        // Shared selection indicator spanning all columns
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
