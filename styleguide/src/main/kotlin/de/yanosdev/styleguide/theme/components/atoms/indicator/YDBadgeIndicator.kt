@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.indicator


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.themes.contentColorFor
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
fun YDBadgeIndicator(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = YDBadgeV2Defaults.color,
    contentColor: Color = contentColorFor(backgroundColor = color),
    contentPadding: PaddingValues = PaddingValues(horizontal = spacings.tiny),
    textStyle: TextStyle = YDBadgeV2Defaults.smallTextStyle,
) = Box(
    modifier = modifier
        .clip(CircleShape)
        .background(color)
        .layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            if (text.length <= 1) {
                val size = placeable.height
                layout(width = size, height = size) {
                    placeable.placeRelative(
                        x = (size - placeable.width) / 2,
                        y = (size - placeable.height) / 2
                    )
                }
            } else {
                layout(width = placeable.width, height = placeable.height) {
                    placeable.placeRelative(x = 0, y = 0)
                }
            }
        },
    contentAlignment = Alignment.Center,
) {
    YDText(
        text = text,
        modifier = Modifier.padding(contentPadding),
        style = textStyle,
        color = contentColor,
    )
}

@Composable
fun YDBetaBadge(modifier: Modifier = Modifier) = YDBadgeIndicator(
    text = "Beta",
    modifier = modifier,
)

internal object YDBadgeV2Defaults {
    val color @Composable get() = colorScheme.surfaceContainerSignal

    val smallTextStyle
        @Composable get() = typography.smMediumBold.copy(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        )

    val mediumTextStyle
        @Composable get() = typography.mdMediumBold.copy(
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        )
}

@PhonePreview
@Composable
private fun SingleCharacterPreview() = YDPreview(modifier = Modifier.padding(spacings.small)) {
    YDBadgeIndicator(text = "1")
}

@PhonePreview
@Composable
private fun TwoCharacterPreview() = YDPreview(modifier = Modifier.padding(spacings.small)) {
    YDBadgeIndicator(text = "12")
}

@PhonePreview
@Composable
private fun MultiCharacterPreview() = YDPreview(modifier = Modifier.padding(spacings.small)) {
    YDBadgeIndicator(text = "BETA")
}