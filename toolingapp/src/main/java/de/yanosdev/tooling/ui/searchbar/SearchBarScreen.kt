@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.searchbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.searchbar.YDSearchBar
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.searchbar.model.SearchBarAction
import de.yanosdev.tooling.ui.searchbar.model.SearchBarScreenData

private val allItems = listOf(
    "Apple", "Apricot", "Avocado",
    "Banana", "Blueberry", "Blackberry", "Breadfruit",
    "Cherry", "Coconut", "Cranberry", "Clementine",
    "Date", "Dragon fruit", "Durian",
    "Elderberry",
    "Fig",
    "Grape", "Grapefruit", "Guava",
    "Honeydew", "Huckleberry",
    "Jackfruit",
    "Kiwi", "Kumquat",
    "Lemon", "Lime", "Lychee",
    "Mango", "Melon", "Mulberry",
    "Nectarine",
    "Orange",
    "Papaya", "Passion fruit", "Peach", "Pear", "Pineapple", "Plum", "Pomegranate",
    "Quince",
    "Raspberry",
    "Strawberry",
    "Tangerine",
    "Watermelon",
)

@Composable
internal fun SearchBarScreen(
    navBack: @Composable () -> Unit,
    viewModel: SearchBarViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Search Bar",
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
internal fun YDUIContentScope<SearchBarScreenData, SearchBarAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var query by remember { mutableStateOf(value = "") }
    val filteredItems by remember {
        derivedStateOf {
            if (query.isBlank()) allItems
            else allItems.filter { it.contains(other = query, ignoreCase = true) }
        }
    }

    Column(modifier = modifier.padding(top = contentPadding.calculateTopPadding())) {
        YDSearchBar(
            query = query,
            onQueryChange = { query = it },
            modifier = Modifier.padding(
                horizontal = spacings.large,
                vertical = spacings.medium,
            ),
            placeholder = "Search fruits…",
        )

        YDDivider()

        if (filteredItems.isEmpty()) {
            YDText(
                text = "No results for \"$query\"",
                style = typography.mdRegular,
                modifier = Modifier.padding(
                    horizontal = spacings.large,
                    vertical = spacings.medium,
                ),
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(bottom = contentPadding.calculateBottomPadding()),
            ) {
                items(items = filteredItems, key = { it }) { item ->
                    YDText(
                        text = item,
                        style = typography.mdRegular,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacings.large, vertical = spacings.medium),
                    )
                    YDDivider(modifier = Modifier.padding(horizontal = spacings.large))
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = SearchBarScreenData()) {
    Content(contentPadding = PaddingValues())
}
