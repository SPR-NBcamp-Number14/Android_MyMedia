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
                        imgUrl = searchItem.snippet.thumbnails.default.url,
                        title = searchItem.snippet.title,
                        description = searchItem.snippet.description ?: "no",
                        channelTitle = searchItem.channelTitle ?: ""
                    )
                }
            } catch (e: Exception) {
                Log.e("sh", "getSearch $e")
                null

            }
        return resultList
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

    //카테고리 값만 토큰은 삭제, 카테고리 리스트 리셋해서 사용








}
