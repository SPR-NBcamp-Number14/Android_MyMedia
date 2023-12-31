package com.example.android_mymedia.home.repository

import android.util.Log
import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.home.data.model.PlayListModel
import com.example.android_mymedia.retrofit.RetrofitClient

class HomeRepositoryImpl(
    private val client: RetrofitClient
) : HomeRepository {
    override suspend fun getPopularVideo(
        token: String?,
        category: String?
    ): Pair<List<PlayListModel>, String> {

        val responseVideo =
            client.api.getVideo(pageToken = token, videoCategoryId = category ?: "0")
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
        Log.d("카테고리", responseCategory.items.toString())
        val responseList = responseCategory.items ?: return emptyList()

        val resultList: List<ButtonModel> =
            responseList
                .filter { categoryItems ->

                    categoryItems.snippet.assignable &&
                            categoryItems.id != "19" &&
                            categoryItems.id != "27"

                }
                .map { categoryItems ->

                    ButtonModel(
                        category = categoryItems.id,
                        btnTitle = categoryItems.snippet.title ?: "오류"

                    )
                }
        // 여행하고 교육이 404 에러가 남(카테고리 19,27)

        Log.d("버튼.리스트", resultList.toString())

        return resultList
    }

}