package com.example.haminjast

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs

object TimeUtils {

    fun timeStampToTime(timestamp: Long): String {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"))
        calendar.timeInMillis = timestamp

        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
    }

    fun timeStampToSemantics(timestamp: Long): String {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"))
        calendar.timeInMillis = timestamp
        val now = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"))

        return when {
            isWithinOneMinute(calendar, now) -> {
                "الان"
            }
            isSameDay(calendar, now) -> {
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
            }
            isYesterday(calendar, now) -> {
                "دیروز"
            }
            isWithinOneYear(calendar, now) -> {
                SimpleDateFormat("MM/dd", Locale.getDefault()).format(Date(timestamp))
            }
            else -> {
                SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date(timestamp))
            }
        }
    }

    private fun isWithinOneMinute(cal1: Calendar, cal2: Calendar): Boolean {
        return abs(cal1.timeInMillis - cal2.timeInMillis) <= 60 * 1000
    }

    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
    }

    private fun isYesterday(cal1: Calendar, cal2: Calendar): Boolean {
        cal2.add(Calendar.DAY_OF_MONTH, -1)
        return isSameDay(cal1, cal2)
    }

    private fun isWithinOneYear(cal1: Calendar, cal2: Calendar): Boolean {
        val oneYearInMillis = 365 * 24 * 60 * 60 * 1000L
        return abs(cal1.timeInMillis - cal2.timeInMillis) <= oneYearInMillis
    }
}