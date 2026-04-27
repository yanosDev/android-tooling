@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.picker.YDDatePickerDialog
import de.yanosdev.styleguide.theme.components.molecules.picker.YDMonthYearPickerDialog
import de.yanosdev.styleguide.theme.components.molecules.picker.YDTimePickerDialog
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerDate
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerTime
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerYearMonth
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTab
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRow
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.picker.model.PickerAction
import de.yanosdev.tooling.ui.picker.model.PickerScreenData
import kotlinx.coroutines.launch

private val MonthAbbreviations = listOf(
    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
)

@Composable
internal fun PickerScreen(
    navBack: @Composable () -> Unit,
    viewModel: PickerViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Pickers",
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
        Content(contentPadding = contentPadding)
    }
}

@Composable
internal fun YDUIContentScope<PickerScreenData, PickerAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Time", "Date", "Month/Year")
    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.padding(contentPadding)) {
        YDTabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, tab ->
                YDTab(
                    selected = pagerState.currentPage == index,
                    text = { YDText(text = tab) },
                    onClick = { scope.launch { pagerState.scrollToPage(page = index) } },
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            Column(
                modifier = Modifier.padding(all = spacings.large),
                verticalArrangement = Arrangement.spacedBy(spacings.large),
            ) {
                when (page) {
                    0 -> TimePickerDemos()
                    1 -> DatePickerDemo()
                    2 -> MonthYearPickerDemo()
                }
            }
        }
    }
}

@Composable
private fun TimePickerDemos(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.large)) {
        TimePickerDemo(label = "12-hour format", is24HourFormat = false)
        TimePickerDemo(label = "24-hour format", is24HourFormat = true)
    }
}

@Composable
private fun TimePickerDemo(
    label: String,
    is24HourFormat: Boolean,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(value = false) }
    var result by remember { mutableStateOf<YDPickerTime?>(value = null) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = label, style = typography.lgMediumBold)
        result?.let { time ->
            val hour = time.hour.toString().padStart(2, '0')
            val minute = time.minute.toString().padStart(2, '0')
            val period = if (is24HourFormat) "" else if (time.isAm) " AM" else " PM"
            YDText(text = "Selected: $hour:$minute$period", style = typography.mdRegular)
        }
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Time Picker",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDTimePickerDialog(
            onConfirm = { time ->
                result = time
                showDialog = false
            },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            is24HourFormat = is24HourFormat,
            title = label,
        )
    }
}

@Composable
private fun DatePickerDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }
    var result by remember { mutableStateOf<YDPickerDate?>(value = null) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "Date picker", style = typography.lgMediumBold)
        result?.let { date ->
            val day = date.day.toString().padStart(2, '0')
            val month = date.month.toString().padStart(2, '0')
            YDText(text = "Selected: $day/$month/${date.year}", style = typography.mdRegular)
        }
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Date Picker",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDDatePickerDialog(
            onConfirm = { date ->
                result = date
                showDialog = false
            },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            title = "Select date",
        )
    }
}

@Composable
private fun MonthYearPickerDemo(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(value = false) }
    var result by remember { mutableStateOf<YDPickerYearMonth?>(value = null) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(spacings.small)) {
        YDText(text = "Month / Year picker", style = typography.lgMediumBold)
        result?.let { yearMonth ->
            YDText(
                text = "Selected: ${MonthAbbreviations[yearMonth.month - 1]} ${yearMonth.year}",
                style = typography.mdRegular,
            )
        }
        YDPrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Open Month/Year Picker",
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        YDMonthYearPickerDialog(
            onConfirm = { yearMonth ->
                result = yearMonth
                showDialog = false
            },
            onDismissRequest = { showDialog = false },
            dismissText = "Cancel",
            title = "Select month and year",
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = PickerScreenData()) {
    Content(contentPadding = PaddingValues())
}
