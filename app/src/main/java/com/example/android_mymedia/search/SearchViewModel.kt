package com.example.android_mymedia.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.home.data.model.PlayListModel
import com.example.android_mymedia.retrofit.RetrofitClient
import com.example.android_mymedia.search.searchdata.SearchListModel
import com.example.android_mymedia.search.searchrepository.SearchRepository
import com.example.android_mymedia.search.searchrepository.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {



    private val _searchsList: MutableLiveData<List<SearchListModel>?> = MutableLiveData()
    val searchList: MutableLiveData<List<SearchListModel>?> get() = _searchsList

    private val _categoryList: MutableLiveData<List<ButtonModel>> = MutableLiveData()
    val categoryList: LiveData<List<ButtonModel>> get() = _categoryList
    private var currentQuery: String = ""

    init {
        setCategory()
    }

    private fun setCategory() {
        viewModelScope.launch {
            val currentList = categoryList.value.orEmpty().toMutableList()
            val responseList = repository.getCategory()

            currentList.addAll(responseList)

            _categoryList.value = currentList
        }

    }

    fun searchWithQuery(query: String) {
        currentQuery = query
        viewModelScope.launch {
            val list = repository.getSearch(currentQuery)
            _searchsList.value = list
        }
    }


    fun searchWithCategory(item: ButtonModel) {
        viewModelScope.launch {
            _searchsList.value = null
            val responseList = repository.getSearchWithCategory(currentQuery, item.category)
            val currentList = searchList.value.orEmpty().toMutableList()
            if (responseList != null) {
                currentList.addAll(responseList)
            }
            _searchsList.value = currentList
        }
    }

}


class SearchViewModelFactory(

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                SearchRepositoryImpl(RetrofitClient)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}

