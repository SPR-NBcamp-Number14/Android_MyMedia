package com.example.android_mymedia.searchrepository

import com.example.android_mymedia.retrofit.RetrofitClient
import com.example.android_mymedia.searchdata.SearchListModel
import com.example.android_mymedia.searchdata.Searched

class SearchRepositoryImpl : SearchRepository{

    override suspend fun getSearch(): List<SearchListModel> {
        val responseSearch = RetrofitClient.api.getSearch()
        val responseSearchList = responseSearch.items

        val resultList = responseSearchList.map { searchItem ->
            SearchListModel(
                id =searchItem.id.searchId,
                imgUrl = searchItem.snippet.thumbnails.default.url,
                title = searchItem.snippet.title,
                description = searchItem.snippet.description
            )
        }

        return resultList

    }
    }
