@file:YDRevisionIn(implementedAt = "2026-04-19", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.text

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.core.net.toUri
import de.yanosdev.annotation.YDRevisionIn
import de.yanosdev.core.util.openUri
import de.yanosdev.styleguide.theme.components.atoms.text.YDTextTokens.AnnotatedStringUriAnnotationTag
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDContentColor
import de.yanosdev.styleguide.theme.foundations.semantics.LocalYDTextStyle


@Composable
fun YDText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontFamily: FontFamily? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    style: TextStyle = LocalYDTextStyle.current,
    textAlign: TextAlign = TextAlign.Unspecified,
    textDecoration: TextDecoration? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) = YDText(
    text = AnnotatedString(text),
    modifier = modifier,
    color = color,
    fontSize = fontSize,
    fontStyle = fontStyle,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    lineHeight = lineHeight,
    overflow = overflow,
    softWrap = softWrap,
    minLines = minLines,
    maxLines = maxLines,
    onTextLayout = onTextLayout,
    style = style
)

@Composable
fun YDText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontFamily: FontFamily? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    style: TextStyle = LocalYDTextStyle.current,
    textAlign: TextAlign = TextAlign.Unspecified,
    textDecoration: TextDecoration? = null,
    onClick: ((Int) -> Unit)? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalYDContentColor.current
        }
    }
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )

    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    BasicText(
        text = text,
        modifier = modifier.clickableText(layoutResult = layoutResult, text = text, onClick = onClick),
        style = mergedStyle,
        onTextLayout = {
            layoutResult = it
            onTextLayout(it)
        },
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent
    )
}

private fun Modifier.clickableText(
    layoutResult: TextLayoutResult?,
    text: AnnotatedString,
    onClick: ((Int) -> Unit)? = null
) = composed {
    if (text.getStringAnnotations(0, text.lastIndex).isNotEmpty()) {
        val context = LocalContext.current

        val clickHandler = remember(layoutResult, text, onClick) {
            onClick ?: { pos: Int ->
                text.getStringAnnotations(
                    tag = AnnotatedStringUriAnnotationTag,
                    start = pos,
                    end = pos
                ).firstOrNull()?.let { annotation ->
                    context.openUri(uri = annotation.item.toUri())
                }
            }
        }

        Modifier.pointerInput(clickHandler, layoutResult) {
            detectTapGestures { pos ->
                layoutResult?.let { layoutResult ->
                    clickHandler(layoutResult.getOffsetForPosition(pos))
                }
            }
        }
    } else Modifier
}