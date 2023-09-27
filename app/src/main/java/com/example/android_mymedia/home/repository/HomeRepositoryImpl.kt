package com.example.android_mymedia.home.repository

import android.util.Log
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getPopularVideo(): List<PlayListModel> {
        val responseVideo = RetrofitClient.api.getVideo()
        val responseVideoList = responseVideo.items
        Log.d("리스폰.tag", responseVideo.items[0].snippet.tags.toString())
        val resultList = responseVideoList.map { videoItem ->
            PlayListModel(
                id = videoItem.id,
                videoUrl = "https://www.youtube.com/watch?v=${videoItem.id}",
                publishAt = videoItem.snippet.publishedAt,
                defaultImgUrl = videoItem.snippet.thumbnails.default.url,
                mediumImgUrl = videoItem.snippet.thumbnails.medium.url,
                highImgUrl = videoItem.snippet.thumbnails.high.url,
                title = videoItem.snippet.title,
                channelTitle = videoItem.snippet.channelTitle,
                description = videoItem.snippet.description,
                viewCount = videoItem.statistics.viewCount,
                likeCount = videoItem.statistics.likeCount,
                commentCount = videoItem.statistics.commentCount
            )
        }

        return resultList

    }


}