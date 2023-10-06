package com.example.android_mymedia.search.searchdata

import com.google.gson.annotations.SerializedName


data class ResponseSearch(
    val items: List<SearchItem>
)

data class SearchItem(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("id")
    val id: Videoid,
    @SerializedName("channelTitle")
    val channelTitle: String?,
    @SerializedName("snippet")
    val snippet: SearchSnippet,
)

data class Videoid(
    val videoId: String?,
)

data class SearchSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: SearchThumbnail
)

data class SearchThumbnail(
    val default: SearchThumbnails,
    val medium: SearchThumbnails,
    val high: SearchThumbnails
)

data class SearchThumbnails(
    val url: String
)


