@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.section

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.button.YDButton
import de.yanosdev.styleguide.theme.components.atoms.button.YDButtonDefaults
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.HomeSection
import de.yanosdev.tooling.ui.home.model.StyleGuideItems

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.HomeBodySection(
    selectedSection: HomeSection,
    modifier: Modifier = Modifier,
) {
    val sectionItems: List<StyleGuideItems> = when (selectedSection) {
        HomeSection.SubAtoms -> data.items.filterIsInstance<StyleGuideItems.SubAtoms>()
        HomeSection.Atoms -> data.items.filterIsInstance<StyleGuideItems.Atoms>()
        HomeSection.Molecules -> data.items.filterIsInstance<StyleGuideItems.Molecules>()
    }

    LazyColumn(modifier = modifier, contentPadding = PaddingValues(spacings.large)) {
        items(items = sectionItems) { StyleItem(item = it) }
    }
}

@Composable
private fun YDUIContentScope<HomeScreenData, HomeAction>.StyleItem(
    item: StyleGuideItems,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(all = spacings.tiny)) {
        YDButton(
            colors = YDButtonDefaults.buttonColors(),
            modifier = Modifier.fillMaxWidth(),
            text = item.javaClass.simpleName,
            onClick = { onScreenAction(action = HomeAction.NavToStyleItem(item = item)) },
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = HomeScreenData()) {
    HomeBodySection(selectedSection = HomeSection.Atoms)
}
