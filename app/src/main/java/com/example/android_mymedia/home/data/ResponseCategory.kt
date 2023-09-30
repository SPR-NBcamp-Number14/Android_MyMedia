package com.example.android_mymedia.home.data

import com.google.gson.annotations.SerializedName

data class ResponseCategory(
    val kind: String,
    val etag: String,
    val items: List<VideoCategory>
)

data class VideoCategory(
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: CategorySnippet
)

data class CategorySnippet(
    val title: String,
    val assignable: Boolean,
    val channelId: String
)

