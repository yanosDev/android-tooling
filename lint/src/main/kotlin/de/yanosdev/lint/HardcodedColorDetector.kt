package de.yanosdev.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Attr

/**
 * Detects hardcoded colors in XML layout files. *
 * Why: Hardcoded colors bypass the StyleGuide token system, making
 * dark-mode and rebranding support harder to maintain.
 */
@Suppress("UnstableApiUsage")
class HardcodedColorDetector : ResourceXmlDetector() {

    companion object {
        val ISSUE: Issue = Issue.create(
            id = "HardcodedColor",
            briefDescription = "Hardcoded color instead of StyleGuide token",
            explanation = """
                Using hardcoded colors (#RRGGBB) directly in layouts bypasses the \
                StyleGuide token system. Use `@color/` references from the \
                StyleGuide library instead.
            """.trimIndent(),
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                HardcodedColorDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )

        private val COLOR_REGEX = Regex("^#([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$")
    }

    override fun getApplicableAttributes(): Collection<String> = ALL

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val value = attribute.value ?: return
        if (COLOR_REGEX.matches(value)) {
            context.report(
                issue = ISSUE,
                scope = attribute,
                location = context.getValueLocation(attribute),
                message = "Hardcoded color `$value` – use a `@color/` token from the StyleGuide instead"
            )
        }
    }
}
