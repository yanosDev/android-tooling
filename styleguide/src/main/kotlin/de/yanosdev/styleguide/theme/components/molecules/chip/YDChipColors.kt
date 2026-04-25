@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.chip

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import de.yanosdev.annotation.YDRevisionIn

/**
 * Colors used by chip components. Instances are created via [YDChipDefaults]; the constructor
 * is not part of the public API.
 *
 * The `selected*` variants are applied when [YDFilterChip] is in the selected state. For
 * [YDAssistChip] and [YDInputChip] the selected colors are never used.
 */
@Immutable
class YDChipColors internal constructor(
    val borderColor: Color,
    val containerColor: Color,
    val contentColor: Color,
    val disabledBorderColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val selectedBorderColor: Color,
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
) {
    internal fun borderColor(enabled: Boolean, selected: Boolean): Color = when {
        !enabled -> disabledBorderColor
        selected -> selectedBorderColor
        else -> borderColor
    }

    internal fun containerColor(enabled: Boolean, selected: Boolean): Color = when {
        !enabled -> disabledContainerColor
        selected -> selectedContainerColor
        else -> containerColor
    }

    internal fun contentColor(enabled: Boolean, selected: Boolean): Color = when {
        !enabled -> disabledContentColor
        selected -> selectedContentColor
        else -> contentColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is YDChipColors) return false
        if (borderColor != other.borderColor) return false
        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledBorderColor != other.disabledBorderColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        if (selectedBorderColor != other.selectedBorderColor) return false
        if (selectedContainerColor != other.selectedContainerColor) return false
        if (selectedContentColor != other.selectedContentColor) return false
        return true
    }

    override fun hashCode(): Int {
        var result = borderColor.hashCode()
        result = 31 * result + containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBorderColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        result = 31 * result + selectedBorderColor.hashCode()
        result = 31 * result + selectedContainerColor.hashCode()
        result = 31 * result + selectedContentColor.hashCode()
        return result
    }
}
