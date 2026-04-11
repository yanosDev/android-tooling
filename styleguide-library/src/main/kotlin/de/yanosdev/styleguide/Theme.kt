package de.yanosdev.styleguide

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = YDColors.Primary,
    secondary = YDColors.Secondary,
    background = YDColors.Background,
    surface = YDColors.Surface,
    error = YDColors.Error,
)

private val DarkColors = darkColorScheme(
    primary = YDColors.PrimaryDark,
    secondary = YDColors.SecondaryDark,
    background = YDColors.BackgroundDark,
    surface = YDColors.SurfaceDark,
    error = YDColors.Error,
)

@Composable
fun YanosDevTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = YanosDevTypography,
        content = content
    )
}
