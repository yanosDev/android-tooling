package de.yanosdev.lint.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format() = this.format(DateTimeFormatter.ISO_LOCAL_DATE)