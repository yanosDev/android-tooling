@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.chip.YDAssistChip
import de.yanosdev.styleguide.theme.components.molecules.chip.YDFilterChip
import de.yanosdev.styleguide.theme.components.molecules.chip.YDInputChip
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTab
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRow
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.chip.model.ChipAction
import de.yanosdev.tooling.ui.chip.model.ChipScreenData
import kotlinx.coroutines.launch

private val filterTopics = listOf("Technology", "Design", "Business", "Science", "Culture", "Sports", "Health")
private val assistActions = listOf("Add to cart", "Save for later", "Share", "Compare", "Notify me")
private val initialTags = listOf("Kotlin", "Compose", "Android", "UI Design", "Coroutines", "Jetpack")

private val LeadingIconSize = 16.dp

@Composable
internal fun ChipScreen(
    navBack: @Composable () -> Unit,
    viewModel: ChipViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Chips",
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun YDUIContentScope<ChipScreenData, ChipAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Filter", "Input", "Assist")
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
                0 -> FilterPage()
                1 -> InputPage()
                2 -> AssistPage()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterPage(modifier: Modifier = Modifier) {
    val selected = remember { mutableStateListOf(*Array(filterTopics.size) { it < 2 }) }
    LazyColumn(modifier = modifier) {
        item {
            YDText(
                text = "Topics",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                filterTopics.forEachIndexed { index, topic ->
                    YDFilterChip(
                        selected = selected[index],
                        text = topic,
                        onSelectedChange = { selected[index] = it },
                    )
                }
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }
        item {
            YDText(
                text = "Disabled",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDFilterChip(selected = false, text = "Disabled off", onSelectedChange = {}, enabled = false)
                YDFilterChip(selected = true, text = "Disabled on", onSelectedChange = {}, enabled = false)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun InputPage(modifier: Modifier = Modifier) {
    val tags = remember { mutableStateListOf(*initialTags.toTypedArray()) }
    LazyColumn(modifier = modifier) {
        item {
            YDText(
                text = "Selected tags — tap × to remove",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                tags.toList().forEach { tag ->
                    YDInputChip(
                        text = tag,
                        onDismiss = { tags.remove(element = tag) },
                    )
                }
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }
        item {
            YDText(
                text = "With leading icon",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDInputChip(
                    text = "Sonay",
                    onDismiss = {},
                    leadingIcon = {
                        YDIcon(
                            imageVector = YDIcons.User,
                            contentDescription = null,
                            modifier = Modifier.size(size = LeadingIconSize),
                        )
                    },
                )
                YDInputChip(
                    text = "Bookmark",
                    onDismiss = {},
                    leadingIcon = {
                        YDIcon(
                            imageVector = YDIcons.Star,
                            contentDescription = null,
                            modifier = Modifier.size(size = LeadingIconSize),
                        )
                    },
                )
                YDInputChip(text = "Disabled", onDismiss = {}, enabled = false)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AssistPage(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            YDText(
                text = "Quick actions",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                assistActions.forEach { action ->
                    YDAssistChip(text = action, onClick = {})
                }
            }
        }
        item { YDDivider(modifier = Modifier.padding(vertical = spacings.medium)) }
        item {
            YDText(
                text = "With leading icons",
                style = typography.smRegular,
                modifier = Modifier.padding(horizontal = spacings.large, vertical = spacings.small),
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = spacings.large),
                horizontalArrangement = Arrangement.spacedBy(space = spacings.small),
                verticalArrangement = Arrangement.spacedBy(space = spacings.small),
            ) {
                YDAssistChip(
                    text = "Search",
                    onClick = {},
                    leadingIcon = {
                        YDIcon(
                            imageVector = YDIcons.Search,
                            contentDescription = null,
                            modifier = Modifier.size(size = LeadingIconSize),
                        )
                    },
                )
                YDAssistChip(
                    text = "Filter",
                    onClick = {},
                    leadingIcon = {
                        YDIcon(
                            imageVector = YDIcons.Filter,
                            contentDescription = null,
                            modifier = Modifier.size(size = LeadingIconSize),
                        )
                    },
                )
                YDAssistChip(
                    text = "Download",
                    onClick = {},
                    leadingIcon = {
                        YDIcon(
                            imageVector = YDIcons.Download,
                            contentDescription = null,
                            modifier = Modifier.size(size = LeadingIconSize),
                        )
                    },
                )
                YDAssistChip(text = "Disabled", onClick = {}, enabled = false)
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ChipScreenData()) {
    Content(contentPadding = PaddingValues())
}
