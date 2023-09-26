package com.example.android_mymedia.home.data

data class ResponsePlayList(
    val id: String,
    val snippet: Snippet,
    val contentDetails: ContentDetails,
    val status: Status
)

data class Snippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: Key,
    val channelTitle: String,
    val videoOwnerChannelTitle: String,
    val videoOwnerChannelId: String,
    val playlistId: String,
    val position: Int,
    val resourceId: ResourceId
)

data class Key(
    val default: Thumbnail,
    val medium: Thumbnail
)

data class Thumbnail(
    val url: String
)


data class ResourceId(
    val kind: String,
    val videoId: String
)

data class ContentDetails(
    val videoId: String,
    val startAt: String,
    val endAt: String,
    val note: String,
    val videoPublishedAt: String
)

data class Status(
    val privacyStatus: String
)
