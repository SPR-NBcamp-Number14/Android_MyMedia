package com.example.android_mymedia.unit

import com.example.android_mymedia.BuildConfig
import java.text.SimpleDateFormat
import java.util.TimeZone



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


}