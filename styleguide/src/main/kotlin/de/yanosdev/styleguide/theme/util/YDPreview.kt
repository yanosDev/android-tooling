@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)
@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package de.yanosdev.styleguide.theme.util

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.components.organisms.screen.rememberYDScreenScope
import de.yanosdev.styleguide.theme.foundations.semantics.CompactYDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.DarkTonalElevationColorSchemes
import de.yanosdev.styleguide.theme.foundations.semantics.DarkYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.ExpandedYDTypography
import de.yanosdev.styleguide.theme.foundations.semantics.LightTonalElevationColorSchemes
import de.yanosdev.styleguide.theme.foundations.semantics.LightYDColors
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Preview(
    name = PreviewLight,
    group = PreviewGroupLight,
    showBackground = true,
    backgroundColor = PreviewBackgroundLight
)
@Preview(
    name = PreviewDark,
    group = PreviewGroupDark,
    showBackground = true,
    backgroundColor = PreviewBackgroundDark,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class PhonePreview

const val PreviewLight = "Light"
const val PreviewDark = "Dark"

const val PreviewGroupLight = "Light"
const val PreviewGroupDark = "Dark"

const val PreviewBackgroundLight = 0xFFFFFFFF
const val PreviewBackgroundDark = 0xFF0B0F19

@Composable
fun YDPreview(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val isDarkTheme = isSystemInDarkTheme()

    BoxWithConstraints(modifier = modifier) {
        YDTheme(
            colorScheme = if (isDarkTheme) DarkYDColors else LightYDColors,
            tonalElevationColorSchemes = when {
                isDarkTheme -> DarkTonalElevationColorSchemes
                else -> LightTonalElevationColorSchemes
            },
            shadowElevationEnabled = !isDarkTheme,
            tonalElevationEnabled = isDarkTheme,
            typography = if (maxWidth < 600.dp) CompactYDTypography else ExpandedYDTypography,
        ) {
            CompositionLocalProvider(
                LocalYDWindowSizeClass provides WindowSizeClass.calculateFromSize(
                    DpSize(
                        maxWidth,
                        maxHeight
                    )
                ),
                LocalYDContentColor provides colorScheme.onSurface,
                content = content
            )
        }
    }
}

@Composable
fun <T, Z> YDContentPreview(
    data: T,
    modifier: Modifier = Modifier,
    content: @Composable YDUIContentScope<T, Z>.() -> Unit
) = YDPreview(modifier = modifier) {
    rememberYDScreenScope<T, Z>(data = data).content()
}