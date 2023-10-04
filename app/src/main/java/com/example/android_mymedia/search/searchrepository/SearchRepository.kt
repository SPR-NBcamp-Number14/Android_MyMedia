package com.example.android_mymedia.search.searchrepository

import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.search.searchdata.SearchListModel

interface SearchRepository {
    suspend fun getSearch(query: String): List<SearchListModel>?
    suspend fun getCategory(): List<ButtonModel>?
    suspend fun getSearchWithCategory(query: String,category: String): List<SearchListModel>?
}