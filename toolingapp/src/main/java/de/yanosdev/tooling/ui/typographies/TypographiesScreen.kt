@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.typographies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDScreen
import de.yanosdev.core.component.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.typographies.model.TypographiesAction
import de.yanosdev.tooling.ui.typographies.model.TypographiesScreenData
import de.yanosdev.tooling.ui.typographies.section.TypographiesBodySection


@Composable
internal fun TypographiesScreen(
    navBack: @Composable () -> Unit,
    viewModel: TypographiesViewModel,
    modifier: Modifier = Modifier
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Typographies"
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDScreen(viewModel = viewModel) {
        Content(contentPadding = contentPadding)
    }
}

@Composable
internal fun YDUIContentScope<TypographiesScreenData, TypographiesAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(contentPadding)) {
        TypographiesBodySection()
    }
}


@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = TypographiesScreenData()) {
    Content(contentPadding = PaddingValues())
}