@file:YDRevisionIn(implementedAt = "2026-04-22", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.button

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.progress.YDCircularProgressIndicator
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.foundations.semantics.YDButtonColors
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes
import de.yanosdev.styleguide.theme.themes.YDTheme.sizes
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.LocalYDMinTouchTargetEnforcement


@Composable
fun YDButton(
    colors: YDButtonColors,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    enabled: Boolean = true,
    fontStyle: TextStyle = typography.mdMediumBold,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loading: Boolean = false,
    shape: Shape = shapes.medium
) = YDButton(
    onClick = onClick,
    colors = colors,
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
    shape = shape,
    fontStyle = fontStyle,
    border = border
) {
    YDText(text = text, textAlign = TextAlign.Center)
}

@Composable
fun YDButton(
    colors: YDButtonColors,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    enabled: Boolean = true,
    fontStyle: TextStyle = typography.mdMediumBold,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    loading: Boolean = false,
    shape: Shape = shapes.medium,
    useDynamicHorizontalContentPadding: Boolean = true,
    useVerticalContentPadding: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val horizontalPadding = if (useDynamicHorizontalContentPadding) spacings.medium else 0.dp
    val verticalPadding = if (useVerticalContentPadding) spacings.small else 0.dp
    val contentColor by colors.contentColor(enabled = enabled && !loading)

    // Fix for Surface minimum size enforcement
    CompositionLocalProvider(
        LocalYDMinTouchTargetEnforcement provides false
    ) {
        ProvideMergedYDTextStyle(
            value = fontStyle.copy(textAlign = TextAlign.Center)
        ) {
            YDSurface(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled && !loading,
                shape = shape,
                color = colors.containerColor(enabled = enabled && !loading).value,
                contentColor = contentColor,
                border = border,
                interactionSource = interactionSource
            ) {
                Crossfade(
                    targetState = loading,
                    modifier = Modifier.wrapContentSize(),
                    label = "Button loading state"
                ) { loading ->
                    Box(
                        modifier = Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        if (loading) {
                            YDCircularProgressIndicator(
                                modifier = Modifier
                                    .height(sizes.tiny)
                                    .aspectRatio(1.0f)
                                    .align(Alignment.Center),
                                color = colors.contentColor(enabled = enabled).value
                            )
                        }
                        Row(modifier = Modifier.alpha(if (loading) 0f else 1f)) {
                            if (!useDynamicHorizontalContentPadding) {
                                Row(
                                    horizontalArrangement = spacedBy(
                                        space = spacings.small,
                                        alignment = Alignment.CenterHorizontally
                                    ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = content
                                )
                            } else {
                                Spacer(
                                    modifier = Modifier
                                        .width(spacings.large)
                                        .weight(weight = 1f, fill = false)
                                )
                                Row(
                                    horizontalArrangement = spacedBy(
                                        space = spacings.small,
                                        alignment = Alignment.CenterHorizontally
                                    ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    content = content
                                )
                                Spacer(
                                    modifier = Modifier
                                        .width(spacings.large)
                                        .weight(weight = 1f, fill = false)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

