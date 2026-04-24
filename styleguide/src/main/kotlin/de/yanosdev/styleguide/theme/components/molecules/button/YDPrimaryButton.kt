@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.button.YDButton
import de.yanosdev.styleguide.theme.components.atoms.button.YDButtonDefaults
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loading: Boolean = false
) = YDButton(
    text = text,
    onClick = onClick,
    colors = primaryButtonColors(),
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
)

@Composable
fun YDPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) = YDButton(
    onClick = onClick,
    colors = primaryButtonColors(),
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
    content = content
)

@Composable
private fun primaryButtonColors() = YDButtonDefaults.buttonColors(
    containerColor = YDTheme.colorScheme.primary
)

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    Column(
        modifier = Modifier.padding(YDTheme.spacings.small),
        verticalArrangement = Arrangement.spacedBy(YDTheme.spacings.small)
    ) {
        YDPrimaryButton(text = "Primary Button", onClick = {})

        YDPrimaryButton(text = "Disabled", onClick = {}, enabled = false)

        YDPrimaryButton(text = "Loading", onClick = {}, loading = true)

        YDPrimaryButton(onClick = { }) {
            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
            YDText("With icons")
            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
        }
    }
}