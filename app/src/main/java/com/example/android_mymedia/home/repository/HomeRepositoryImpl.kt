package com.example.android_mymedia.home.repository

import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getPopularVideo(): List<PlayListModel> {
        val responseVideo = RetrofitClient.api.getVideo()
        val responseVideoList = responseVideo.items

        val resultList = responseVideoList.map { videoItem ->
            PlayListModel(
                id = videoItem.id,
                defaultImgUrl = videoItem.snippet.thumbnails.default.url,
                mediumImgUrl = videoItem.snippet.thumbnails.medium.url,
                highImgUrl = videoItem.snippet.thumbnails.high.url,
                title = videoItem.snippet.title,
                description = videoItem.snippet.description
            )
        }

        return resultList

    }


}