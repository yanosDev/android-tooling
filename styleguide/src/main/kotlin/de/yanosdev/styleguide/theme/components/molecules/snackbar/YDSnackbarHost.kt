@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.delay

/**
 * Renders the current snackbar from [hostState] with an animated slide-in/out transition.
 *
 * Place this composable in the `snackbarHost` slot of [de.yanosdev.styleguide.theme.components.molecules.scaffold.YDScaffold]
 * or [de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen].
 *
 * ```kotlin
 * val snackbarState = remember { YDSnackbarHostState() }
 *
 * YDDefaultScreen(
 *     snackbarHost = { YDSnackbarHost(hostState = snackbarState) },
 *     ...
 * )
 * ```
 *
 * @param hostState State that controls which snackbar (if any) is currently shown.
 * @param modifier Modifier applied to the [AnimatedVisibility] wrapper.
 * @param snackbar Slot used to render each snackbar. Defaults to [YDSnackbar].
 */
@Composable
fun YDSnackbarHost(
    hostState: YDSnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (YDSnackbarData) -> Unit = { data -> YDSnackbar(snackbarData = data) },
) {
    val currentData = hostState.currentSnackbarData

    LaunchedEffect(currentData) {
        if (currentData != null) {
            val delayMs = when (currentData.duration) {
                YDSnackbarDuration.Short -> 4_000L
                YDSnackbarDuration.Long -> 10_000L
                YDSnackbarDuration.Indefinite -> return@LaunchedEffect
            }
            delay(timeMillis = delayMs)
            currentData.dismiss()
        }
    }

    AnimatedVisibility(
        visible = currentData != null,
        modifier = modifier,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
    ) {
        currentData?.let { snackbar(it) }
    }
}
