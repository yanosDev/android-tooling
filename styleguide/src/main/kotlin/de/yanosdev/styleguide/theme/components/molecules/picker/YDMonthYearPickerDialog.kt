@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDTextButton
import de.yanosdev.styleguide.theme.components.molecules.dialog.YDAlertDialog
import de.yanosdev.styleguide.theme.components.molecules.dialog.YDAlertDialogDefaults
import de.yanosdev.styleguide.theme.components.molecules.dialog.YDAlertDialogProperties
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerYearMonth
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

/**
 * Modal dialog presenting a scroll-wheel month and year picker.
 *
 * Suitable for expiry date inputs, filter ranges, and similar month-granularity selections.
 * Maintains its own internal state seeded from [initialValue].
 *
 * @param onConfirm Called with the selected [YDPickerYearMonth] when the confirm button is tapped.
 * @param onDismissRequest Called when the dialog should close.
 * @param confirmText Label for the confirm button.
 * @param dismissText Label for the optional dismiss button. Null hides it.
 * @param initialValue Month and year shown when the dialog first opens.
 * @param pickerColors Colors for the wheel columns.
 * @param title Optional dialog heading shown above the picker.
 * @param yearRange Range of selectable years. Defaults to 1900–2100.
 * @param onDismissClick Override click handler for the dismiss button. Defaults to [onDismissRequest].
 */
@Composable
fun YDMonthYearPickerDialog(
    onConfirm: (YDPickerYearMonth) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "OK",
    dismissText: String? = null,
    initialValue: YDPickerYearMonth = YDPickerYearMonth(month = 1, year = 2026),
    pickerColors: YDPickerColors = YDPickerDefaults.pickerColors(),
    properties: YDAlertDialogProperties = YDAlertDialogDefaults.Properties,
    title: String? = null,
    yearRange: IntRange = 1900..2100,
    onDismissClick: (() -> Unit)? = null,
) {
    var currentValue by remember { mutableStateOf(initialValue) }

    YDAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
        confirmButton = {
            YDPrimaryButton(
                text = confirmText,
                onClick = { onConfirm(currentValue) },
            )
        },
        dismissButton = dismissText?.let { text ->
            {
                YDTextButton(
                    text = text,
                    onClick = onDismissClick ?: onDismissRequest,
                )
            }
        },
        text = {
            YDMonthYearPicker(
                value = currentValue,
                onValueChange = { currentValue = it },
                colors = pickerColors,
                yearRange = yearRange,
            )
        },
        title = title?.let { { YDText(text = it) } },
    )
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    YDMonthYearPicker(
        value = YDPickerYearMonth(month = 4, year = 2026),
        onValueChange = {},
    )
}
