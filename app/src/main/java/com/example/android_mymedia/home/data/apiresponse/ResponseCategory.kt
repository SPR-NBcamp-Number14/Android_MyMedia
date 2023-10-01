package com.example.android_mymedia.home.data.apiresponse

import com.google.gson.annotations.SerializedName

data class ResponseCategory(
    @SerializedName("items")
    val items: List<VideoCategory>
)

data class VideoCategory(
    @SerializedName("id")
    val id: String,
    @SerializedName("snippet")
    val snippet: CategorySnippet
)

data class CategorySnippet(
    @SerializedName("title")
    val title: String,
    @SerializedName("assignable")
    val assignable: Boolean
)

