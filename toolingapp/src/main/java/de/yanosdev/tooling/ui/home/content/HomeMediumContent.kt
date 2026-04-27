@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.navigationrail.YDNavigationRail
import de.yanosdev.styleguide.theme.components.molecules.navigationrail.YDNavigationRailItem
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.home.icon
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.MediumContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    onSectionChange: (HomeSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxSize()) {
        YDNavigationRail(windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp)) {
            HomeSection.entries.forEach { section ->
                YDNavigationRailItem(
                    selected = selectedSection == section,
                    onClick = { onSectionChange(section) },
                    label = { YDText(text = section.label, style = typography.xsRegular) },
                ) {
                    YDIcon(imageVector = section.icon(), contentDescription = section.label)
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .padding(top = contentPadding.calculateTopPadding()),
        ) {
            HomeHeaderSection()
            HomeBodySection(selectedSection = selectedSection)
        }
    }
}

@PhonePreview
@Composable
private fun MediumPreview() = YDContentPreview(data = HomeScreenData()) {
    MediumContent(
        contentPadding = PaddingValues(),
        selectedSection = HomeSection.Atoms,
        onSectionChange = {},
    )
}
