@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.YDSurface
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.components.molecules.button.YDTextButton
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

private val DismissIconSize = 20.dp

/**
 * Visual representation of a snackbar message.
 *
 * This composable is stateless — it renders whatever [snackbarData] says and delegates action
 * handling back through [YDSnackbarData.performAction] / [YDSnackbarData.dismiss]. Place it
 * inside [YDSnackbarHost] which manages visibility, timing, and queuing.
 *
 * @param snackbarData Data describing the message, optional action label, and callbacks.
 * @param modifier Modifier applied to the surface container.
 * @param colors Background, text, and action colors.
 */
@Composable
fun YDSnackbar(
    snackbarData: YDSnackbarData,
    modifier: Modifier = Modifier,
    colors: YDSnackbarColors = YDSnackbarDefaults.snackbarColors(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    YDSurface(
        modifier = modifier
            .padding(horizontal = spacings.large, vertical = spacings.medium)
            .fillMaxWidth(),
        color = colors.containerColor,
        contentColor = colors.contentColor,
        shadowElevation = YDSnackbarDefaults.ShadowElevation,
        shape = YDSnackbarDefaults.Shape,
    ) {
        Row(
            modifier = Modifier.padding(
                start = spacings.large,
                end = when {
                    snackbarData.actionLabel != null -> spacings.small
                    snackbarData.withDismissAction -> spacings.small
                    else -> spacings.large
                },
                top = spacings.medium,
                bottom = spacings.medium,
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            YDText(
                text = snackbarData.message,
                style = typography.mdRegular,
                modifier = Modifier.weight(weight = 1f),
            )
            when {
                snackbarData.actionLabel != null -> {
                    Spacer(modifier = Modifier.width(width = spacings.small))
                    CompositionLocalProvider(value = LocalYDContentColor provides colors.actionContentColor) {
                        YDTextButton(
                            onClick = { snackbarData.performAction() },
                            textDecoration = null,
                        ) {
                            YDText(
                                text = snackbarData.actionLabel!!,
                                style = typography.smMediumBold,
                            )
                        }
                    }
                }

                snackbarData.withDismissAction -> {
                    Spacer(modifier = Modifier.width(width = spacings.small))
                    Box(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = rememberYDRipple(bounded = false),
                                onClick = { snackbarData.dismiss() },
                            )
                            .padding(all = spacings.small),
                    ) {
                        YDIcon(
                            imageVector = YDIcons.Close,
                            contentDescription = "Dismiss",
                            modifier = Modifier.size(size = DismissIconSize),
                        )
                    }
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun SnackbarPreview() = YDPreview {
    Column(
        modifier = Modifier.padding(all = spacings.small),
        verticalArrangement = Arrangement.spacedBy(space = spacings.small),
    ) {
        YDSnackbar(
            snackbarData = previewData(message = "File saved successfully"),
        )
        YDSnackbar(
            snackbarData = previewData(message = "Connection lost", actionLabel = "Retry"),
        )
        YDSnackbar(
            snackbarData = previewData(message = "Item deleted", withDismissAction = true),
        )
    }
}

private fun previewData(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
): YDSnackbarData = object : YDSnackbarData {
    override val actionLabel = actionLabel
    override val duration = YDSnackbarDuration.Short
    override val message = message
    override val withDismissAction = withDismissAction
    override fun dismiss() = Unit
    override fun performAction() = Unit
}
