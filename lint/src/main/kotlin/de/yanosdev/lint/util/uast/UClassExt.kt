package de.yanosdev.lint.util.uast

import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.uast.UClass

fun UClass.isDataClass(): Boolean = (sourceElement as? KtClass)?.isData() == true