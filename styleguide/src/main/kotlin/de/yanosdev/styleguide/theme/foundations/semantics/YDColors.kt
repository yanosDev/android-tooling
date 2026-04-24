@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.foundations.semantics

import android.annotation.SuppressLint
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.BuildConfig
import de.yanosdev.styleguide.theme.foundations.token.YDColorTokens

@SuppressLint("DataClassParameterSorting")
@Immutable
data class YDColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val active: Color,
    val onActive: Color,
    val success: Color,
    val onSuccess: Color,
    val warning: Color,
    val onWarning: Color,
    val error: Color,
    val onError: Color,
    val surface: Color,
    val surfaceContainerDefault: Color,
    val surfaceContainerNeutral: Color,
    val surfaceContainerNeutralVariant: Color,
    val surfaceContainerHighlight: Color,
    val surfaceContainerHighlightVariant: Color,
    val surfaceContainerPrimary: Color,
    val surfaceContainerSignal: Color,
    val surfaceContainerMarketing: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val onSurfaceSignal: Color,
    val onSurfaceContainerPrimary: Color,
    val onSurfaceContainerSignal: Color,
    val onSurfaceContainerMarketing: Color,
    val placeholder: Color,
    val line: Color,
    val scrim: Color,
    val onScrim: Color,
    val imageOverlay: Color,
)

internal fun YDColors.elevatedColorFor(color: Color, elevatedScheme: YDColors) = when (color) {
    primary -> elevatedScheme.primary
    onPrimary -> elevatedScheme.onPrimary
    secondary -> elevatedScheme.secondary
    onSecondary -> elevatedScheme.onSecondary
    active -> elevatedScheme.active
    onActive -> elevatedScheme.onActive
    success -> elevatedScheme.success
    onSuccess -> elevatedScheme.onSuccess
    warning -> elevatedScheme.warning
    onWarning -> elevatedScheme.onWarning
    error -> elevatedScheme.error
    onError -> elevatedScheme.onError
    surface -> elevatedScheme.surface
    surfaceContainerDefault -> elevatedScheme.surfaceContainerDefault
    surfaceContainerNeutral -> elevatedScheme.surfaceContainerNeutral
    surfaceContainerNeutralVariant -> elevatedScheme.surfaceContainerNeutralVariant
    surfaceContainerHighlight -> elevatedScheme.surfaceContainerHighlight
    surfaceContainerHighlightVariant -> elevatedScheme.surfaceContainerHighlightVariant
    surfaceContainerPrimary -> elevatedScheme.surfaceContainerPrimary
    surfaceContainerSignal -> elevatedScheme.surfaceContainerSignal
    surfaceContainerMarketing -> elevatedScheme.surfaceContainerMarketing
    onSurface -> elevatedScheme.onSurface
    onSurfaceVariant -> elevatedScheme.onSurfaceVariant
    onSurfaceSignal -> elevatedScheme.onSurfaceSignal
    onSurfaceContainerPrimary -> elevatedScheme.onSurfaceContainerPrimary
    onSurfaceContainerSignal -> elevatedScheme.onSurfaceContainerSignal
    onSurfaceContainerMarketing -> elevatedScheme.onSurfaceContainerMarketing
    placeholder -> elevatedScheme.placeholder
    line -> elevatedScheme.line
    scrim -> elevatedScheme.scrim
    onScrim -> elevatedScheme.onScrim
    imageOverlay -> elevatedScheme.imageOverlay
    else -> color
}

internal fun YDColors.contentColorFor(backgroundColor: Color) = when (backgroundColor) {
    primary -> onPrimary
    secondary -> onSecondary
    active -> onActive
    success -> onSuccess
    warning -> onWarning
    error -> onError
    surface -> onSurface
    surfaceContainerDefault -> onSurface
    surfaceContainerNeutral -> onSurface
    surfaceContainerNeutralVariant -> onSurface
    surfaceContainerHighlight -> onSurface
    surfaceContainerHighlightVariant -> onSurface
    surfaceContainerPrimary -> onSurfaceContainerPrimary
    surfaceContainerSignal -> onSurfaceContainerSignal
    surfaceContainerMarketing -> onSurfaceContainerMarketing
    scrim -> onScrim
    else -> Color.Unspecified
}

