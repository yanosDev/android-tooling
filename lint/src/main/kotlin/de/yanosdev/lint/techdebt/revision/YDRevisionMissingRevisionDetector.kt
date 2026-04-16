package de.yanosdev.lint.techdebt.revision

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import de.yanosdev.lint.util.format
import de.yanosdev.lint.util.reference.ClassNameReference
import org.jetbrains.uast.UElement
import org.jetbrains.uast.getContainingUFile
import java.time.LocalDate

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/RevisionReadMe.md]
 */
class YDRevisionMissingRevisionDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> = listOf(UElement::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler =
        object : UElementHandler() {

            override fun visitElement(node: UElement) {
                val filePsi = context.uastFile?.javaPsi
                if (filePsi?.text?.contains("@file:${ClassNameReference.YDRevisionIn}") == false) {
                    val today = LocalDate.now().format()
                    val fix = fix()
                        .replace()
                        .text("package")
                        .with("@file:${ClassNameReference.YDRevisionIn}(implementedAt = \"$today\", revisionAfterInDays = 365)\npackage")
                        .autoFix()
                        .reformat(true)
                        .name("Add Automatic Revision Annotation")
                        .build()

                    context.report(
                        issue,
                        context.getLocation(node.getContainingUFile()),
                        "Please make sure, any code you develop has this annotation as we want to revision files which were untouched for a long time.",
                        fix
                    )
                }
            }
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