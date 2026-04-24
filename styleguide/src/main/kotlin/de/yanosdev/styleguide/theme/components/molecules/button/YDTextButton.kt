@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.button.YDButton
import de.yanosdev.styleguide.theme.components.atoms.button.YDButtonDefaults
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = YDTextButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    content = { YDText(text = text, textAlign = TextAlign.Center) }
)

@Composable
fun YDTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textDecoration: TextDecoration? = TextDecoration.Underline,
    content: @Composable RowScope.() -> Unit,
) {
    ProvideMergedYDTextStyle(value = TextStyle(textDecoration = textDecoration)) {
        YDButton(
            onClick = onClick,
            colors = textButtonColors(),
            modifier = modifier,
            enabled = enabled,
            fontStyle = typography.mdRegular,
            interactionSource = interactionSource,
            content = content
        )
    }
}

/**
 * Variant of [YDTextButton] that does not have horizontal padding.
 */
@Composable
fun YDSlimTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = YDSlimTextButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
    content = { YDText(text = text, textAlign = TextAlign.Start) }
)

/**
 * @see [YDSlimTextButton]
 */
@Composable
fun YDSlimTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textDecoration: TextDecoration? = TextDecoration.Underline,
    content: @Composable RowScope.() -> Unit,
) {
    ProvideMergedYDTextStyle(
        value = TextStyle(textDecoration = textDecoration)
    ) {
        YDButton(
            onClick = onClick,
            colors = textButtonColors(),
            modifier = modifier,
            enabled = enabled,
            loading = loading,
            fontStyle = typography.mdRegular,
            useDynamicHorizontalContentPadding = false,
            interactionSource = interactionSource,
            content = content
        )
    }
}

/**
 * Text button for use in top app bars.
 */
@Composable
fun YDTopAppBarTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = YDButton(
    onClick = onClick,
    colors = textButtonColors(),
    modifier = modifier,
    enabled = enabled,
    fontStyle = typography.mdRegular,
    useDynamicHorizontalContentPadding = false,
    interactionSource = interactionSource,
    content = { YDText(text = text, modifier = Modifier.padding(horizontal = spacings.medium)) }
)

@Composable
internal fun textButtonColors(
    containerColor: Color = Color.Transparent,
    contentColor: Color = LocalYDContentColor.current,
) = YDButtonDefaults.buttonColors(
    containerColor = containerColor,
    contentColor = contentColor,
    disabledContainerColor = Color.Transparent
)

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    Column(
        modifier = Modifier.padding(spacings.small),
        verticalArrangement = spacedBy(spacings.small)
    ) {
        YDTextButton(text = "Text Button", onClick = {})

        YDTextButton(text = "Disabled", onClick = {}, enabled = false)

        YDTextButton(onClick = { }) {
            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
            YDText("With icons")
            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
        }
    }
}

@PhonePreview
@Composable
private fun SlimPreview() = YDPreview(modifier = Modifier.width(200.dp)) {
    Column(
        modifier = Modifier.padding(spacings.small),
        verticalArrangement = spacedBy(spacings.small)
    ) {
        YDSlimTextButton(text = "Slim text Button", onClick = {})

        YDSlimTextButton(text = "Disabled", onClick = {}, enabled = false)

        YDSlimTextButton(onClick = { }) {
            YDIcon(imageVector = Icons.Rounded.Search, contentDescription = "")
            YDText("With icons")
            YDIcon(imageVector = Icons.Rounded.Check, contentDescription = "")
        }
    }
}