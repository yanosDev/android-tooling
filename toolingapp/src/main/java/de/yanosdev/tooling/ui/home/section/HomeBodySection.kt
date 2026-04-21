@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.component.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.home.model.HomeAction
import de.yanosdev.tooling.ui.home.model.HomeScreenData
import de.yanosdev.tooling.ui.home.model.StyleGuideItems

@Composable
internal fun YDUIContentScope<HomeScreenData, HomeAction>.HomeBodySection(
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(spacings.large)) {
        stickyHeader {
            YDText(text = "Overview", style = typography.h2)
        }
        item { Spacer(modifier = Modifier.height(spacings.small)) }
        stickyHeader { StyleItemGroup("SubAtoms") }
        items(items = data.items.filterIsInstance<StyleGuideItems.SubAtoms>()) { StyleItem(name = it.javaClass.simpleName) }
        stickyHeader { StyleItemGroup("Atoms") }
        items(items = data.items.filterIsInstance<StyleGuideItems.Atoms>()) { StyleItem(name = it.javaClass.simpleName) }
        stickyHeader { StyleItemGroup("Molecules") }
        items(items = data.items.filterIsInstance<StyleGuideItems.Molecules>()) { StyleItem(name = it.javaClass.simpleName) }
    }
}

@Composable
private fun StyleItem(
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(spacings.tiny)) {
        YDText(
            modifier = modifier.fillMaxWidth(),
            text = name
        )
    }
}

@Composable
private fun StyleItemGroup(
    groupName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacings.big)
    ) {
        YDText(
            text = groupName,
            style = typography.lgMediumBold
        )
        YDText(
            text = "↓",
            style = typography.lgMediumBold
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = HomeScreenData()) { HomeBodySection() }