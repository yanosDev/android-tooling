@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.tabs


import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.indicator.YDBadgeIndicator
import de.yanosdev.styleguide.theme.components.atoms.indicator.YDDotIndicator
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview
import kotlin.math.max

/**
 * Tab that is typically used inside a [YDTabRow].
 *
 */
@Composable
fun YDTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalYDContentColor.current,
    unselectedContentColor: Color = colorScheme.onSurfaceVariant,
    leading: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val styledText: @Composable (() -> Unit)? = text?.let {
        @Composable {
            val style = typography.mdRegular.copy(textAlign = TextAlign.Center)
            ProvideMergedYDTextStyle(value = style, content = text)
        }
    }
    YDTab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        content = {
            val contents = listOfNotNull(leading, styledText, trailing)
            Layout(
                content = { contents.forEach { it() } },
                modifier = Modifier.padding(horizontal = HorizontalTextPadding)
            ) { measurables, constraints ->

                val placeables = measurables.map { it.measure(constraints) }

                val contentWidth = placeables.sumOf { it.width }
                val contentHeight = placeables.maxOfOrNull { it.height } ?: 0

                val height = max(
                    SmallTabHeight.toPx(),
                    contentHeight + IconDistanceFromBaseline.toPx()
                ).toInt()


                layout(
                    width = contentWidth,
                    height = height
                ) {
                    var currentX = 0
                    placeables.forEach { placeable ->
                        val yPosition = (height - placeable.height) / 2
                        placeable.placeRelative(x = currentX, y = yPosition)
                        currentX += placeable.width
                    }
                }
            }
        }
    )
}

/**
 * Tab that is typically used inside a [YDTabRow].
 *
 */
@Composable
fun YDTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalYDContentColor.current,
    unselectedContentColor: Color = colorScheme.onSurfaceVariant,
    content: @Composable ColumnScope.() -> Unit,
) {
    // The color of the Ripple should always be the selected color, as we want to show the color
    // before the item is considered selected, and hence before the new contentColor is
    // provided by TabTransition.
    val ripple = rememberYDRipple(bounded = true, color = selectedContentColor)

    TabTransition(selectedContentColor, unselectedContentColor, selected) {
        Column(
            modifier = modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = ripple
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = content
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    YDTab(selected = true, onClick = {}, text = { YDText("Tab") })
}

@PhonePreview
@Composable
private fun PreviewTrailing() = YDPreview {
    YDTab(
        selected = true,
        onClick = {},
        text = { YDText("Tab") },
        trailing = {
            YDBadgeIndicator(
                "Hello",
                modifier = Modifier.padding(start = spacings.small),
                contentPadding = PaddingValues(horizontal = spacings.small, vertical = spacings.tiny)
            )
        }
    )
}

@PhonePreview
@Composable
private fun PreviewLeading() = YDPreview {
    YDTab(
        selected = true,
        onClick = {},
        text = { YDText("Tab") },
        leading = {
            YDBadgeIndicator(
                "Hello",
                modifier = Modifier.padding(start = spacings.small),
                contentPadding = PaddingValues(horizontal = spacings.small, vertical = spacings.tiny)
            )
        }
    )
}

@PhonePreview
@Composable
private fun PreviewLeadingANdTrailing() = YDPreview {
    YDTab(
        selected = true,
        onClick = {},
        text = { YDText("Tab") },
        leading = {
            YDDotIndicator(modifier = Modifier.padding(end = spacings.small))
        },
        trailing = {
            YDBadgeIndicator("tiny text", modifier = Modifier.padding(start = spacings.small))
        }
    )
}

/**
 * Transition defining how the tint color for a tab animates, when a new tab is selected. This
 * component uses [LocalYDContentColor] to provide an interpolated value between [activeColor]
 * and [inactiveColor] depending on the animation status.
 */
@Composable
private fun TabTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable () -> Unit,
) {
    val transition = updateTransition(targetState = selected, label = "Update")
    val color by transition.animateColor(
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(
                    durationMillis = TabFadeInAnimationDuration,
                    delayMillis = TabFadeInAnimationDelay,
                    easing = LinearEasing
                )
            } else {
                tween(
                    durationMillis = TabFadeOutAnimationDuration,
                    easing = LinearEasing
                )
            }
        },
        label = "Color"
    ) {
        if (it) activeColor else inactiveColor
    }
    CompositionLocalProvider(
        LocalYDContentColor provides color,
        content = content
    )
}


// Tab specifications
private
val SmallTabHeight = 48.dp

// Tab transition specifications
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100
private val IconDistanceFromBaseline = 20.sp

internal val ActiveTabIndicatorHeight = 3.dp

// The horizontal padding on the left and right of text
internal val HorizontalTextPadding = 16.dp
