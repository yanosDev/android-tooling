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
import de.yanosdev.styleguide.theme.components.molecules.picker.model.YDPickerTime
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

/**
 * Modal dialog presenting a scroll-wheel time picker.
 *
 * Maintains its own internal state seeded from [initialValue]. The confirmed value is delivered
 * via [onConfirm] when the user taps the confirm button; the dialog does not auto-dismiss —
 * call [onDismissRequest] inside [onConfirm] if needed.
 *
 * @param onConfirm Called with the selected [YDPickerTime] when the confirm button is tapped.
 * @param onDismissRequest Called when the dialog should close (back press or outside tap).
 * @param confirmText Label for the confirm button.
 * @param dismissText Label for the optional dismiss button. Null hides it.
 * @param initialValue Time shown when the dialog first opens.
 * @param is24HourFormat When true shows hours 0–23 and hides the AM/PM column.
 * @param pickerColors Colors for the wheel columns.
 * @param title Optional dialog heading shown above the picker.
 * @param onDismissClick Override click handler for the dismiss button. Defaults to [onDismissRequest].
 */
@Composable
fun YDTimePickerDialog(
    onConfirm: (YDPickerTime) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "OK",
    dismissText: String? = null,
    initialValue: YDPickerTime = YDPickerTime(hour = 12, minute = 0),
    is24HourFormat: Boolean = false,
    pickerColors: YDPickerColors = YDPickerDefaults.pickerColors(),
    properties: YDAlertDialogProperties = YDAlertDialogDefaults.Properties,
    title: String? = null,
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
            YDTimePicker(
                value = currentValue,
                onValueChange = { currentValue = it },
                colors = pickerColors,
                is24HourFormat = is24HourFormat,
            )
        },
        title = title?.let { { YDText(text = it) } },
    )
}

@PhonePreview
@Composable
private fun Preview() = YDPreview {
    YDTimePicker(
        value = YDPickerTime(hour = 10, minute = 30),
        onValueChange = {},
    )
}
