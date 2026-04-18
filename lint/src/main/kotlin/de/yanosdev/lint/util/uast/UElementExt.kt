package de.yanosdev.lint.util.uast

import org.jetbrains.uast.UElement
import org.jetbrains.uast.getContainingUFile


internal val UElement?.isYDCode: Boolean
    get() = this?.getContainingUFile()?.packageName?.contains("de.yanosdev") == true