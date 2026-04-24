@file:YDRevisionIn(implementedAt = "2026-04-24", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.molecules.bottomsheet


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.molecules.topbar.YDTopAppBar
import de.yanosdev.styleguide.theme.components.molecules.topbar.YDTopAppBarDefaults
import de.yanosdev.styleguide.theme.components.organisms.navigation.YDNavigationCloseIcon
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDPreview

@Composable
internal fun YDBottomSheetTopAppBar(
    navigationIcon: (@Composable () -> Unit)? = null,
    title: String?,
    windowInsets: WindowInsets = YDTopAppBarDefaults.windowInsets
) {
    YDTopAppBar(
        title = {
            title?.let {
                YDText(
                    text = title,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(spacings.small)
                )
            }
        },
        windowInsets = windowInsets,
        navigationIcon = {
            navigationIcon?.invoke()
        }
    )
}

@PhonePreview
@Composable
private fun DefaultDialogTopAppBarPreview() = YDPreview {
    YDBottomSheetTopAppBar(
        title = "Title",
        navigationIcon = { YDNavigationCloseIcon(navUp = { }) }
    )
}

@PhonePreview
@Composable
private fun DefaultDialogTopAppBarLongTitlePreview() = YDPreview {
    YDBottomSheetTopAppBar(
        title = "Lorem ipsum dolor sit amet consectetur. Vitae aliquet nec eget imperdiet. Dolor ornare amet facilisis nunc ac ante.",
        navigationIcon = { YDNavigationCloseIcon(navUp = { }) }
    )
}