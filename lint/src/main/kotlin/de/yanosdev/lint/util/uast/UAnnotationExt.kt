package de.yanosdev.lint.util.uast

import de.yanosdev.lint.util.reference.QualifiedNameReference
import org.jetbrains.uast.UAnnotation

internal fun UAnnotation.isComposableAnnotation() =
    qualifiedName == QualifiedNameReference.Composable

internal fun <T> UAnnotation.get(value: String) = findAttributeValue(value)?.evaluate() as T