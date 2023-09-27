package com.example.android_mymedia.home.data

import com.google.gson.annotations.SerializedName

data class ResponseVideo(
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("items")
    val items: List<VideoItem>
)

data class VideoItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("snippet")
    val snippet: VideoSnippet,
    @SerializedName("statistics")
    val statistics: VideoStatistics
)

data class VideoSnippet(
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnails")
    val thumbnails: VideoThumbnailType,
    @SerializedName("channelTitle")
    val channelTitle: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String,
    @SerializedName("localized")
    val localized: Localized,
    @SerializedName("defaultAudioLanguage")
    val defaultAudioLanguage: String
)

data class VideoThumbnailType(
    @SerializedName("default")
    val default: ThumbnailDetails,
    @SerializedName("medium")
    val medium: ThumbnailDetails,
    @SerializedName("high")
    val high: ThumbnailDetails,
)

data class ThumbnailDetails(
    @SerializedName("url")
    val url: String
)

data class Localized(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
)

data class VideoStatistics(
    @SerializedName("viewCount")
    val viewCount: String,
    @SerializedName("likeCount")
    val likeCount: String,
    @SerializedName("commentCount")
    val commentCount: String
)