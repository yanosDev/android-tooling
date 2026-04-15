package de.yanosdev.lint.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.localDate(): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)
