package de.yanosdev.lint.codestyle

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.isDuplicatedOverload
import com.intellij.psi.PsiParameter
import de.yanosdev.lint.util.isDataClass
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UMethod

/**
 * Iterates through all Classes and finds mismatches in sorting.
 * SortedBy: Default Value, Alphabetical
 *
 * For further information on usage See [../documentation/CodeStyleReadMe.md]
 */
class DataClassParameterSortingCodeStyleDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> {
        return listOf(UClass::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler {
        return object : UElementHandler() {
            override fun visitClass(node: UClass) {
                if (!node.isDataClass()) return

                val constructors =
                    node.methods.filter { it.isConstructor && !it.isDuplicatedOverload() }

                for (constructor in constructors) {
                    checkConstructorParameters(context, constructor)
                }
            }

            private fun checkConstructorParameters(context: JavaContext, constructor: UMethod) {
                val parameters = constructor.parameterList.parameters.toList()
                val outOfOrderParams = mutableListOf<PsiParameter>()

                for (i in 1 until parameters.size) {
                    val prevName = parameters[i - 1].name
                    val currName = parameters[i].name
                    if (currName < prevName) {
                        outOfOrderParams.add(parameters[i])
                    }
                }

                if (outOfOrderParams.isNotEmpty()) {
                    val parameterList = constructor.parameterList
                    val sortedParameters = parameters.sortedBy { it.name }
                    val sortedParamsText = sortedParameters.joinToString(", \n") { "\t" + it.text }
                    val replacementText = "(\n$sortedParamsText\n)"

                    val fix = fix()
                        .replace()
                        .range(context.getLocation(parameterList))
                        .with(replacementText)
                        .name("Fix sorting of parameters")
                        .reformat(true)
                        .build()

                    // Report on each out-of-order parameter individually
                    outOfOrderParams.forEach { param ->
                        context.report(
                            issue,
                            param,
                            context.getLocation(param),
                            "Parameter '${param.name}' is out of alphabetical order.",
                            fix
                        )
                    }
                }
            }
        }
    }

    companion object {
        val issue: Issue = Issue.create(
            id = "DataClassParameterSorting",
            briefDescription = "CodeStyle: Data Class Parameter Sorting",
            explanation = """
                For better overview, parameters should be sorted alphabetically mostly for better readability
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                DataClassParameterSortingCodeStyleDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}