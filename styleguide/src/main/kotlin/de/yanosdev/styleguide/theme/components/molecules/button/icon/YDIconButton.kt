@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button.icon

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.YDIconButtonColors
import de.yanosdev.styleguide.theme.util.ydMinTouchTargetSize

@Composable
fun YDIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDIconButtonColors = YDIconButtonDefaults.iconButtonColors(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .ydMinTouchTargetSize()
            .size(YDIconButtonDefaults.IconButtonSize)
            .background(color = colors.containerColor(enabled).value)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = YDIconButtonDefaults.indication
            ),
        contentAlignment = Alignment.Center
    ) {
        val contentColor = colors.contentColor(enabled).value
        CompositionLocalProvider(LocalYDContentColor provides contentColor, content = content)
    }
}

@Composable
fun YDIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: YDIconButtonColors = YDIconButtonDefaults.iconToggleButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .ydMinTouchTargetSize()
            .size(YDIconButtonDefaults.IconButtonSize)
            .background(color = colors.containerColor(enabled, checked).value)
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                enabled = enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                indication = YDIconButtonDefaults.indication
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(targetState = checked) {
            val contentColor = colors.contentColor(enabled, checked).value
            CompositionLocalProvider(LocalYDContentColor provides contentColor, content = content)
        }
    }
}