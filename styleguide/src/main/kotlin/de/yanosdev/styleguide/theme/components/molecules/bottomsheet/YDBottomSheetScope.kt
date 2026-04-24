@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import de.yanosdev.annotation.YDRevisionIn

/**
 * Scope for [YDBottomSheet] content.
 */
interface YDBottomSheetScope : ColumnScope {
    /**
     * Animates the sheet to the dismiss state.
     *
     * @param onCompletion is only called when the sheet is dismissed successfully.
     */
    fun animateToDismiss(onCompletion: () -> Unit = {})
}

internal class YDBottomSheetScopeImpl(
    columnScope: ColumnScope,
    private val onAnimateToDismiss: (() -> Unit) -> Unit,
) : YDBottomSheetScope, ColumnScope by columnScope {
    override fun animateToDismiss(onCompletion: () -> Unit) {
        onAnimateToDismiss(onCompletion)
    }
}