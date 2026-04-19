package de.yanosdev.lint.util.uast

import com.intellij.psi.PsiClassType
import de.yanosdev.lint.util.reference.ClassNameReference
import de.yanosdev.lint.util.reference.QualifiedNameReference
import org.jetbrains.kotlin.psi.KtParameter
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
    get() = when {
        text != null -> text?.contains("@${ClassNameReference.Composable}") != false
        else -> findAnnotation(QualifiedNameReference.Composable) != null
    }


internal val UParameter.isOptional
    get() = (sourcePsi as? KtParameter)?.hasDefaultValue() ?: (text == null || text.contains("="))

internal fun UParameter.isOfType(typeName: String) = type.canonicalText.contains(typeName)