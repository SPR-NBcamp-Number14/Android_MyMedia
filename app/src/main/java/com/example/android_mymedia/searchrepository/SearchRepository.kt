package com.example.android_mymedia.searchrepository

import com.example.android_mymedia.searchdata.SearchListModel

interface SearchRepository {

    suspend fun getSearch(query: String): List<SearchListModel>
}