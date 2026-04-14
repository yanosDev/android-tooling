package de.yanosdev.lint.codestyle

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/CodeStyleReadMe.md]
 */
class DataClassParameterSortingCodeStyleDetector : Detector(), SourceCodeScanner {

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