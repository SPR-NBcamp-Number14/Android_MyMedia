package com.example.android_mymedia.unit

import com.example.android_mymedia.BuildConfig
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Unit {
    const val API = BuildConfig.YOUTUBE_API_KEY
    const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    fun setViewCountFormat(count: String): String {
        var resultFormat: String = ""

        val countView = count.toLongOrNull()
        if (countView != null) {
            if (countView in 0..9999) {
                resultFormat = (countView / 1000).toString() + "천"
            } else if (countView in 10000..99999999) {
                resultFormat = (countView / 10000).toString() + "만"
            } else if (countView in 100000000..999999999999) {
                resultFormat = (countView / 100000000).toString() + "억"
            }
        }

        return resultFormat
    }

    fun dateTimeFormat(inputDatetime: String): String? {
        try {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val dateTime = LocalDateTime.parse(inputDatetime, formatter)
            val min = if (dateTime.minute != 0) "${dateTime.minute}분" else ""
            val resultDatetime =
                "${dateTime.year}년" +
                        " ${dateTime.monthValue}월 " +
                        "${dateTime.dayOfMonth}일 " +
                        "${dateTime.hour}시 $min"

            return resultDatetime
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }


}