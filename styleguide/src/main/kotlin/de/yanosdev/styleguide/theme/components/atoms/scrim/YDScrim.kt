@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.scrim


import android.content.Context
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.clearAndSetSemantics
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.util.updateNavBar
import de.yanosdev.styleguide.theme.util.updateStatusBar

@Composable
internal fun YDScrim(
    color: Color,
    visible: Boolean,
    onDismissRequest: () -> Unit,
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(),
            label = "Scrim color"
        )
        val dismissSheet = if (visible) {
            Modifier
                .pointerInput(onDismissRequest) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }
                .clearAndSetSemantics {}
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissSheet)
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
}

@Composable
fun YDSystemBarsScrim(color: Color) {
    YDStatusBarScrim(color = color)
    YDNavBarScrim(color = color)
}

@Composable
fun YDStatusBarScrim(color: Color) = YDSystemBarScrim(
    color = color,
    onEffect = { updateStatusBar(color = it) }
)

@Composable
fun YDNavBarScrim(color: Color) = YDSystemBarScrim(
    color = color,
    onEffect = { updateNavBar(color = it) },
)

@Composable
private fun YDSystemBarScrim(
    color: Color,
    onEffect: Context.(Color) -> Unit
) {
    // This check is necessary to prevent a crash in the Compose Preview,
    // where the context is not an Activity context.
    if (LocalInspectionMode.current) return

    val context = LocalContext.current
    val defaultSystemBarColor = colorScheme.surface
    val systemBarColor = color.compositeOver(background = colorScheme.surface)
    DisposableEffect(color, colorScheme) {
        context.onEffect(systemBarColor)
        onDispose { context.onEffect(defaultSystemBarColor) }
    }
}