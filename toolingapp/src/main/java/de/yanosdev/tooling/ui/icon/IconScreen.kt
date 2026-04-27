@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.icon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcon
import de.yanosdev.styleguide.theme.components.atoms.icon.YDIcons
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.organisms.screen.YDDefaultScreen
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContent
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.YDTheme.typography
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.icon.model.IconAction
import de.yanosdev.tooling.ui.icon.model.IconScreenData

private val allIcons: List<Pair<String, ImageVector>> = listOf(
    "ArrowLeft" to YDIcons.ArrowLeft,
    "ArrowRight" to YDIcons.ArrowRight,
    "ArrowUp" to YDIcons.ArrowUp,
    "ArrowDown" to YDIcons.ArrowDown,
    "Home" to YDIcons.Home,
    "Close" to YDIcons.Close,
    "Menu" to YDIcons.Menu,
    "Add" to YDIcons.Add,
    "Minus" to YDIcons.Minus,
    "Check" to YDIcons.Check,
    "Search" to YDIcons.Search,
    "Edit" to YDIcons.Edit,
    "Trash" to YDIcons.Trash,
    "Copy" to YDIcons.Copy,
    "Share" to YDIcons.Share,
    "Download" to YDIcons.Download,
    "Upload" to YDIcons.Upload,
    "Refresh" to YDIcons.Refresh,
    "Filter" to YDIcons.Filter,
    "ExternalLink" to YDIcons.ExternalLink,
    "Eye" to YDIcons.Eye,
    "Info" to YDIcons.Info,
    "Warning" to YDIcons.Warning,
    "Bell" to YDIcons.Bell,
    "User" to YDIcons.User,
    "Star" to YDIcons.Star,
    "Heart" to YDIcons.Heart,
    "Lock" to YDIcons.Lock,
    "Calendar" to YDIcons.Calendar,
    "Clock" to YDIcons.Clock,
    "Settings" to YDIcons.Settings,
    "Terminal" to YDIcons.Terminal,
    "Code" to YDIcons.Code,
    "Layers" to YDIcons.Layers,
    "Bolt" to YDIcons.Bolt,
    "Database" to YDIcons.Database,
)

@Composable
internal fun IconScreen(
    navBack: @Composable () -> Unit,
    viewModel: IconViewModel,
    modifier: Modifier = Modifier,
) = YDDefaultScreen(
    modifier = modifier,
    navBack = navBack,
    title = "Icons",
) { contentPadding ->
    LaunchedEffect(viewModel.navEvents) {
        viewModel.navEvents.collect { navAction ->
            when (navAction) {
                else -> {}
            }
        }
    }

    YDUIContent(viewModel = viewModel) {
        Content(contentPadding = contentPadding)
    }
}

@Composable
internal fun YDUIContentScope<IconScreenData, IconAction>.Content(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 80.dp),
        modifier = modifier.padding(contentPadding),
        contentPadding = PaddingValues(all = spacings.medium),
        verticalArrangement = Arrangement.spacedBy(spacings.medium),
        horizontalArrangement = Arrangement.spacedBy(spacings.medium),
    ) {
        items(items = allIcons) { (name, icon) ->
            IconCell(name = name, icon = icon)
        }
    }
}

@Composable
private fun IconCell(
    name: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacings.tiny),
    ) {
        YDIcon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
        )
        YDText(
            text = name,
            style = typography.smRegular,
        )
    }
}

@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = IconScreenData()) {
    Content(contentPadding = PaddingValues())
}
