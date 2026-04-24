@file:YDRevisionIn(implementedAt = "2026-04-23", revisionAfterInDays = 365)

package de.yanosdev.tooling.ui.colors.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.styleguide.theme.components.atoms.text.YDText
import de.yanosdev.styleguide.theme.components.organisms.screen.YDUIContentScope
import de.yanosdev.styleguide.theme.foundations.semantics.YDColors
import de.yanosdev.styleguide.theme.themes.YDTheme.colorScheme
import de.yanosdev.styleguide.theme.themes.YDTheme.shapes
import de.yanosdev.styleguide.theme.themes.YDTheme.sizes
import de.yanosdev.styleguide.theme.themes.YDTheme.spacings
import de.yanosdev.styleguide.theme.themes.contentColorFor
import de.yanosdev.styleguide.theme.util.PhonePreview
import de.yanosdev.styleguide.theme.util.YDContentPreview
import de.yanosdev.tooling.ui.colors.model.ColorsAction
import de.yanosdev.tooling.ui.colors.model.ColorsScreenData

@Composable
internal fun YDUIContentScope<ColorsScreenData, ColorsAction>.ColorsBodySection(
    modifier: Modifier = Modifier,
) {
    val colorScheme = colorScheme

    val colors = remember(colorScheme) { colorScheme.mapToKeyValue }
    LazyColumn(modifier = modifier) {
        items(items = colors) { (colorName, complementColorName, colorValue) ->
            ColorRow(name = colorName, complementName = complementColorName, color = colorValue)
        }
    }
}

@Composable
private fun ColorRow(
    modifier: Modifier = Modifier,
    name: String,
    color: Color,
    complementName: String
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(vertical = spacings.small, horizontal = spacings.medium),
    horizontalArrangement = Arrangement.spacedBy(spacings.medium),
    verticalAlignment = Alignment.CenterVertically
) {
    YDText(
        modifier = Modifier.weight(1f),
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(name) }
            if (complementName.isNotEmpty()) {
                append(" with ")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(complementName) }
            }
        },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Box(
        modifier = Modifier
            .size(size = sizes.small)
            .clip(shape = shapes.medium)
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        if (complementName.isNotEmpty())
            YDText(text = "YD", maxLines = 1, color = contentColorFor(backgroundColor = color))
    }
}

private val YDColors.mapToKeyValue
    get() = listOf(
        Triple("Primary", "onPrimary", primary),
        Triple("Secondary", "onSecondary", secondary),
        Triple("Active", "onActive", active),
        Triple("Success", "onSuccess", success),
        Triple("Warning", "onWarning", warning),
        Triple("Error", "onError", error),
        Triple("Surface", "onSurface", surface),
        Triple("SurfaceContainerDefault", "onSurface", surfaceContainerDefault),
        Triple("SurfaceContainerNeutral", "onSurface", surfaceContainerNeutral),
        Triple("SurfaceContainerNeutralVariant", "onSurfaceVariant", surfaceContainerNeutralVariant),
        Triple("SurfaceContainerHighlight", "onSurface", surfaceContainerHighlight),
        Triple("SurfaceContainerHighlightVariant", "onSurfaceVariant", surfaceContainerHighlightVariant),
        Triple("SurfaceContainerPrimary", "onSurfaceContainerPrimary", surfaceContainerPrimary),
        Triple("SurfaceContainerSignal", "onSurfaceContainerSignal", surfaceContainerSignal),
        Triple("SurfaceContainerMarketing", "onSurfaceContainerMarketing", surfaceContainerMarketing),
        Triple("Scrim", "onScrim", scrim),
        Triple("Placeholder", "", placeholder),
        Triple("Line", "", line),
        Triple("ImageOverlay", "", imageOverlay),
    )


@PhonePreview
@Composable
private fun Preview() = YDContentPreview(data = ColorsScreenData()) { ColorsBodySection() }