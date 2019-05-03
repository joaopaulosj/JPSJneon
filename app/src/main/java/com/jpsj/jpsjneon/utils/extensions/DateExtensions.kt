package com.jpsj.jpsjneon.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toCalendar(dateFormat: String): Calendar {
    return Calendar.getInstance().apply {
        time = this@toCalendar.dateFromString(dateFormat)
    }
}

fun String.dateFromString(dateFormat: String): Date? {
    return try {
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        sdf.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}