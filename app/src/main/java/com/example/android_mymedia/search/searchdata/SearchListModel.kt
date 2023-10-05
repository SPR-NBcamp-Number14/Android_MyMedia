package com.example.android_mymedia.search.searchdata

import com.example.android_mymedia.room.VideoEntity

data class SearchListModel(
    val id: String,//고민이 필요함
    val channelTitle: String,
    val defaultImgUrl: String,
    val mediumImgUrl: String,
    val highImgUrl: String,
    val title: String,
    val description: String,
    val url: String
)


fun SearchListModel.toVideoEntity(): VideoEntity {
    return VideoEntity(
        id = id,
        videoUrl = "https://www.youtube.com/watch?v=${id}",
        mediumImgUrl = mediumImgUrl,
        highImgUrl = highImgUrl,
        title = title,
        channelTitle = channelTitle,
        description = description
    )
}
