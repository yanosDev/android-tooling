package de.yanosdev.lint.techdebt.revision

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import de.yanosdev.lint.util.reference.ClassNameReference
import org.jetbrains.kotlin.utils.addToStdlib.enumSetOf
import java.util.EnumSet

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/RevisionReadMe.md]
 */
class YDRevisionMissingRevisionDetector : Detector(), SourceCodeScanner {

    override fun getApplicableFiles(): EnumSet<Scope> {
        return enumSetOf(Scope.JAVA_FILE)
    }

    override fun run(context: Context) {
        val file = context.file

        if (context.getContents()?.contains("@${ClassNameReference.YDRevisionIn}") == true)
            context.report(
                issue,
                context.getLocation(file),
                "Please make sure, any code you develop has this annotation as we want to revision files which were untouched for a long time."
            )
    }

    companion object {
        val issue: Issue = Issue.create(
            id = "YDRevisionMissing",
            briefDescription = "Revision: Y D Revision Missing",
            explanation = """
                Make sure to tag any file created for coding has the @YDRevisionIn tag since.
                """.trimIndent(),
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