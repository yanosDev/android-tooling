@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.searchbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by [YDSearchBar].
 *
 * Obtain instances via [YDSearchBarDefaults.searchBarColors].
 *
 * @param containerColor Background color of the search bar.
 * @param contentColor Color of the typed text.
 * @param disabledContainerColor [containerColor] when disabled.
 * @param disabledContentColor [contentColor] when disabled.
 * @param leadingIconColor Color of the leading search icon.
 * @param placeholderColor Color of the placeholder text.
 * @param trailingIconColor Color of the trailing clear icon.
 */
@Immutable
class YDSearchBarColors internal constructor(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val leadingIconColor: Color,
    val placeholderColor: Color,
    val trailingIconColor: Color,
)
