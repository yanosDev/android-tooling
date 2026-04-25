@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.card.YDCard
import de.yanosdev.styleguide.theme.components.molecules.card.YDOutlinedCard
import de.yanosdev.styleguide.theme.components.molecules.card.YDSelectableCard
import de.yanosdev.styleguide.theme.components.molecules.card.YDToggleableCard
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTab
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRow
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.card.model.CardAction
import de.yanosdev.tooling.ui.card.model.CardScreenData
import kotlinx.coroutines.launch

@Composable
internal fun CardScreen(
    navBack: @Composable () -> Unit,
    viewModel: CardViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Cards",
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
internal fun YDUIContentScope<CardScreenData, CardAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("Filled", "Outlined", "Selectable", "Toggleable")
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
        val selected = remember { mutableStateListOf<String>() }
        val toggles = remember { mutableStateListOf(false, true, false, true) }
        HorizontalPager(state = pagerState) { page ->
            LazyColumn(
                contentPadding = PaddingValues(all = spacings.medium),
                verticalArrangement = Arrangement.spacedBy(spacings.medium),
            ) {
                when (page) {
                    0 -> {
                        item {
                            YDCard(modifier = Modifier.fillMaxWidth()) {
                                CardBody(title = "Static card", subtitle = "No interaction")
                            }
                        }
                        item {
                            YDCard(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                CardBody(title = "Clickable card", subtitle = "Tap me!")
                            }
                        }
                    }

                    1 -> {
                        item {
                            YDOutlinedCard(modifier = Modifier.fillMaxWidth()) {
                                CardBody(title = "Static outlined card", subtitle = "No interaction")
                            }
                        }
                        item {
                            YDOutlinedCard(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                                CardBody(title = "Clickable outlined card", subtitle = "Tap me!")
                            }
                        }
                    }

                    2 -> {
                        val options = listOf("Option A", "Option B", "Option C", "Option D")
                        items(count = options.size) { index ->
                            val option = options[index]
                            YDSelectableCard(
                                selected = option in selected,
                                onClick = {
                                    if (option in selected) selected.remove(element = option)
                                    else selected.add(element = option)
                                },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier.padding(all = spacings.large),
                                    horizontalArrangement = Arrangement.spacedBy(spacings.medium),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    YDIcon(
                                        imageVector = if (option in selected) YDIcons.Check else YDIcons.Add,
                                        contentDescription = null,
                                    )
                                    YDText(text = option, style = typography.mdRegular)
                                }
                            }
                        }
                    }

                    3 -> {
                        val features = listOf(
                            "Dark mode" to YDIcons.Eye,
                            "Notifications" to YDIcons.Bell,
                            "Analytics" to YDIcons.Database,
                            "Auto-sync" to YDIcons.Refresh,
                        )
                        items(count = features.size) { index ->
                            val (label, icon) = features[index]
                            YDToggleableCard(
                                toggled = toggles[index],
                                onToggleChange = { toggles[index] = it },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier.padding(all = spacings.large),
                                    horizontalArrangement = Arrangement.spacedBy(spacings.medium),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    YDIcon(imageVector = icon, contentDescription = null)
                                    Column(modifier = Modifier.weight(weight = 1f)) {
                                        YDText(text = label, style = typography.mdRegular)
                                        YDText(
                                            text = if (toggles[index]) "Enabled" else "Disabled",
                                            style = typography.smRegular,
                                        )
                                    }
                                    YDIcon(
                                        imageVector = if (toggles[index]) YDIcons.Check else YDIcons.Close,
                                        contentDescription = null,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CardBody(
    subtitle: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(all = spacings.large),
        verticalArrangement = Arrangement.spacedBy(spacings.tiny),
    ) {
        YDText(text = title, style = typography.h5)
        YDText(text = subtitle, style = typography.mdRegular)
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = CardScreenData()) {
    Content(contentPadding = PaddingValues())
}
