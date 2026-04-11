package de.yanosdev.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

/**
 * Registers all custom lint rules.
 * Add new Issue objects to the [issues] list as you create more detectors.
 */
class YDIssueRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val minApi: Int = 8

    override val vendor: Vendor = Vendor(
        vendorName = "YanosDev",
        feedbackUrl = "https://github.com/yanosdev/android-tooling/issues",
        contact = "yanosdev@example.com"
    )

    override val issues: List<Issue>
        get() = listOf(
            HardcodedColorDetector.ISSUE,
            MissingContentDescriptionDetector.ISSUE,
        )
}
