package de.yanosdev.styleguide.theme.foundations.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import de.yanosdev.styleguide.theme.foundations.locals.LocalYDContentColor

class YDTheme

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) = colorScheme.contentColorFor(
    backgroundColor
).takeOrElse { LocalYDContentColor.current }

/**
 * Returns a disabled color variant for the provided color.
 *
 * Note that this function is intentionally composable even though the current implementation
 * doesn't require it. This is to ensure consistency with [contentColorFor] and also allow us to
 * change implementation details (like access to the colorScheme) later.
 *
 * @param color the source color
 * @param disabled if the disabled variant should be returned
 * @return the disabled variant of [color] if [disabled], else [color] is returned unchanged
 */
@Composable
@ReadOnlyComposable
@Stable
fun disabledColorFor(color: Color, disabled: Boolean = true) = when {
    disabled -> color.copy(alpha = DisabledAlpha)
    else -> color
}