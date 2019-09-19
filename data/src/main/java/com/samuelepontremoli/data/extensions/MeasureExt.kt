package com.samuelepontremoli.data.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Double.format(digits: Int) = String.format("%.${digits}f", this)

fun String.formatDateDayMonthTime(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
    val dateToFormat = parser.parse(this)
    return formatter.format(dateToFormat ?: "")
}

fun String.formatDateComplete(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
    val dateToFormat = parser.parse(this)
    return formatter.format(dateToFormat ?: "")
}

fun String.toDate(): Date? {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return parser.parse(this)
}