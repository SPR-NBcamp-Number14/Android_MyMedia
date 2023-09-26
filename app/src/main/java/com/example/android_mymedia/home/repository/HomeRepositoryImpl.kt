package com.example.android_mymedia.home.repository

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.home.data.VideoSnippet
import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getPopularVideo(): List<PlayListModel> {
        val responseVideo = RetrofitClient.api.getVideo()
        val responseVideoList = responseVideo.items

        val resultList = responseVideoList.map { videoItem ->
            PlayListModel(
                id = videoItem.id,
                imgUrl = videoItem.snippet.thumbnails.medium.url,
                title = videoItem.snippet.title,
                description =videoItem.snippet.description
            )
        }

        return resultList

    }


}