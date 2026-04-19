@file:YDRevisionIn(implementedAt = "${YEAR}-${MONTH}-${DAY}", revisionAfterInDays = 365)

#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}

#end
import de.yanosdev.annotation.YDRevisionIn

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner

#parse("File Header.java")

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/CodeStyleReadMe.md]
 */
class ${NAME}CodeStyleDetector : Detector(), SourceCodeScanner {

    companion object {
        val issue: Issue = Issue.create( /** copy this to `add(${NAME}CodeStyleDetector.issue)` [de.yanosdev.lint.techdebt.techDebtIssues] */
            id = "${NAME}",
            briefDescription = "CodeStyle: ${NAME.replaceAll('([A-Z])', ' $1').trim()}",
            explanation =
            /** TODO: Add a detailed explanation here */
            ,
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                ${NAME}CodeStyleDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
   }
}