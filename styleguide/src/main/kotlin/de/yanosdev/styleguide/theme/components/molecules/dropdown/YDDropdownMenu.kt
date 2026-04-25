@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val MinWidth = 112.dp
private val MaxWidth = 280.dp

/**
 * A dropdown menu that anchors to the composable it is placed inside.
 *
 * Wrap the trigger and [YDDropdownMenu] together in a [androidx.compose.foundation.layout.Box].
 * The menu anchors to the bottom-start of that Box and dismisses when the user taps outside or
 * presses back. Populate the menu with [YDDropdownMenuItem] and optionally
 * [YDDropdownMenuDivider].
 *
 * @param expanded Whether the menu is currently visible.
 * @param onDismissRequest Called when the user requests dismissal.
 * @param modifier Modifier applied to the menu surface.
 * @param colors Container and content colors.
 * @param content Menu items — typically [YDDropdownMenuItem] and [YDDropdownMenuDivider].
 */
@Composable
fun YDDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDDropdownMenuColors = YDDropdownMenuDefaults.menuColors(),
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!expanded) return
    Popup(
        alignment = Alignment.BottomStart,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(focusable = true),
    ) {
        YDSurface(
            modifier = modifier.widthIn(min = MinWidth, max = MaxWidth),
            color = colors.containerColor,
            contentColor = colors.contentColor,
            shadowElevation = YDDropdownMenuDefaults.ShadowElevation,
            shape = YDDropdownMenuDefaults.Shape,
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
                    .padding(vertical = spacings.tiny),
                content = content,
            )
        }
    }
}

@PhonePreview
@Composable
private fun DropdownMenuPreview() = YDPreview {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = spacings.medium),
        modifier = Modifier.padding(all = spacings.medium),
    ) {
        YDDropdownMenuItem(text = "Edit", onClick = {})
        YDDropdownMenuItem(
            text = "Share",
            onClick = {},
            leadingIcon = { YDIcon(imageVector = YDIcons.Share, contentDescription = null) },
        )
        YDDropdownMenuDivider()
        YDDropdownMenuItem(
            text = "Delete",
            onClick = {},
            leadingIcon = { YDIcon(imageVector = YDIcons.Trash, contentDescription = null) },
        )
        YDDropdownMenuItem(text = "Disabled", onClick = {}, enabled = false)
    }
}
