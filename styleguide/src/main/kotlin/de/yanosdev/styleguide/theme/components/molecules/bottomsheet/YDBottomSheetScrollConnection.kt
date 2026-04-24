@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.molecules.bottomsheet.model.YDSheetState

/**
 * Scroll connection for [YDBottomSheet].
 *
 * Derived from Material 3 SheetDefaults ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection.
 */
class YDBottomSheetScrollConnection(
    private val sheetState: YDSheetState,
    private val orientation: Orientation,
    private val onFling: (Float) -> Unit,
) : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.toFloat()
        return if (delta < 0 && source == NestedScrollSource.UserInput) {
            sheetState.anchoredDraggableState.dispatchRawDelta(delta).toOffset()
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource,
    ) = if (source == NestedScrollSource.UserInput) {
        sheetState.anchoredDraggableState.dispatchRawDelta(available.toFloat()).toOffset()
    } else {
        Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val toFling = available.toFloat()
        val currentOffset = sheetState.requireOffset()
        val minAnchor = sheetState.anchoredDraggableState.anchors.minPosition()
        return if (toFling < 0 && currentOffset > minAnchor) {
            onFling(toFling)
            // since we go to the anchor with tween settling, consume all for the best UX
            available
        } else {
            Velocity.Zero
        }
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        onFling(available.toFloat())
        return available
    }

    private fun Float.toOffset() = Offset(
        x = if (orientation == Orientation.Horizontal) this else 0f,
        y = if (orientation == Orientation.Vertical) this else 0f
    )

    private fun Velocity.toFloat() = if (orientation == Orientation.Horizontal) x else y

    private fun Offset.toFloat() = if (orientation == Orientation.Horizontal) x else y
}