package com.example.android_mymedia.search.searchrepository

import android.util.Log
import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.retrofit.RetrofitClient
import com.example.android_mymedia.search.searchdata.SearchListModel
import java.lang.Exception

class SearchRepositoryImpl(private val client: RetrofitClient) : SearchRepository {
    override suspend fun getSearch(query: String): List<SearchListModel>? {
        val responseSearch =
            RetrofitClient.api.getSearch(q = query) // 검색어를 전달
        val responseSearchList = responseSearch.items

        val resultList =
            try {
                responseSearchList.map { searchItem ->
                    SearchListModel(
                        id = searchItem.id.videoId ?: "",
                        defaultImgUrl = searchItem.snippet.thumbnails.default.url,
                        mediumImgUrl = searchItem.snippet.thumbnails.medium.url,
                        highImgUrl = searchItem.snippet.thumbnails.high.url,
                        title = searchItem.snippet.title,
                        description = searchItem.snippet.description ?: "no",
                        channelTitle = searchItem.channelTitle ?: "",
                        url = searchItem.snippet.thumbnails.default.url
                    )
                }
            } catch (e: Exception) {
                Log.e("sh", "getSearch $e")
                null

            }
        return resultList
    }

    override suspend fun getCategory(): List<ButtonModel>? {
        try {
            val responseCategory = client.api.getCategory()
            val responseList = responseCategory.items ?: return null

            val resultList: List<ButtonModel> = responseList.map { categoryItems ->
                ButtonModel(
                    category = categoryItems.id,
                    btnTitle = categoryItems.snippet.title ?: "오류"
                )
            }

            return resultList
        } catch (e: Exception) {
            Log.e("SearchRepository", "getCategory error: $e")
            return null
        }
    }

    override suspend fun getSearchWithCategory(
        query: String,
        category: String
    ): List<SearchListModel>? {
        val reponseSearch =
            RetrofitClient.api.getSearchCategory(q = query, videoCategoryId = category)
        val responseSearchList = reponseSearch.items

        val result =
            try {
                responseSearchList.map { searchItem ->
                    SearchListModel(
                        id = searchItem.id.videoId ?: "",
                        defaultImgUrl = searchItem.snippet.thumbnails.default.url,
                        mediumImgUrl = searchItem.snippet.thumbnails.medium.url,
                        highImgUrl = searchItem.snippet.thumbnails.high.url,
                        title = searchItem.snippet.title,
                        description = searchItem.snippet.description ?: "no",
                        channelTitle = searchItem.channelTitle ?: "",
                        url = searchItem.snippet.thumbnails.default.url
                    )

                }
            } catch (e: Exception) {
                Log.e("sh", "getsearch $e")
                null
            }
        return result

    }

}

