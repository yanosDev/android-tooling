@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by the picker wheel columns. Instances are created via the internal defaults
 * factory; the constructor is not part of the public API.
 *
 * @param containerColor Background of the picker surface.
 * @param selectedTextColor Text color of the centered (selected) item.
 * @param selectionIndicatorColor Color of the lines that frame the selected item row.
 * @param textColor Text color of non-selected items.
 */
@Immutable
class YDPickerColors internal constructor(
    val containerColor: Color,
    val selectedTextColor: Color,
    val selectionIndicatorColor: Color,
    val textColor: Color,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDPickerColors) return false
        if (containerColor != other.containerColor) return false
        if (selectedTextColor != other.selectedTextColor) return false
        if (selectionIndicatorColor != other.selectionIndicatorColor) return false
        if (textColor != other.textColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + selectedTextColor.hashCode()
        result = 31 * result + selectionIndicatorColor.hashCode()
        result = 31 * result + textColor.hashCode()
        return result
    }
}
