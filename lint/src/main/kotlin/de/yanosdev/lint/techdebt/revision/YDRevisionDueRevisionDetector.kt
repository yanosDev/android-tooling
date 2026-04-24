package de.yanosdev.lint.techdebt.revision

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
import de.yanosdev.lint.util.format
import de.yanosdev.lint.util.localDate
import de.yanosdev.lint.util.paramValue
import de.yanosdev.lint.util.reference.ClassNameReference
import org.jetbrains.kotlin.incremental.createDirectory
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UFile
import org.jetbrains.uast.getContainingUFile
import org.jetbrains.uast.getIoFile
import org.jetbrains.uast.namePsiElement
import java.io.File
import java.time.LocalDate

/**
 * TODO: Add Documentation here.
 *
 * For further information on usage See [../documentation/RevisionReadMe.md]
 */
class YDRevisionDueRevisionDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>> = listOf(UFile::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler =
        object : UElementHandler() {

            override fun visitFile(node: UFile) {
                val file = context.uastFile
                file
                    ?.uAnnotations
                    ?.find { it.namePsiElement?.text == ClassNameReference.YDRevisionIn }
                    ?.run {
                        val implementedAt = file.sourcePsi.text.paramValue("implementedAt")
                        val revisionAfterInDays =
                            file.sourcePsi.text.paramValue("revisionAfterInDays").toLong()

                        val implementedAtDate = implementedAt.localDate()
                        val today = LocalDate.now()
                        if (implementedAtDate.plusDays(revisionAfterInDays) < today) {
                            val resetDay = today.format()
                            val resetFix = fix()
                                .replace()
                                .text(implementedAt)
                                .with(resetDay)
                                .name("✅ Revised File")
                                .autoFix()
                                .build()

                            val root = (context.project.dir.parentFile ?: context.project.dir)
                            val techDirectory = File(root, "automation/techdebt")
                            techDirectory.mkdirs()
                            techDirectory.createDirectory()
                            val relativePath = node.getContainingUFile()
                                ?.getIoFile()
                                ?.path
                                ?.replace(
                                    root.path,
                                    ""
                                )
                            val techDebtTicket = fix()
                                .newFile(
                                    File(
                                        techDirectory,
                                        context.file.name.replace(".kt", ".md")
                                    ),

                                    revisionMD
                                        .replace(
                                            "%name", context.file.name
                                        )
                                        .replace("%date", implementedAt)
                                        .replace("%path", relativePath!!)
                                )
                                .open()
                                .name("🎟️ File in a Ticket")
                                .build()

                            context.report(
                                incident = Incident(context)
                                    .issue(issue)
                                    .location(context.getLocation(context.uastFile))
                                    .message("This file needs revision.")
                                    .fix(fix().alternatives(resetFix, techDebtTicket))
                            )
                        }
                    }
            }

            override fun visitElement(node: UElement) {
                val file = context.uastFile
                file
                    ?.uAnnotations
                    ?.find { it.namePsiElement?.text == ClassNameReference.YDRevisionIn }
                    ?.run {
                        val implementedAt = file.sourcePsi.text.paramValue("implementedAt")
                        val revisionAfterInDays =
                            file.sourcePsi.text.paramValue("revisionAfterInDays").toLong()

                        val implementedAtDate = implementedAt.localDate()
                        val today = LocalDate.now()
                        if (implementedAtDate.plusDays(revisionAfterInDays) < today) {
                            val resetDay = today.format()
                            val resetFix = fix()
                                .replace()
                                .text(implementedAt)
                                .with(resetDay)
                                .name("✅ Revised File")
                                .autoFix()
                                .build()

                            val root = (context.project.dir.parentFile ?: context.project.dir)
                            val techDirectory = File(root, "automation/techdebt")
                            techDirectory.mkdirs()
                            techDirectory.createDirectory()
                            val relativePath = node.getContainingUFile()
                                ?.getIoFile()
                                ?.path
                                ?.replace(
                                    root.path,
                                    ""
                                )
                            val techDebtTicket = fix()
                                .newFile(
                                    File(
                                        techDirectory,
                                        context.file.name.replace(".kt", ".md")
                                    ),

                                    revisionMD
                                        .replace(
                                            "%name", context.file.name
                                        )
                                        .replace("%date", implementedAt)
                                        .replace("%path", relativePath!!)
                                )
                                .open()
                                .name("🎟️ File in a Ticket")
                                .build()

                            context.report(
                                issue,
                                context.getLocation(context.uastFile),
                                "This file needs revision.",
                                fix().alternatives(
                                    resetFix,
                                    techDebtTicket
                                )
                            )
                        }
                    }
            }
        }

    companion object {
        const val revisionMD = """
## %name Revision

---      

**Description:**

The `%name` file needs revision. The last time this file was revision was at `%date`.

**Link:**

[Local %name](..%path)

[GitHub %name](../blob/main%path)

        """
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