@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.themes

import androidx.annotation.RequiresPermission
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalContext
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.CompactYDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.DarkTonalElevationColorSchemes
import de.yanosdev.styleguide.theme.foundations.semantics.DarkYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.ExpandedYDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.LightTonalElevationColorSchemes
import de.yanosdev.styleguide.theme.foundations.semantics.LightYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalTonalYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDFontSizes
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDRadius
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDRippleAlpha
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDShadowEnabled
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDShadows
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDShapes
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDSizes
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDSpacings
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTonalEnabled
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTonals
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.foundations.semantics.YDColors
import de.yanosdev.styleguide.theme.foundations.semantics.YDFontSizes
import de.yanosdev.styleguide.theme.foundations.semantics.YDRadius
import de.yanosdev.styleguide.theme.foundations.semantics.YDRippleAlpha
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadows
import de.yanosdev.styleguide.theme.foundations.semantics.YDShapes
import de.yanosdev.styleguide.theme.foundations.semantics.YDSizes
import de.yanosdev.styleguide.theme.foundations.semantics.YDSpacings
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonals
import de.yanosdev.styleguide.theme.foundations.semantics.YDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.contentColorFor
import de.yanosdev.styleguide.theme.foundations.semantics.rememberYDTextSelectionColors
import de.yanosdev.styleguide.theme.foundations.token.YDAlphaTokens
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.util.LocalYDWindowSizeClass
import de.yanosdev.styleguide.theme.util.core.findActivity

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
    disabled -> color.copy(alpha = YDAlphaTokens.Third)
    else -> color
}

object YDTheme {
    val colorScheme: YDColors
        @Composable
        @ReadOnlyComposable
        get() = LocalYDColors.current

    val fontSizes: YDFontSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalYDFontSizes.current

    val radius: YDRadius
        @Composable
        @RequiresPermission.Read
        get() = LocalYDRadius.current

    val alphas: YDRippleAlpha
        @Composable
        @RequiresPermission.Read
        get() = LocalYDRippleAlpha.current

    val shadows: YDShadows
        @Composable
        @RequiresPermission.Read
        get() = LocalYDShadows.current

    val tonals: YDTonals
        @Composable
        @RequiresPermission.Read
        get() = LocalYDTonals.current

    val shapes: YDShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalYDShapes.current

    val sizes: YDSizes
        @Composable
        @ReadOnlyComposable
        get() = LocalYDSizes.current

    val spacings: YDSpacings
        @Composable
        @ReadOnlyComposable
        get() = LocalYDSpacings.current

    val typography: YDTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalYDTypography.current
}

/**
 * Theme implementation for YD styleguide
 *
 * Allows configuration of color scheme, typography, shapes and grid.
 *
 * Keep in mind that while this theme is very similar to the MaterialTheme in terms of structure,
 * implementation and usage, it does not follow Material design guidelines.
 */
@Composable
fun YDTheme(
    alphas: YDRippleAlpha = YDTheme.alphas,
    colorScheme: YDColors = YDTheme.colorScheme,
    fontSizes: YDFontSizes = YDTheme.fontSizes,
    radius: YDRadius = YDTheme.radius,
    shadowElevationEnabled: Boolean = LocalYDShadowEnabled.current,
    shadows: YDShadows = YDTheme.shadows,
    shapes: YDShapes = YDTheme.shapes,
    sizes: YDSizes = YDTheme.sizes,
    spacings: YDSpacings = YDTheme.spacings,
    tonalElevationColorSchemes: List<YDColors> = LocalTonalYDColors.current,
    tonalElevationEnabled: Boolean = LocalYDTonalEnabled.current,
    tonals: YDTonals = YDTheme.tonals,
    typography: YDTypography = YDTheme.typography,
    content: @Composable () -> Unit
) {
    val rippleIndication = rememberYDRipple(color = colorScheme.onSurface)
    val selectionColors = rememberYDTextSelectionColors(colorScheme = colorScheme)

    CompositionLocalProvider(
        LocalTonalYDColors provides tonalElevationColorSchemes,
        LocalYDShadowEnabled provides shadowElevationEnabled,
        LocalYDTonalEnabled provides tonalElevationEnabled,
        LocalYDRippleTheme provides DefaultYDRippleTheme,
        LocalIndication provides rippleIndication,
        LocalTextSelectionColors provides selectionColors,
        LocalYDColors provides colorScheme,
        LocalYDFontSizes provides fontSizes,
        LocalYDRadius provides radius,
        LocalYDRippleAlpha provides alphas,
        LocalYDShadows provides shadows,
        LocalYDTonals provides tonals,
        LocalYDShapes provides shapes,
        LocalYDSizes provides sizes,
        LocalYDSpacings provides spacings,
        LocalYDTypography provides typography,
    ) {
        ProvideMergedYDTextStyle(value = typography.mdRegular, content = content)
    }
}

@ExperimentalMaterial3WindowSizeClassApi
@Composable
fun YDRootTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    val windowSizeClass = calculateWindowSizeClass(LocalContext.current.findActivity())

    YDTheme(
        colorScheme = if (isDarkTheme) DarkYDColors else LightYDColors,
        tonalElevationColorSchemes = when {
            isDarkTheme -> DarkTonalElevationColorSchemes
            else -> LightTonalElevationColorSchemes
        },
        shadowElevationEnabled = !isDarkTheme,
        tonalElevationEnabled = isDarkTheme,
        typography = if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) CompactYDTypography else ExpandedYDTypography,
    ) {
        CompositionLocalProvider(
            LocalYDWindowSizeClass provides windowSizeClass,
            LocalYDContentColor provides colorScheme.onSurface,
            content = content
        )
    }
}