@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.tabs

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.indicator.YDDotIndicator
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDTabWithBadge(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    hasBadge: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalYDContentColor.current,
    unselectedContentColor: Color = selectedContentColor,
) = YDTab(
    selected = selected,
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    leading =
        if (hasBadge) {
            @Composable {
                YDDotIndicator(modifier = Modifier.padding(all = spacings.small))
            }
        } else {
            null
        },
    text = { YDText(text = text) },
    interactionSource = interactionSource,
    selectedContentColor = selectedContentColor,
    unselectedContentColor = unselectedContentColor
)

@PhonePreview
@Composable
private fun SelectedPreview() = YDPreview {
    YDTabWithBadge(
        selected = true,
        hasBadge = true,
        onClick = {},
        text = "Tab",
    )
}

@PhonePreview
@Composable
private fun NotSelectedPreview() = YDPreview {
    YDTabWithBadge(
        selected = false,
        hasBadge = true,
        onClick = {},
        text = "Tab",
    )
}