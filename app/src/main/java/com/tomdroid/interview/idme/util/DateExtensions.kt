package com.tomdroid.interview.idme.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

fun Date.xAsSimpleDateStringOrNull(): String? {
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy")
    try {
        val date = Date()
        return dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}