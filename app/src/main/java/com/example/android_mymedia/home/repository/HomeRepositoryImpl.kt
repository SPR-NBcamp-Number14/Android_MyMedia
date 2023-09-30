package com.example.android_mymedia.home.repository

import com.example.android_mymedia.home.data.ButtonModel
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl(
    private val client: RetrofitClient
) : HomeRepository {
    override suspend fun getPopularVideo(token: String?): Pair<List<PlayListModel>, String> {

        val responseVideo = client.api.getVideo(pageToken = token)
        val nextToken = responseVideo.nextPageToken
        val responseVideoList = responseVideo.items

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

        return Pair(resultList, nextToken)
    }

    override suspend fun getCategory(): List<ButtonModel> {
        val responseCategory = client.api.getCategory()
        val responseList = responseCategory.items
        val resultList: List<ButtonModel> =
            responseList
                .filter { categoryItems ->

                    categoryItems.snippet.assignable == true

                }
                .map { categoryItems ->

                    ButtonModel(
                        category = categoryItems.id,
                        btnTitle = categoryItems.snippet.title ?: "오류"

                    )
                }

        return resultList
    }

}