package com.example.android_mymedia.home.data

data class PlayListModel(
    val id: String,
    val defaultImgUrl: String? = null,
    val mediumImgUrl: String? = null,
    val highImgUrl: String? = null,
    val title: String,
    val description: String,
)