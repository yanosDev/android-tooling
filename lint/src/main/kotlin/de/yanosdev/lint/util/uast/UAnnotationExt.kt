package de.yanosdev.lint.util.uast

import org.jetbrains.uast.UAnnotation


internal fun <T> UAnnotation.get(value: String) = findAttributeValue(value)?.evaluate() as T