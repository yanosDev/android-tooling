package de.yanosdev.lint.techdebt.codestyle

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Incident
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.isDuplicatedOverload
import com.intellij.psi.PsiParameter
import de.yanosdev.lint.util.uast.isDataClass
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
                if (parameters.any { !sizeNames.contains(it.name) }) {
                    val groupedParameters =
                        parameters.groupBy { it.text.contains("=") }

                    groupedParameters.forEach { (_, params) ->
                        for (i in 1 until params.size) {
                            val prevName = params[i - 1].name
                            val currName = params[i].name

                            val numberPrev = Regex("\\d+").find(prevName)?.value?.toLongOrNull()
                            val numberCurr = Regex("\\d+").find(currName)?.value?.toLongOrNull()
                            if (numberPrev != null && numberCurr != null) {
                                if (numberCurr < numberPrev)
                                    outOfOrderParams.add(params[i])
                            } else if (currName < prevName) {
                                outOfOrderParams.add(params[i])
                            }
                        }
                    }
                } else {
                    for (i in 1 until parameters.size) {
                        val prevName = parameters[i - 1].name
                        val currName = parameters[i].name

                        if (sizeNames.indexOf(currName) < sizeNames.indexOf(prevName))
                            outOfOrderParams.add(parameters[i])
                    }
                }

                if (outOfOrderParams.isNotEmpty()) {
                    val parameterList = constructor.parameterList
                    val sortedParameters = parameters
                        .sortedWith(compareBy<PsiParameter> { it.text.contains("=") }
                            .thenBy {
                                val number = Regex("\\d+").find(it.name)?.value?.toLongOrNull()
                                number ?: Long.MAX_VALUE // Sort numbers first, then non-numbers
                            }
                            .thenBy {
                                it.name // Fallback to alphabetical sort
                            }
                        )

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
                            incident = Incident(context)
                                .issue(issue)
                                .location(
                                    context.getLocation(constructor)

                                )
                                .message("Parameter '${param.name}' is out of alphabetical order.")
                                .fix(fix)

                        )
                    }
                }
            }
        }
    }

    private val sizeNames = listOf(
        "zero", "extraTiny", "tiny", "small", "medium", "large", "big", "huge", "extraHuge"
    )

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