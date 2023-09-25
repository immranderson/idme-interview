package com.tomdroid.interview.idme.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

fun String.xAsDateOrNull(): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
    try {
        val date: Date = format.parse(this)
        return date
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}

fun String.xAsSimpleDateStringOrNull(): String? {
    return this.xAsDateOrNull()?.xAsSimpleDateStringOrNull()
}