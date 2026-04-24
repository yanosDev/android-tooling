package de.yanosdev.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Element

/**
 * Flags ImageViews that have no contentDescription – an accessibility issue.
 */
@Suppress("UnstableApiUsage")
class MissingContentDescriptionDetector : ResourceXmlDetector() {

    companion object {
        val ISSUE: Issue = Issue.create(
            id = "MissingContentDescription",
            briefDescription = "ImageView missing contentDescription",
            explanation = """
                Every `ImageView` that conveys meaning must have a `contentDescription` \
                attribute for screen-reader accessibility. Use \
                `android:contentDescription="@string/..."` or mark it decorative with \
                `android:importantForAccessibility="no"`.
            """.trimIndent(),
            category = Category.A11Y,
            priority = 7,
            severity = Severity.ERROR,
            implementation = Implementation(
                MissingContentDescriptionDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun getApplicableElements(): Collection<String> = listOf("ImageView")

    override fun visitElement(context: XmlContext, element: Element) {
        val hasDesc = element.hasAttributeNS(
            "http://schemas.android.com/apk/res/android", "contentDescription"
        )
        val isDecorative = element.getAttributeNS(
            "http://schemas.android.com/apk/res/android", "importantForAccessibility"
        ) == "no"

        if (!hasDesc && !isDecorative) {
            context.report(
                issue = ISSUE,
                scope = element,
                location = context.getNameLocation(element),
                message = "`ImageView` is missing `android:contentDescription`"
            )
        }
    }
}
