@file:YDRevisionIn(implementedAt = "2026-04-16", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.util.findActivity

@Composable
fun YDStatusBarColorManager(statusBarColor: Color) {
    val view = LocalView.current
    val colorScheme = colorScheme

    DisposableEffect(key1 = view, key2 = YDTheme.colorScheme) {
        val insetsController = WindowCompat.getInsetsController(
            view.context.findActivity().window,
            view
        )
        insetsController.isAppearanceLightStatusBars = statusBarColor.luminance() > 0.5
        onDispose {
            insetsController.isAppearanceLightStatusBars = colorScheme.surface.luminance() > 0.5
        }
    }
}