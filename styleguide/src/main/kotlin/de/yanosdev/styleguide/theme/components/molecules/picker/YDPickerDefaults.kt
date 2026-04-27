@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme

@Stable
internal object YDPickerDefaults {

    val ItemHeight: Dp = 44.dp
    val VisibleItemCount: Int = 5

    val ContainerColor: Color
        @Composable get() = colorScheme.surface

    val SelectedTextColor: Color
        @Composable get() = colorScheme.primary

    val SelectionIndicatorColor: Color
        @Composable get() = colorScheme.line

    val TextColor: Color
        @Composable get() = colorScheme.onSurfaceVariant

    @Composable
    fun pickerColors(
        containerColor: Color = ContainerColor,
        selectedTextColor: Color = SelectedTextColor,
        selectionIndicatorColor: Color = SelectionIndicatorColor,
        textColor: Color = TextColor,
    ) = YDPickerColors(
        containerColor = containerColor,
        selectedTextColor = selectedTextColor,
        selectionIndicatorColor = selectionIndicatorColor,
        textColor = textColor,
    )
}
