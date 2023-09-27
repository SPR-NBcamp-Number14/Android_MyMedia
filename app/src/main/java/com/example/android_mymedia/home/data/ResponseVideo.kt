package com.example.android_mymedia.home.data

data class ResponseVideo(
    val items: List<VideoItem>
)

data class VideoItem(
    val id: String,
    val snippet: VideoSnippet,
    val statistics: VideoStatistics
)

data class VideoSnippet(
    val publishedAt: String,
    val channelId: String,
    val title: String,
    val description: String,
    val thumbnails: VideoThumbnailType,
    val channelTitle: String,
    val tags: List<String>,
    val categoryId: String,
    val liveBroadcastContent: String,
    val localized: Localized,
    val defaultAudioLanguage: String
)

data class VideoThumbnailType(
    val default: ThumbnailDetails,
    val medium: ThumbnailDetails,
    val high: ThumbnailDetails,
)

data class ThumbnailDetails(
    val url: String
)

data class Localized(
    val title: String,
    val description: String
)

data class VideoStatistics(
    val viewCount: Long,
    val likeCount: String,
    val commentCount: Long
)
