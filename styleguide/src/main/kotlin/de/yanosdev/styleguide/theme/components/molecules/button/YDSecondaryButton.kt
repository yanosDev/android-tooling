@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.button.YDButton
import de.yanosdev.styleguide.theme.components.atoms.button.YDButtonDefaults
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loading: Boolean = false,
    opaqueBackground: Boolean = false
) {
    val colors = when {
        opaqueBackground -> secondaryButtonColorsOpaque()
        else -> secondaryButtonColors()
    }

    YDButton(
        text = text,
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        interactionSource = interactionSource,
        border = secondaryButtonBorder(colors.contentColor(enabled = enabled && !loading).value)
    )
}

@Composable
fun YDSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    opaqueBackground: Boolean = false,
    useVerticalContentPadding: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val colors = when {
        opaqueBackground -> secondaryButtonColorsOpaque()
        else -> secondaryButtonColors()
    }

    YDButton(
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        enabled = enabled,
        loading = loading,
        interactionSource = interactionSource,
        content = content,
        useVerticalContentPadding = useVerticalContentPadding,
        border = secondaryButtonBorder(colors.contentColor(enabled = enabled && !loading).value)
    )
}

@Composable
internal fun secondaryButtonColors() = YDButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = LocalYDContentColor.current,
    disabledContainerColor = Color.Transparent
)

@Composable
internal fun secondaryButtonColorsOpaque() = YDButtonDefaults.buttonColors(
    containerColor = colorScheme.surfaceContainerDefault,
    disabledContainerColor = colorScheme.surfaceContainerDefault
)

internal fun secondaryButtonBorder(color: Color) = BorderStroke(
    width = 1.dp,
    color = color
)

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    Column(
        modifier = Modifier.padding(spacings.small),
        verticalArrangement = spacedBy(spacings.small)
    ) {
        YDSecondaryButton(text = "Secondary Button", onClick = {})

        YDSecondaryButton(text = "Disabled", onClick = {}, enabled = false)

        YDSecondaryButton(text = "Loading", onClick = {}, loading = true)

        YDSecondaryButton(onClick = { }) {
            YDIcon(imageVector = YDIcons.Search, contentDescription = "")
            YDText("With icons")
            YDIcon(imageVector = YDIcons.Check, contentDescription = "")
        }
    }
}
//endregion