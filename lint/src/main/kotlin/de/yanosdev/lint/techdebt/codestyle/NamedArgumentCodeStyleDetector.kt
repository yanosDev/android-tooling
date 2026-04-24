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
import de.yanosdev.lint.util.uast.isYDCode
import de.yanosdev.lint.util.uast.namedValueArguments
import de.yanosdev.lint.util.uast.resolvedUMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See (ReadMe)[../lint/documentation/CodeStyleReadMe.md]
 */
class NamedArgumentCodeStyleDetector : Detector(), SourceCodeScanner {
    override fun getApplicableUastTypes(): List<Class<out UElement>> =
        listOf(UCallExpression::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler =
        object : UElementHandler() {
            override fun visitCallExpression(node: UCallExpression) {
                if (!node.resolvedUMethod.isYDCode) return

                val argumentsWithErrors =
                    node.namedValueArguments.filter { !it.isNamed && !(it.name == "content" && node.namedValueArguments.last() == it) }
                if (argumentsWithErrors.isNotEmpty()) {
                    val fixes = argumentsWithErrors.map { argument ->
                        fix()
                            .replace()
                            .range(range = context.getLocation(argument.element))
                            .with(newText = argument.namedArgumentValueText)
                            .name("Change to named argument only call: ${argument.namedArgumentValueText}")
                            .reformat(reformat = true)
                            .autoFix()
                            .build()
                    }

                    // Create a fix group as primary option
                    val fixGroup =
                        fix()
                            .name("Fix all named argument issues")
                            .composite(fixes)

                    fixes.forEach { fix ->
                        context.report(
                            incident = Incident(context)
                                .issue(issue)
                                .location(fix.range ?: context.getLocation(node))
                                .message("")
                                .apply { if (fix.range != null) fix(fix().alternatives(fixGroup, fix)) },
                        )
                    }
                }
            }
        }

    companion object {
        val issue: Issue = Issue.create(
            id = "NamedArgument",
            briefDescription = "CodeStyle: Named Argument",
            explanation = """
                Using named arguments make it easier to refactor/add parameter methods or classes.
                Also it will improve readability.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                NamedArgumentCodeStyleDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}