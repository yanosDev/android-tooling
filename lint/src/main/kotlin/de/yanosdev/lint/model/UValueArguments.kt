package de.yanosdev.lint.model

import org.jetbrains.uast.UExpression

/**
 * Wrapper for [org.jetbrains.uast.UCallExpression] instances [org.jetbrains.uast.UCallExpression.valueArguments]
 *
 * @param name null if named Argument is not used else the param name
 * @param element valueArgument itself
 */
internal data class UValueArguments(
    val name: String?,
    val isNamed: Boolean,
    val element: UExpression,
) {
    private val value get() = element.sourcePsi?.text ?: ""
    val namedArgumentValueText get() = "${name?.trim()} = ${value.trim()}"
}