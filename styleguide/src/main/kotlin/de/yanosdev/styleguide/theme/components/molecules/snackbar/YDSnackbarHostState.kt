@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * State holder for [YDSnackbarHost].
 *
 * Call [showSnackbar] from a coroutine to enqueue a snackbar. Calls are serialised by a mutex —
 * only one snackbar is visible at a time, and subsequent calls suspend until the current one is
 * dismissed.
 *
 * ```kotlin
 * val snackbarState = remember { YDSnackbarHostState() }
 * val scope = rememberCoroutineScope()
 *
 * YDDefaultScreen(
 *     snackbarHost = { YDSnackbarHost(hostState = snackbarState) },
 *     ...
 * )
 *
 * // trigger:
 * scope.launch { snackbarState.showSnackbar("Saved") }
 * ```
 */
@Stable
class YDSnackbarHostState {

    private val mutex = Mutex()

    var currentSnackbarData by mutableStateOf<YDSnackbarData?>(null)
        private set

    /**
     * Shows a snackbar and suspends until it is dismissed or the action is performed.
     *
     * @param message Text displayed in the snackbar.
     * @param actionLabel Optional label for the action button. Null hides the button.
     * @param duration How long to show the snackbar before auto-dismissing.
     * @param withDismissAction Whether to show a close (×) icon when [actionLabel] is null.
     * @return [YDSnackbarResult.ActionPerformed] if the action was tapped, [YDSnackbarResult.Dismissed] otherwise.
     */
    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        duration: YDSnackbarDuration = YDSnackbarDuration.Short,
        withDismissAction: Boolean = false,
    ): YDSnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentSnackbarData = YDSnackbarDataImpl(
                    actionLabel = actionLabel,
                    duration = duration,
                    message = message,
                    withDismissAction = withDismissAction,
                    continuation = continuation,
                )
            }
        } finally {
            currentSnackbarData = null
        }
    }
}
