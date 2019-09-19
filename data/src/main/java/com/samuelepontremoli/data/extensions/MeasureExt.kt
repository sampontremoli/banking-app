package com.samuelepontremoli.data.extensions

import java.text.SimpleDateFormat
import java.util.*

const val NETWORK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun Double.format(digits: Int) = String.format("%.${digits}f", this)

fun String.formatDateDayMonthTime(): String {
    val parser = SimpleDateFormat(NETWORK_DATE_FORMAT, Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
    val dateToFormat = parser.parse(this)
    return formatter.format(dateToFormat ?: "")
}

fun String.formatDateComplete(): String {
    val parser = SimpleDateFormat(NETWORK_DATE_FORMAT, Locale.getDefault())
    val formatter = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
    val dateToFormat = parser.parse(this)
    return formatter.format(dateToFormat ?: "")
}

fun String.toDate(): Date? {
    val parser = SimpleDateFormat(NETWORK_DATE_FORMAT, Locale.getDefault())
    return parser.parse(this)
}