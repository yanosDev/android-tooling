@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.CompactContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        HomeHeaderSection(modifier = Modifier.padding(top = contentPadding.calculateTopPadding()))
        HomeBodySection(selectedSection = selectedSection)
    }
}

@PhonePreview
@Composable
private fun CompactPreview() = YDContentPreview(data = HomeScreenData()) {
    CompactContent(contentPadding = PaddingValues(), selectedSection = HomeSection.Atoms)
}