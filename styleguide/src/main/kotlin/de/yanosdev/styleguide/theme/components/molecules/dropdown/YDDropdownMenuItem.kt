@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.divider.YDDivider
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val ItemMinHeight = 48.dp
private val IconSize = 20.dp

/**
 * A single row item in a [YDDropdownMenu].
 *
 * Disabled items are rendered with [YDDropdownMenuColors.disabledContentColor] and ignore taps.
 * Optional [leadingIcon] and [trailingContent] slots accept any composable — typically a [YDIcon].
 *
 * @param text Label text for this item.
 * @param onClick Called when the item is tapped.
 * @param modifier Modifier applied to the item row.
 * @param colors Content colors for enabled and disabled states.
 * @param enabled Whether this item responds to taps.
 * @param leadingIcon Optional composable drawn before the label.
 * @param trailingContent Optional composable drawn after the label.
 */
@Composable
fun YDDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDDropdownMenuColors = YDDropdownMenuDefaults.menuColors(),
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    CompositionLocalProvider(value = LocalYDContentColor provides contentColor) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberYDRipple(),
                    enabled = enabled,
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(horizontal = spacings.large, vertical = spacings.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = spacings.medium),
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            YDText(
                text = text,
                style = typography.mdRegular,
                modifier = Modifier.weight(weight = 1f),
            )
            if (trailingContent != null) {
                trailingContent()
            }
        }
    }
}

/**
 * A thin horizontal divider for separating groups of [YDDropdownMenuItem]s within a [YDDropdownMenu].
 */
@Composable
fun YDDropdownMenuDivider(modifier: Modifier = Modifier) = YDDivider(modifier = modifier)

@PhonePreview
@Composable
private fun MenuItemPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(space = spacings.small),
    ) {
        YDDropdownMenuItem(text = "Plain item", onClick = {})
        YDDropdownMenuItem(
            text = "With leading icon",
            onClick = {},
            leadingIcon = {
                YDIcon(
                    imageVector = YDIcons.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(size = IconSize),
                )
            },
        )
        YDDropdownMenuItem(
            text = "With trailing content",
            onClick = {},
            trailingContent = {
                YDIcon(
                    imageVector = YDIcons.ArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(size = IconSize),
                )
            },
        )
        YDDropdownMenuDivider()
        YDDropdownMenuItem(text = "Disabled", onClick = {}, enabled = false)
    }
}
