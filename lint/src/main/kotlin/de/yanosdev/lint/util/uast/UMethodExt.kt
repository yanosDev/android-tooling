package de.yanosdev.lint.util.uast

import de.yanosdev.lint.util.reference.QualifiedNameReference
import org.jetbrains.uast.UMethod

internal val UMethod?.isComposable get() = this?.uAnnotations?.any { it.qualifiedName == QualifiedNameReference.Composable } == true