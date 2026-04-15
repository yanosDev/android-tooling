package de.yanosdev.lint.techdebt.revision

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
 * For further information on usage See [../documentation/RevisionReadMe.md]
 */
class YDRevisionMissingRevisionDetector : Detector(), SourceCodeScanner {

    companion object {
        val issue: Issue = Issue.create(
            id = "YDRevisionMissing",
            briefDescription = "Revision: Y D Revision Missing",
            explanation = "Make sure to tag any file created for coding has the @YDRevisionIn tag since.",
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                YDRevisionMissingRevisionDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}