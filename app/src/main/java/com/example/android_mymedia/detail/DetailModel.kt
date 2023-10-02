package com.example.android_mymedia.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val id: String,
    val videoUrl: String,
    val mediumImgUrl: String,
    val highImgUrl: String,
    val title: String,
    val channelTitle: String,
    val description: String,
    val isBooked: Boolean = false
) : Parcelable