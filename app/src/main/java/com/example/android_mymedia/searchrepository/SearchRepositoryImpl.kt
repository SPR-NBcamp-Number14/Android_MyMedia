package com.example.android_mymedia.searchrepository

import com.example.android_mymedia.retrofit.RetrofitClient
import com.example.android_mymedia.searchdata.SearchListModel

class SearchRepositoryImpl : SearchRepository {

    override suspend fun getSearch(query: String): List<SearchListModel> {
        val responseSearch = RetrofitClient.api.getSearch(q = query) // 검색어를 전달
        val responseSearchList = responseSearch.items

        val resultList = responseSearchList.map { searchItem ->
            SearchListModel(
                id = searchItem.id.videoId,
                imgUrl = searchItem.snippet.thumbnails.default.url,
                title = searchItem.snippet.title,
                description = searchItem.snippet.description
            )
        }

        return resultList
    }
}
