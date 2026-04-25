@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.searchbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTextStyle
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val MinHeight = 48.dp
private val IconSize = 20.dp

/**
 * A pill-shaped search input field with a leading search icon and a trailing clear button.
 *
 * The clear button appears automatically when [query] is non-empty. Tapping it calls
 * [onQueryChange] with an empty string. When [onSearch] is provided, pressing the keyboard
 * search action calls it with the current [query].
 *
 * @param query Current text in the field.
 * @param onQueryChange Called on every keystroke with the new query string.
 * @param modifier Modifier applied to the search bar.
 * @param colors Background, icon, placeholder, and text colors.
 * @param enabled Whether the field accepts input.
 * @param placeholder Hint text shown when [query] is empty.
 * @param leadingIcon Custom leading slot. Defaults to a search icon.
 * @param onSearch Called when the user presses the IME search action.
 */
@Composable
fun YDSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: YDSearchBarColors = YDSearchBarDefaults.searchBarColors(),
    enabled: Boolean = true,
    placeholder: String = "Search",
    leadingIcon: (@Composable () -> Unit)? = null,
    onSearch: ((String) -> Unit)? = null,
) {
    val clearInteractionSource = remember { MutableInteractionSource() }
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val iconColor = if (enabled) colors.leadingIconColor else colors.disabledContentColor

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = MinHeight),
        enabled = enabled,
        singleLine = true,
        textStyle = LocalYDTextStyle.current.merge(typography.mdRegular).copy(color = contentColor),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch?.invoke(query) }),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = containerColor, shape = YDSearchBarDefaults.Shape)
                    .padding(horizontal = spacings.large, vertical = spacings.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
            ) {
                CompositionLocalProvider(value = LocalYDContentColor provides iconColor) {
                    if (leadingIcon != null) {
                        leadingIcon()
                    } else {
                        YDIcon(
                            imageVector = YDIcons.Search,
                            contentDescription = null,
                            modifier = Modifier.size(size = IconSize),
                        )
                    }
                }
                Box(modifier = Modifier.weight(weight = 1f)) {
                    if (query.isEmpty()) {
                        YDText(
                            text = placeholder,
                            style = typography.mdRegular,
                            color = colors.placeholderColor,
                        )
                    }
                    innerTextField()
                }
                if (query.isNotEmpty() && enabled) {
                    CompositionLocalProvider(value = LocalYDContentColor provides colors.trailingIconColor) {
                        Box(
                            modifier = Modifier.clickable(
                                interactionSource = clearInteractionSource,
                                indication = rememberYDRipple(bounded = false),
                                onClick = { onQueryChange("") },
                            ),
                        ) {
                            YDIcon(
                                imageVector = YDIcons.Close,
                                contentDescription = "Clear",
                                modifier = Modifier.size(size = IconSize),
                            )
                        }
                    }
                }
            }
        },
    )
}

@PhonePreview
@Composable
private fun SearchBarPreview() = YDPreview {
    var query by remember { mutableStateOf(value = "") }
    Column(
        modifier = Modifier.padding(all = spacings.large),
        verticalArrangement = Arrangement.spacedBy(space = spacings.medium),
    ) {
        YDSearchBar(query = query, onQueryChange = { query = it })
        YDSearchBar(query = "Android", onQueryChange = {})
        YDSearchBar(query = "", onQueryChange = {}, enabled = false, placeholder = "Disabled")
    }
}