@Stable
internal val LightYDColors = YDColors(
    primary = YDColorTokens.DeepBrown20,
    onPrimary = YDColorTokens.Neutral100,
    secondary = YDColorTokens.DeepBrown40,
    onSecondary = YDColorTokens.Neutral100,
    active = YDColorTokens.ActiveBrown50,
    onActive = YDColorTokens.Neutral100,
    success = YDColorTokens.Green30,
    onSuccess = YDColorTokens.Neutral100,
    warning = YDColorTokens.Orange50,
    onWarning = YDColorTokens.Neutral10,
    error = YDColorTokens.Red40,
    onError = YDColorTokens.Neutral100,
    surface = YDColorTokens.Neutral100,
    surfaceContainerDefault = YDColorTokens.Neutral100,
    surfaceContainerNeutral = YDColorTokens.Neutral95,
    surfaceContainerNeutralVariant = YDColorTokens.Neutral90,
    surfaceContainerHighlight = YDColorTokens.WarmGrey95,
    surfaceContainerHighlightVariant = YDColorTokens.WarmGrey80,
    surfaceContainerPrimary = YDColorTokens.DeepBrown20,
    surfaceContainerSignal = YDColorTokens.SignalBrown50,
    surfaceContainerMarketing = YDColorTokens.Neutral100,
    onSurface = YDColorTokens.Neutral10,
    onSurfaceVariant = YDColorTokens.Neutral30,
    onSurfaceSignal = YDColorTokens.SignalBrown50,
    onSurfaceContainerPrimary = YDColorTokens.Neutral100,
    onSurfaceContainerSignal = YDColorTokens.Neutral100,
    onSurfaceContainerMarketing = YDColorTokens.Neutral10,
    placeholder = YDColorTokens.Neutral40,
    line = YDColorTokens.Neutral80,
    scrim = YDColorTokens.Neutral0,
    onScrim = YDColorTokens.Neutral100,
    imageOverlay = YDColorTokens.Neutral0,
)

internal val DarkYDColors = YDColors(
    primary = YDColorTokens.SignalBrown50,
    onPrimary = YDColorTokens.Neutral100,
    secondary = YDColorTokens.DeepBrown90,
    onSecondary = YDColorTokens.CoolGrey10,
    active = YDColorTokens.ActiveBrown80,
    onActive = YDColorTokens.CoolGrey10,
    success = YDColorTokens.Green50,
    onSuccess = YDColorTokens.CoolGrey10,
    warning = YDColorTokens.Orange60,
    onWarning = YDColorTokens.CoolGrey10,
    error = YDColorTokens.Red70,
    onError = YDColorTokens.CoolGrey10,
    surface = YDColorTokens.Neutral10,
    surfaceContainerDefault = YDColorTokens.Neutral10,
    surfaceContainerNeutral = YDColorTokens.Neutral15,
    surfaceContainerNeutralVariant = YDColorTokens.Neutral20,
    surfaceContainerHighlight = YDColorTokens.WarmGrey10,
    surfaceContainerHighlightVariant = YDColorTokens.WarmGrey30,
    surfaceContainerPrimary = YDColorTokens.DeepBrown90,
    surfaceContainerSignal = YDColorTokens.SignalBrown50,
    surfaceContainerMarketing = YDColorTokens.Neutral0,
    onSurface = YDColorTokens.CoolGrey90,
    onSurfaceVariant = YDColorTokens.Neutral70,
    onSurfaceSignal = YDColorTokens.ActiveBrown70,
    onSurfaceContainerPrimary = YDColorTokens.CoolGrey90,
    onSurfaceContainerSignal = YDColorTokens.Neutral100,
    onSurfaceContainerMarketing = YDColorTokens.Neutral100,
    placeholder = YDColorTokens.CoolGrey60,
    line = YDColorTokens.CoolGrey40,
    scrim = YDColorTokens.Neutral0,
    onScrim = YDColorTokens.CoolGrey90,
    imageOverlay = YDColorTokens.Neutral100,
)

internal val LightTonalElevationColorSchemes = listOf(LightYDColors)

internal val DarkYDColorSchemeLevel1 = DarkYDColors.copy(
    surface = YDColorTokens.CoolGrey12,
    surfaceContainerDefault = YDColorTokens.CoolGrey12,
    surfaceContainerNeutral = YDColorTokens.CoolGrey20,
    surfaceContainerNeutralVariant = YDColorTokens.CoolGrey25,
    surfaceContainerHighlight = YDColorTokens.CoolGrey20,
    surfaceContainerHighlightVariant = YDColorTokens.CoolGrey25,
    surfaceContainerPrimary = YDColorTokens.DeepBrown25,
    placeholder = YDColorTokens.CoolGrey70,
)

internal val DarkYDColorSchemeLevel2 = DarkYDColorSchemeLevel1.copy(
    surface = YDColorTokens.CoolGrey15,
    surfaceContainerDefault = YDColorTokens.CoolGrey15,
    surfaceContainerNeutral = YDColorTokens.CoolGrey25,
    surfaceContainerHighlight = YDColorTokens.CoolGrey25,
    surfaceContainerPrimary = YDColorTokens.DeepBrown30,
)

internal val DarkTonalElevationColorSchemes = listOf(
    DarkYDColors,
    DarkYDColorSchemeLevel1,
    DarkYDColorSchemeLevel2,
)

internal val LocalTonalYDColors = staticCompositionLocalOf {
    listOf(LightYDColors)
}

internal val LocalYDColors = staticCompositionLocalOf { LightYDColors }

val LocalYDContentColor = compositionLocalOf {
    if (BuildConfig.DEBUG) LightYDColors.warning else LightYDColors.onSurface
}

@Composable
internal fun rememberYDTextSelectionColors(colorScheme: YDColors): TextSelectionColors {
    val defaultColor = colorScheme.primary
    return remember(defaultColor) {
        TextSelectionColors(
            handleColor = defaultColor,
            backgroundColor = defaultColor.copy(alpha = 0.4f)
        )
    }
}