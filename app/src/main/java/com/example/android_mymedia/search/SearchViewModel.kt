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

    //검색 결과를 저장한 mutableLiveData
    private val _searchsList: MutableLiveData<List<SearchListModel>?> = MutableLiveData()
    val searchList: MutableLiveData<List<SearchListModel>?> get() = _searchsList

    //검색 카테고리 목록을 저장할 MutableLiveData
    private val _categoryList: MutableLiveData<List<SearchListModel>?> = MutableLiveData()
    val categoryList: LiveData<List<SearchListModel>?> get() = _categoryList
    private var currentQuery: String = ""

    // 검색 쿼리를 저장할 MutableLiveData
    private val _searchQuery: MutableLiveData<String?> = MutableLiveData()
    val searchQuery: LiveData<String?> get() = _searchQuery

    //선택한 카테고리를 저장할 MutableLiveData
    private val _searchCategory: MutableLiveData<List<ButtonModel>> = MutableLiveData()
    val searchCategory: LiveData<List<ButtonModel>> get() = _searchCategory
    init {
        _searchCategory.value = mutableListOf<ButtonModel>().apply {
            add(
                ButtonModel(
                    category = "0",
                    btnTitle = "전체"
                )
            )
        }
        setBtnSearch()
    }
    fun searchWithQuery(query: String) {
        currentQuery = query
        viewModelScope.launch {
            val list = repository.getSearch(currentQuery)
            _searchsList.value = list
        }
    }
    private fun setBtnSearch() {
        viewModelScope.launch {
            val currentList = searchCategory.value.orEmpty().toMutableList()
            val responseList = repository.getCategory() ?: return@launch
            currentList.addAll(responseList)
            _searchCategory.value = currentList
        }
    }
    fun reset() {
        _categoryList.value = null
    }

    fun setSearchQuery(query: String){
        _searchQuery.value = query
    }
    fun getSearchWithCategory(category: String){
        viewModelScope.launch {
            val query = searchQuery.value ?: ""
            val searcher = repository.getSearchWithCategory(query, category)
            val currentList = searchList.value.orEmpty().toMutableList()//문법 적인
            if(
                searcher != null
            ){
                currentList.addAll(searcher)
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

