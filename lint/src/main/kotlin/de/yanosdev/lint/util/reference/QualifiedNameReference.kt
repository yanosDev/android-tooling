package de.yanosdev.lint.util.reference

@Suppress("ConstPropertyName")
internal object QualifiedNameReference {
    const val YDRevisionIn = "de.yanosdev.annotation.${ClassNameReference.YDRevisionIn}"
    const val Composable = "androidx.compose.runtime.Composable"
}