package de.yanosdev.lint.util.uast

import com.android.tools.lint.detector.api.isReceiver
import de.yanosdev.lint.model.UValueArguments
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.toUElementOfType

internal val UCallExpression.resolvedUMethod: UMethod?
    get() = resolve()?.toUElementOfType<UMethod>()

/**
 * Wraps [UCallExpression.valueArguments] which is a list of [UExpression] in our own implementation [UValueArguments]
 * There is currently no implementation providing information how a [UCallExpression.valueArguments] is used
 * and the text of [UCallExpression.valueArguments] will only contain the value, not the value name.
 *
 * Uses text search to identify named argument usages.
 *
 * returns list of [UValueArguments]
 */
internal
val UCallExpression.namedValueArguments
    get() = sourcePsi?.text?.let { callText ->
        // 1.   Remove whitespaces to avoid issues
        var parsedCallText = callText
            .replace("\\s".toRegex(), "")

        // 2.   Replace value argument text to reduce failures cause they can contain themselves
        //      '(', ',' '{'  besides the once the method text already contains
        //      This will also remove the trailing lambda being defined as Method(){ ... }
        //      which will break the logic for detecting named argument usages.
        valueArguments
            .mapNotNull { it.sourcePsi?.text?.replace("\\s".toRegex(), "") }
            .forEachIndexed { index, text ->
                parsedCallText = parsedCallText.replaceFirst(text, "$$index")
            }

        // 3.   remove possible ,) if last argument in method argument block has , at end
        //      Strip Method call to the argument block
        parsedCallText =
            parsedCallText
                .replace(",)", ")")
                .substring(
                    parsedCallText.indexOfFirst { it == '(' } + 1,
                    parsedCallText.indexOfLast { it == ')' }
                )

        // 4.   Split the parsedText by ',' and check in each block if there is a '=' or not
        //      which is basically identifying named argument usages.
        val argumentTextBlocks = parsedCallText.split(",")
        argumentTextBlocks.mapIndexed { index, textBlock ->
            val textBlockSplit = textBlock.split("=")

            UValueArguments(
                isNamed = textBlockSplit.size > 1,
                name =
                    if (textBlockSplit.size > 1)
                        textBlockSplit[0].trim()
                    else // 5. Filter out receiver type which is also added to parameters
                        resolvedUMethod?.parameterList?.parameters?.filter { !it.isReceiver() }[index]?.name,
                element = valueArguments[index]
            )
        }
    } ?: emptyList()