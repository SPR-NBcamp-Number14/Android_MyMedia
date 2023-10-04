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

    //버튼 카테고리
    private val _btnList: MutableLiveData<List<ButtonModel>> = MutableLiveData()

    val btnList: LiveData<List<ButtonModel>> get() = _btnList

    private val _relatedCategories: MutableLiveData<List<SearchListModel>?> = MutableLiveData()
    val relatedCategories: MutableLiveData<List<SearchListModel>?> get() = _relatedCategories

    init {


        _btnList.value = mutableListOf<ButtonModel>().apply {
            add(
                ButtonModel(
                    category = "0",
                    btnTitle = "전체"
                )
            )
        }
        setBtnList()
    }


    fun searchWithQuery(query: String) {
        currentQuery = query
        viewModelScope.launch {
            val list = repository.getSearch(currentQuery)
            _searchsList.value = list
        }
    }



    private fun setBtnList() {
        viewModelScope.launch {
            val currentList = btnList.value.orEmpty().toMutableList()
            val responseList = repository.getCategory() ?: return@launch

            currentList.addAll(responseList)

            _btnList.value = currentList
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

