@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.card

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by card components. Instances are created via [YDCardDefaults]; the constructor
 * is not part of the public API.
 *
 * The `selected*` variants are applied when a card is in the selected or toggled-on state.
 */
@Immutable
class YDCardColors internal constructor(
    val borderColor: Color,
    val containerColor: Color,
    val contentColor: Color,
    val selectedBorderColor: Color,
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDCardColors) return false
        if (borderColor != other.borderColor) return false
        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (selectedBorderColor != other.selectedBorderColor) return false
        if (selectedContainerColor != other.selectedContainerColor) return false
        if (selectedContentColor != other.selectedContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + selectedBorderColor.hashCode()
        result = 31 * result + selectedContainerColor.hashCode()
        result = 31 * result + selectedContentColor.hashCode()
        return result
    }
}
