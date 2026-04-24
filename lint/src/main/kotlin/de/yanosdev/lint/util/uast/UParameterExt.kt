package de.yanosdev.lint.util.uast

import com.intellij.psi.PsiClassType
import de.yanosdev.lint.util.reference.ClassNameReference
import de.yanosdev.lint.util.reference.QualifiedNameReference
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UParameter
import org.jetbrains.uast.UastFacade

internal fun UParameter.isFunctionType(): Boolean {
    return (type as? PsiClassType)?.resolve()?.let {
        (UastFacade.convertElementWithParent(
            it,
            UClass::class.java
        ) as? UClass)?.superTypes?.mapNotNull { type -> type.resolve() }
    }?.any {
        val parent = UastFacade.convertElementWithParent(it, UClass::class.java) as? UClass
        parent?.name == Function::class.simpleName
    } ?: type.canonicalText.contains("Function")
}

internal val UParameter.isComposable
    get() = when {
        type.annotations.any { it.qualifiedName == QualifiedNameReference.Composable } -> true
        uAnnotations.any { it.qualifiedName == QualifiedNameReference.Composable } -> true
        text != null -> text?.contains("@${ClassNameReference.Composable}") != false
        else -> findAnnotation(QualifiedNameReference.Composable) != null
    }


internal val UParameter.isOptional
    get() = when {
        sourcePsi is KtParameter -> (sourcePsi as KtParameter).hasDefaultValue()
        text != null -> text.contains("=")
        else -> true
    }

internal fun UParameter.isOfType(typeName: String) = type.canonicalText.contains(typeName)