package de.yanosdev.lint.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.localDate(): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)

fun String.paramValue(param: String): String {
    val regex = Regex("$param\\s*=\\s*([^,\\s)]+)")
    val matchResult = regex.find(this.replace("\"", ""))
    return matchResult?.groups?.get(1)?.value!!
}