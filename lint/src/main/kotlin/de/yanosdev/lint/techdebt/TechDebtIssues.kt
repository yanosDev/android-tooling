package de.yanosdev.lint.techdebt

import de.yanosdev.lint.techdebt.codestyle.DataClassParameterSortingCodeStyleDetector
import de.yanosdev.lint.techdebt.revision.YDRevisionMissingRevisionDetector

internal val techDebtIssues = buildList {
    add(DataClassParameterSortingCodeStyleDetector.issue)
    add(YDRevisionMissingRevisionDetector.issue)
}