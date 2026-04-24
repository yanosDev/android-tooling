@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model

import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import de.yanosdev.annotation.YDRevisionIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@Composable
internal fun rememberYDSheetState(
    initialValue: YDSheetValue = YDSheetValue.Hidden,
) = rememberSaveable(saver = YDSheetState.Saver()) {
    YDSheetState(initialValue = initialValue)
}

@Stable
internal class YDSheetState internal constructor(initialValue: YDSheetValue) {

    val currentValue: YDSheetValue
        get() = anchoredDraggableState.currentValue

    val settledValue: YDSheetValue
        get() = anchoredDraggableState.settledValue

    val targetValue: YDSheetValue
        get() = anchoredDraggableState.targetValue

    val isVisible: Boolean
        get() = anchoredDraggableState.settledValue != YDSheetValue.Hidden

    internal val anchoredDraggableState = AnchoredDraggableState(initialValue = initialValue)

    fun requireOffset(): Float = anchoredDraggableState.requireOffset()

    suspend fun show() {
        animateTo(targetValue = YDSheetValue.Expanded)
    }

    suspend fun hide() {
        animateTo(targetValue = YDSheetValue.Hidden)
    }

    internal suspend fun animateTo(targetValue: YDSheetValue) {
        anchoredDraggableState.animateTo(targetValue)
    }

    companion object {

        fun Saver(): Saver<YDSheetState, YDSheetValue> = Saver(
            save = { it.currentValue },
            restore = { YDSheetState(initialValue = it) }
        )
    }
}

internal fun YDSheetState.dismissedEvents(): Flow<Unit> = combine(
    snapshotFlow { currentValue },
    snapshotFlow { targetValue },
    snapshotFlow { settledValue }
) { it }.filter { states -> states.all { it == YDSheetValue.Hidden } }.map {}