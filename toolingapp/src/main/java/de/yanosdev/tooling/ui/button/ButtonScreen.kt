@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDScreen
import de.yanosdev.core.component.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDSecondaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDTextButton
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTab
import de.yanosdev.styleguide.theme.components.molecules.tabs.YDTabRow
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.button.model.ButtonAction
import de.yanosdev.tooling.ui.button.model.ButtonScreenData
import kotlinx.coroutines.launch

@Composable
internal fun ButtonScreen(
    navBack: @Composable () -> Unit,
    viewModel: ButtonViewModel,
    modifier: Modifier = Modifier
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "This is the ButtonScreen"
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDScreen(viewModel = viewModel) {
        Content(
            contentPadding = contentPadding,
        )
    }
}

@Composable
internal fun YDUIContentScope<ButtonScreenData, ButtonAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val entries = listOf("Primary", "Secondary", "Text")
    val pagerState = rememberPagerState {
        entries.size
    }
    val scope = rememberCoroutineScope()
    Column(modifier.padding(contentPadding)) {
        YDTabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            entries.forEachIndexed { index, tab ->
                YDTab(
                    selected = pagerState.currentPage == index,
                    text = { YDText(text = tab) },
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { page ->
            Column(
                modifier = Modifier.padding(spacings.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacings.small)
            ) {
                when (page) {
                    0 -> {
                        YDPrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button",
                            onClick = {}
                        )
                        YDPrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button Disabled",
                            enabled = false,
                            onClick = {}
                        )
                        YDPrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button Loading",
                            loading = true,
                            onClick = {}
                        )
                        YDPrimaryButton(
                            modifier = Modifier.fillMaxWidth(), onClick = { }
                        ) {
                            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
                            YDText(text = "${entries[page]} Button icons")
                            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
                        }
                    }

                    1 -> {
                        YDSecondaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button",
                            onClick = {}
                        )
                        YDSecondaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button Disabled",
                            enabled = false,
                            onClick = {}
                        )
                        YDSecondaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button Loading",
                            loading = true,
                            onClick = {}
                        )
                        YDSecondaryButton(
                            modifier = Modifier.fillMaxWidth(), onClick = { }
                        ) {
                            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
                            YDText(text = "${entries[page]} Button with icons")
                            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
                        }
                    }

                    2 -> {
                        YDTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button",
                            onClick = {}
                        )
                        YDTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${entries[page]} Button Disabled",
                            enabled = false,
                            onClick = {}
                        )
                        YDTextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { }
                        ) {
                            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
                            YDText(text = "${entries[page]} Button icons")
                            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
                        }
                    }
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ButtonScreenData()) {
    Content(
        contentPadding = PaddingValues(),
    )
}