package de.yanosdev.lint.techdebt.util

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.android.tools.lint.checks.infrastructure.TestMode
import com.android.tools.lint.detector.api.Issue
import de.yanosdev.lint.techdebt.util.stubs.clickableStub
import de.yanosdev.lint.techdebt.util.stubs.composableStub
import de.yanosdev.lint.techdebt.util.stubs.modifierStub
import de.yanosdev.lint.techdebt.util.stubs.paddingValuesStub
import de.yanosdev.lint.techdebt.util.stubs.serializableStub

fun ydlint(issue: Issue, vararg files: TestFile) =
    lint()
        .files(
            serializableStub(),
            modifierStub(),
            clickableStub(),
            composableStub(),
            paddingValuesStub(),
            *files
        )
        .skipTestModes(
            TestMode.JVM_OVERLOADS,
            TestMode.REORDER_ARGUMENTS,
            TestMode.WHITESPACE,
        )
        .allowDuplicates()
        .issues(issue)