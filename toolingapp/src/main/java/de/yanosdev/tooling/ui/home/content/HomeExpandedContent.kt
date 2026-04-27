@file:YDRevisionIn(implementedAt = "2026-04-27", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.content

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
import de.yanosdev.styleguide.theme.components.molecules.navigationdrawer.YDNavigationDrawer
import de.yanosdev.styleguide.theme.components.molecules.navigationdrawer.YDNavigationDrawerItem
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.home.icon
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.section.HomeBodySection
import de.yanosdev.tooling.ui.home.section.HomeHeaderSection

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.ExpandedContent(
    contentPadding: PaddingValues,
    selectedSection: HomeSection,
    onSectionChange: (HomeSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(top = contentPadding.calculateTopPadding()),
    ) {
        YDNavigationDrawer(
            windowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
            header = { HomeHeaderSection() },
        ) {
            HomeSection.entries.forEach { section ->
                YDNavigationDrawerItem(
                    label = section.label,
                    selected = selectedSection == section,
                    onClick = { onSectionChange(section) },
                    icon = { YDIcon(imageVector = section.icon(), contentDescription = section.label) },
                )
            }
        }
        HomeBodySection(
            selectedSection = selectedSection,
            modifier = Modifier.weight(weight = 1f),
        )
    }
}

@PhonePreview
@Composable
private fun ExpandedPreview() = YDContentPreview(data = HomeScreenData()) {
    ExpandedContent(
        contentPadding = PaddingValues(),
        selectedSection = HomeSection.Atoms,
        onSectionChange = {},
    )
}
