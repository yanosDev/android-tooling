@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.button.icon

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.util.rememberYDRipple
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.YDIconButtonColors
import de.yanosdev.styleguide.theme.themes.disabledColorFor
import de.yanosdev.styleguide.theme.util.LocalYDMinTouchTargetEnforcement

internal object YDIconButtonDefaults {

    val indication: Indication
        @Composable
        get() = if (LocalYDMinTouchTargetEnforcement.current) {
            rememberYDRipple(
                bounded = false,
                radius = IconButtonSize / 2
            )
        } else LocalIndication.current

    @Composable
    fun iconButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = LocalYDContentColor.current,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = disabledColorFor(contentColor),
    ) = YDIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    internal fun iconToggleButtonColors(
        checkedContainerColor: Color = Color.Transparent,
        checkedContentColor: Color = LocalYDContentColor.current,
        containerColor: Color = Color.Transparent,
        contentColor: Color = LocalYDContentColor.current,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = disabledColorFor(contentColor),
    ) = YDIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        checkedContainerColor = checkedContainerColor,
        checkedContentColor = checkedContentColor,
    )

    internal val IconButtonSize = 40.dp
}