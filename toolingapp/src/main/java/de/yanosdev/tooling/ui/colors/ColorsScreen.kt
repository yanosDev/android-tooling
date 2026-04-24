@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.colors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.colors.model.ColorsAction
import de.yanosdev.tooling.ui.colors.model.ColorsScreenData
import de.yanosdev.tooling.ui.colors.section.ColorsBodySection

@Composable
internal fun ColorsScreen(
    navBack: @Composable () -> Unit,
    viewModel: ColorsViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Colors"
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
        Content(
            contentPadding = contentPadding,
        )
    }
}

@Composable
internal fun YDUIContentScope<ColorsScreenData, ColorsAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(contentPadding)) {
        ColorsBodySection()
    }
}


@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ColorsScreenData()) {
    Content(
        contentPadding = PaddingValues(),
    )
}