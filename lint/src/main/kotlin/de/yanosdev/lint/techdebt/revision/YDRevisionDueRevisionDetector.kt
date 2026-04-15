package de.yanosdev.lint.techdebt.revision

import com.android.tools.lint.detector.api.AnnotationInfo
import com.android.tools.lint.detector.api.AnnotationUsageInfo
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import de.yanosdev.lint.util.format
import de.yanosdev.lint.util.localDate
import de.yanosdev.lint.util.reference.QualifiedNameReference
import de.yanosdev.lint.util.uast.get
import org.jetbrains.uast.UElement
import java.time.LocalDate

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/RevisionReadMe.md]
 */
class YDRevisionDueRevisionDetector : Detector(), SourceCodeScanner {

    override fun applicableAnnotations(): List<String> = listOf(QualifiedNameReference.YDRevisionIn)

    override fun visitAnnotationUsage(
        context: JavaContext,
        element: UElement,
        annotationInfo: AnnotationInfo,
        usageInfo: AnnotationUsageInfo
    ) {
        val implementedAt = annotationInfo
            .annotation
            .get<String>("implementedAt")

        val implementedAtDate = implementedAt.localDate()
        val today = LocalDate.now()

        val revisionAfterInDays = annotationInfo.annotation.get<Long>("revisionAfterInDays")

        if (implementedAtDate.plusDays(revisionAfterInDays) < today) {
            val resetDay = today.format()
            val fix = fix()
                .replace()
                .text(implementedAt)
                .with(resetDay)
                .autoFix()
                .build()

            context.report(
                issue,
                context.getLocation(element),
                "This file needs revision.",
                fix
            )
        }
    }

    companion object {
        val issue: Issue = Issue.create(
            id = "YDRevisionDue",
            briefDescription = "Revision: Y D Revision Due",
            explanation = """
                A warning will be shown for any File which needs revision.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 3,
            severity = Severity.WARNING,
            implementation = Implementation(
                YDRevisionDueRevisionDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }
}