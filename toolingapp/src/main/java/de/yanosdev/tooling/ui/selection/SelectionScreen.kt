@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.selection.YDCheckbox
import de.yanosdev.styleguide.theme.components.atoms.selection.YDRadioButton
import de.yanosdev.styleguide.theme.components.atoms.selection.YDSwitch
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTab
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRow
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.selection.model.SelectionAction
import de.yanosdev.tooling.ui.selection.model.SelectionScreenData
import kotlinx.coroutines.launch

private val checkboxOptions = listOf("Notifications", "Dark mode", "Analytics", "Auto-sync")
private val radioOptions = listOf("Light theme", "Dark theme", "System default")
private val switchOptions = listOf("Push notifications", "Location access", "Analytics", "Auto-sync")

@Composable
internal fun SelectionScreen(
    navBack: @Composable () -> Unit,
    viewModel: SelectionViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Selection Controls",
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
internal fun YDUIContentScope<SelectionScreenData, SelectionAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Checkbox", "Radio", "Switch")
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
            when (page) {
                0 -> CheckboxPage()
                1 -> RadioPage()
                2 -> SwitchPage()
            }
        }
    }
}

@Composable
private fun CheckboxPage(modifier: Modifier = Modifier) {
    val checked = remember { mutableStateListOf(true, false, false, true) }
    LazyColumn(modifier = modifier) {
        items(count = checkboxOptions.size) { index ->
            YDCheckbox(
                checked = checked[index],
                text = checkboxOptions[index],
                onCheckedChange = { checked[index] = it },
            )
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDCheckbox(
                checked = false,
                text = "Disabled unchecked",
                onCheckedChange = {},
                enabled = false,
            )
        }
        item {
            YDCheckbox(
                checked = true,
                text = "Disabled checked",
                onCheckedChange = {},
                enabled = false,
            )
        }
    }
}

@Composable
private fun RadioPage(modifier: Modifier = Modifier) {
    var selected by remember { mutableIntStateOf(value = 0) }
    LazyColumn(modifier = modifier) {
        items(count = radioOptions.size) { index ->
            YDRadioButton(
                selected = selected == index,
                text = radioOptions[index],
                onClick = { selected = index },
            )
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDRadioButton(
                selected = false,
                text = "Disabled unselected",
                onClick = {},
                enabled = false,
            )
        }
        item {
            YDRadioButton(
                selected = true,
                text = "Disabled selected",
                onClick = {},
                enabled = false,
            )
        }
    }
}

@Composable
private fun SwitchPage(modifier: Modifier = Modifier) {
    val checked = remember { mutableStateListOf(true, false, false, true) }
    LazyColumn(modifier = modifier) {
        items(count = switchOptions.size) { index ->
            YDSwitch(
                checked = checked[index],
                text = switchOptions[index],
                onCheckedChange = { checked[index] = it },
            )
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.small)) }
        item {
            YDSwitch(
                checked = false,
                text = "Disabled off",
                onCheckedChange = {},
                enabled = false,
            )
        }
        item {
            YDSwitch(
                checked = true,
                text = "Disabled on",
                onCheckedChange = {},
                enabled = false,
            )
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = SelectionScreenData()) {
    Content(contentPadding = PaddingValues())
}
