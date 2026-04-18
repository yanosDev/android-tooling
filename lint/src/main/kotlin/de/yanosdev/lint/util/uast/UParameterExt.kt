package de.yanosdev.lint.util.uast

import com.intellij.psi.PsiClassType
import de.yanosdev.lint.util.reference.ClassNameReference
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UParameter
import org.jetbrains.uast.UastFacade

internal fun UParameter.isFunctionType(): Boolean {
    val types = (type as? PsiClassType)?.resolve()?.let {
        (UastFacade.convertElementWithParent(
            it,
            UClass::class.java
        ) as? UClass)?.superTypes?.mapNotNull { type -> type.resolve() }
    }.orEmpty()
    return types.any {
        val parent = UastFacade.convertElementWithParent(it, UClass::class.java) as? UClass
        parent?.name == Function::class.simpleName
    }
}

internal val UParameter.isComposable
    get() = text?.contains("@${ClassNameReference.Composable}") != false

internal val UParameter.isRequired
    get() = !text.contains("=")

internal fun UParameter.isOfType(typeName: String) = type.canonicalText.contains(typeName)