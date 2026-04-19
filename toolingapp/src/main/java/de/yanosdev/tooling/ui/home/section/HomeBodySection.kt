@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.home.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.tooling.ui.home.model.HomeBodySectionData
import de.yanosdev.tooling.ui.home.model.StyleGuideItems


@Composable
internal fun HomeBodySection(
    modifier: Modifier = Modifier,
    data: HomeBodySectionData = HomeBodySectionData(),
    onHomeBodyAction: () -> Unit = {}
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(spacings.b16)) {
        stickyHeader {
            YDText(text = "Overview", style = typography.h2)
        }
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
    Row(modifier = modifier.padding(spacings.b4)) {
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
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spacings.b16)) {
        YDText(
            text = groupName,
            style = typography.lgMediumBold
        )
        YDText(
            "↓",
            style = typography.lgMediumBold
        )
    }
}

@Preview
@Composable
private fun Preview() {
    HomeBodySection(
        data = HomeBodySectionData(),
        onHomeBodyAction = {}
    )
}