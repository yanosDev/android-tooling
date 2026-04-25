@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.CancellableContinuation

/**
 * Data carried by a queued snackbar.
 *
 * Call [performAction] when the user taps the action button, or [dismiss] to close without action.
 * Only the first call to either function has any effect.
 */
interface YDSnackbarData {
    val actionLabel: String?
    val duration: YDSnackbarDuration
    val message: String
    val withDismissAction: Boolean

    fun dismiss()
    fun performAction()
}

internal class YDSnackbarDataImpl(
    override val actionLabel: String?,
    override val duration: YDSnackbarDuration,
    override val message: String,
    override val withDismissAction: Boolean,
    private val continuation: CancellableContinuation<YDSnackbarResult>,
) : YDSnackbarData {

    override fun performAction() {
        if (continuation.isActive) continuation.resume(
            value = YDSnackbarResult.ActionPerformed,
            onCancellation = { _, _, _ -> }
        )
    }

    override fun dismiss() {
        if (continuation.isActive) continuation.resume(
            value = YDSnackbarResult.Dismissed,
            onCancellation = { _, _, _ -> }
        )
    }
}
