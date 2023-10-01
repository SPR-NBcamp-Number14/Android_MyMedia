package com.example.android_mymedia.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val videoUrl: String,
    val imgUrl: String,
    val title: String,
    val channelTitle: String,
    val description: String
) : Parcelable
