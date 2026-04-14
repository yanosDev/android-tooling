package de.yanosdev.lint.techdebt

import de.yanosdev.lint.codestyle.DataClassParameterSortingCodeStyleDetector

internal val techDebtIssues = buildList {
    add(DataClassParameterSortingCodeStyleDetector.issue)

}