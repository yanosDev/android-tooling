@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.button.YDPrimaryButton
import de.yanosdev.styleguide.theme.components.molecules.button.YDTextButton
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.ProvideMergedYDTextStyle
import de.yanosdev.styleguide.theme.foundations.semantics.YDShadow
import de.yanosdev.styleguide.theme.foundations.semantics.YDTonal
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val MinWidth = 280.dp
private val MaxWidth = 560.dp

/**
 * Modal alert dialog with composable slots for icon, title, body, and action buttons.
 *
 * All content slots are optional. The dialog is sized and shaped by [shape], [shadowElevation],
 * and [tonalElevation]. Dismissal behaviour is controlled via [properties].
 *
 * @param onDismissRequest Called when the user taps the scrim or presses back.
 * @param confirmButton Primary action slot — typically a [YDPrimaryButton].
 * @param dismissButton Secondary action slot — typically a [YDTextButton]. Null hides it.
 * @param icon Optional icon centered above the title.
 * @param title Optional heading. Start-aligned unless [icon] is set, in which case it is centered.
 * @param text Optional body content. Accepts any composable, not limited to plain text.
 */
@Composable
fun YDAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDAlertDialogColors = YDAlertDialogDefaults.alertDialogColors(),
    properties: YDAlertDialogProperties = YDAlertDialogDefaults.Properties,
    shadowElevation: YDShadow = YDAlertDialogDefaults.ShadowElevation,
    shape: Shape = YDAlertDialogDefaults.Shape,
    tonalElevation: YDTonal = YDAlertDialogDefaults.TonalElevation,
    confirmButton: (@Composable () -> Unit)? = null,
    dismissButton: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = properties.dismissOnBackPress,
            dismissOnClickOutside = properties.dismissOnClickOutside,
            securePolicy = properties.securePolicy,
            usePlatformDefaultWidth = properties.usePlatformDefaultWidth,
        )
    ) {
        YDAlertDialogContent(
            modifier = modifier,
            colors = colors,
            shadowElevation = shadowElevation,
            shape = shape,
            tonalElevation = tonalElevation,
            confirmButton = confirmButton,
            dismissButton = dismissButton,
            icon = icon,
            text = text,
            title = title,
        )
    }
}

/**
 * Convenience overload of [YDAlertDialog] with plain-string title, body, and button labels.
 *
 * The confirm button is always rendered. The dismiss button appears only when [dismissText] is
 * non-null; tapping it calls [onDismissClick] if provided, otherwise [onDismissRequest].
 *
 * @param confirmText Label for the confirm button.
 * @param onConfirmClick Called when the confirm button is tapped.
 * @param onDismissRequest Called when the dialog should close.
 * @param dismissText Label for the optional dismiss button. Null omits it.
 * @param onDismissClick Override click handler for the dismiss button. Defaults to [onDismissRequest].
 */
@Composable
fun YDAlertDialog(
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    colors: YDAlertDialogColors = YDAlertDialogDefaults.alertDialogColors(),
    dismissText: String? = null,
    properties: YDAlertDialogProperties = YDAlertDialogDefaults.Properties,
    shadowElevation: YDShadow = YDAlertDialogDefaults.ShadowElevation,
    shape: Shape = YDAlertDialogDefaults.Shape,
    text: String? = null,
    title: String? = null,
    tonalElevation: YDTonal = YDAlertDialogDefaults.TonalElevation,
    icon: (@Composable () -> Unit)? = null,
    onDismissClick: () -> Unit = onDismissRequest,
) = YDAlertDialog(
    onDismissRequest = onDismissRequest,
    modifier = modifier,
    colors = colors,
    properties = properties,
    shadowElevation = shadowElevation,
    shape = shape,
    tonalElevation = tonalElevation,
    confirmButton = { YDPrimaryButton(text = confirmText, onClick = onConfirmClick) },
    dismissButton = dismissText?.let { { YDTextButton(text = it, onClick = onDismissClick) } },
    icon = icon,
    text = text?.let { { YDText(text = it) } },
    title = title?.let { { YDText(text = it) } },
)

@Composable
private fun YDAlertDialogContent(
    modifier: Modifier = Modifier,
    colors: YDAlertDialogColors = YDAlertDialogDefaults.alertDialogColors(),
    shadowElevation: YDShadow = YDAlertDialogDefaults.ShadowElevation,
    shape: Shape = YDAlertDialogDefaults.Shape,
    tonalElevation: YDTonal = YDAlertDialogDefaults.TonalElevation,
    confirmButton: (@Composable () -> Unit)? = null,
    dismissButton: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
) {
    YDSurface(
        modifier = modifier.sizeIn(minWidth = MinWidth, maxWidth = MaxWidth),
        color = colors.containerColor,
        shadowElevation = shadowElevation,
        shape = shape,
        tonalElevation = tonalElevation,
    ) {
        Column(modifier = Modifier.padding(all = spacings.huge)) {
            icon?.let {
                CompositionLocalProvider(LocalYDContentColor provides colors.iconContentColor) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = spacings.large)
                            .align(alignment = Alignment.CenterHorizontally),
                    ) {
                        it()
                    }
                }
            }
            title?.let {
                CompositionLocalProvider(LocalYDContentColor provides colors.titleContentColor) {
                    ProvideMergedYDTextStyle(value = typography.h5) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = if (text != null) spacings.small else spacings.zero)
                                .align(alignment = if (icon != null) Alignment.CenterHorizontally else Alignment.Start),
                        ) {
                            it()
                        }
                    }
                }
            }
            text?.let {
                CompositionLocalProvider(LocalYDContentColor provides colors.textContentColor) {
                    ProvideMergedYDTextStyle(value = typography.mdRegular) {
                        Box(modifier = Modifier.padding(bottom = spacings.large)) {
                            it()
                        }
                    }
                }
            }
            if (confirmButton != null || dismissButton != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    dismissButton?.invoke()
                    if (dismissButton != null && confirmButton != null) {
                        Spacer(modifier = Modifier.width(width = spacings.small))
                    }
                    confirmButton?.invoke()
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun SlotApiPreview() = YDPreview {
    YDAlertDialogContent(
        title = { YDText(text = "Delete item?") },
        text = { YDText(text = "This action cannot be undone. The item will be permanently removed.") },
        confirmButton = { YDPrimaryButton(text = "Delete", onClick = {}) },
        dismissButton = { YDTextButton(text = "Cancel", onClick = {}) },
    )
}

@PhonePreview
@Composable
private fun StringApiPreview() = YDPreview {
    YDAlertDialogContent(
        title = { YDText(text = "Discard changes?") },
        text = { YDText(text = "Your unsaved changes will be lost.") },
        confirmButton = { YDPrimaryButton(text = "Discard", onClick = {}) },
        dismissButton = { YDTextButton(text = "Keep editing", onClick = {}) },
    )
}
