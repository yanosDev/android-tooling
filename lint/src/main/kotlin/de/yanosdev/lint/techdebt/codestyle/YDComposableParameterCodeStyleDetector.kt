package de.yanosdev.lint.techdebt.codestyle

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.isReceiver
import com.intellij.psi.PsiParameter
import de.yanosdev.lint.util.reference.ClassNameReference
import de.yanosdev.lint.util.uast.isComposable
import de.yanosdev.lint.util.uast.isFunctionType
import de.yanosdev.lint.util.uast.isOfType
import de.yanosdev.lint.util.uast.isOptional
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.toUElement

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/CodeStyleReadMe.md]
 */
class YDComposableParameterCodeStyleDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> = listOf(UMethod::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitMethod(node: UMethod) {
                if (!node.isComposable || node.parameterList.isEmpty) return

                val params = mapParams(node = node)
                val hasModifierIssue = checkModifier(params = params, node = node)
                val hasContentIssue = checkContentLambda(params = params, node = node)
                val hasLambdaIssue = checkLambdaNames(params = params, node = node)

                if (hasModifierIssue || hasContentIssue || hasLambdaIssue) return

                val sortedParams = sortParameters(params)
                if (params != sortedParams) {
                    context.report(
                        issue = issue,
                        scope = node.toUElement(),
                        location = context.getLocation(node),
                        message = "The sorting of the parameters is wrong. Correct would be ${sortedParams.map { it.name + ": ${it.isLambda}" }}",
                        quickfixData = if (sortedParams.first().plainText != null) fix()
                            .replace()
                            .range(context.getLocation(node.parameterList))
                            .with("(\n" + sortedParams.joinToString(",\n") { "\t" + it.plainText } + "\n)")
                            .name("Fix Sort Order to ${sortedParams.map { it.name + "\n" }}")
                            .reformat(true)
                            .build() else null
                    )
                }
            }

            private fun checkLambdaNames(params: List<DetectorParams>, node: UElement): Boolean {
                var hasIssue = false
                val lambdas = params.filter { it.isLambda }
                lambdas.forEach {
                    if (
                        !it.isNavLambda
                        && !it.isActionLambda
                        && !it.isComposableLambda
                    ) {
                        context.report(
                            issue = issue,
                            scope = node,
                            location = context.getLocation(node),
                            message = "This parameter ${it.source.name} needs to be renamed or turn it into a Composable parameter",
                        )
                        hasIssue = true
                    }
                }
                return hasIssue
            }

            private fun checkContentLambda(params: List<DetectorParams>, node: UElement): Boolean {
                var hasIssue = false
                val contentLambda = params.filter { it.isContentLambda }
                if (contentLambda.isNotEmpty() && contentLambda.firstOrNull()?.index != params.lastIndex) {
                    context.report(
                        issue = issue,
                        scope = node,
                        location = context.getLocation(node),
                        message = "Content Lambda should be last parameter.",
                    )
                    hasIssue = true
                }
                return hasIssue
            }

            private fun checkModifier(params: List<DetectorParams>, node: UElement): Boolean {
                var hasIssue = false
                val modifiers = params.filter { it.isModifier }
                if (modifiers.size > 1) {
                    context.report(
                        issue = issue,
                        scope = node,
                        location = context.getLocation(node = node),
                        message = "There should be only one Modifier.",
                    )
                    hasIssue = true
                }
                modifiers.forEach {
                    if (!it.isOptional) {
                        context.report(
                            issue = issue,
                            scope = node,
                            location = context.getLocation(node),
                            message = "Modifier lambda should be optional.",
                            quickfixData = if (it.plainText != null) fix()
                                .replace()
                                .text(it.plainText)
                                .with(it.plainText + " = Modifier")
                                .name("Add default value")
                                .build() else null
                        )
                        hasIssue = true
                    }
                }

                return hasIssue
            }

            private fun sortParameters(params: List<DetectorParams>): List<DetectorParams> {
                val navParams = mutableListOf<DetectorParams>()
                val viewModelParams = mutableListOf<DetectorParams>()
                val requiredParams = mutableListOf<DetectorParams>()
                val modifierParams = mutableListOf<DetectorParams>()
                val optionalParams = mutableListOf<DetectorParams>()
                val contentLambdaParams = mutableListOf<DetectorParams>()

                params.forEach { param ->
                    when {
                        param.isNavLambda -> navParams.add(param)
                        param.isViewModel -> viewModelParams.add(param)
                        param.isContentLambda -> contentLambdaParams.add(param)
                        param.isModifier -> modifierParams.add(param)
                        param.isOptional -> optionalParams.add(param)
                        else -> requiredParams.add(param) // This includes modifiers and other optional parameters
                    }
                }

                // Sort within each section: non-lambdas first, then lambdas, then alphabetical
                val sortedNav = sortSection(navParams)
                val sortedViewModel = sortSection(viewModelParams)
                val sortedRequired = sortSection(requiredParams)
                val sortedOptional = sortSection(optionalParams)
                val sortedContent =
                    sortSection(contentLambdaParams) // Content lambda should ideally be only one and last

                return sortedNav + sortedViewModel + sortedRequired + modifierParams + sortedOptional + sortedContent
            }

            private fun sortSection(section: List<DetectorParams>): List<DetectorParams> {
                return section.sortedWith(compareBy<DetectorParams> { it.isLambda } // false (non-lambda) comes before true (lambda)
                    .thenBy { it.name } // Then sort alphabetically by plainText
                )
            }
        }
    }

    private fun mapParams(node: UMethod): List<DetectorParams> {
        return node.uastParameters.filter { !it.isReceiver() }.mapIndexed { index, parameter ->
            DetectorParams(
                isLambda = parameter.isFunctionType(),
                isViewModel = parameter.isOfType(ClassNameReference.ViewModel),
                isNavLambda = parameter.name.startsWith("navTo"),
                isActionLambda = parameter.name.startsWith("on"),
                isComposableLambda = parameter.isComposable,
                isContentLambda = parameter.name == "content",
                isOptional = parameter.isOptional,
                isModifier = parameter.isOfType(ClassNameReference.Modifier),
                index = index,
                name = parameter.name,
                plainText = parameter.text,
                source = parameter
            )
        }
    }

    companion object {
        val issue: Issue = Issue.create(
            id = "YDComposableParameterSorting",
            briefDescription = "CodeStyle: Y D Composable Parameter Sorting",
            explanation = """
                Parameters of a method annotated with @Composable should be sorted as following:
                    1. Primarily separated in 5 Sections -> navigation, viewModel, required parameters, optional parameters, content composable lambda
                    2. Within each section of those we group by lambdas and none lambdas.
                    3. Lambda's need to have a prefix of 'on' for actions, 'navTo' for navActions, 
                    4. Modifier is the first parameter if its optional in that group
                    5. Trailing Lambda must be named `content` and can only be content and nothing else
                    6. There can be only one Modifier.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.ERROR,
            implementation = Implementation(
                YDComposableParameterCodeStyleDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}

private data class DetectorParams(
    val isLambda: Boolean,
    val isViewModel: Boolean,
    val isNavLambda: Boolean,
    val isComposableLambda: Boolean,
    val isActionLambda: Boolean,
    val isContentLambda: Boolean,
    val isOptional: Boolean,
    val isModifier: Boolean,
    val index: Int,
    val name: String,
    val plainText: String?,
    val source: PsiParameter
)