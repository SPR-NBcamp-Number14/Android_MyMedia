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

    private val _categoryList: MutableLiveData<List<SearchListModel>> = MutableLiveData()
    val categoryList: LiveData<List<SearchListModel>> get() = _categoryList
    private var currentQuery: String = ""

    //버튼 카테고리
    private val _btnList: MutableLiveData<List<ButtonModel>> = MutableLiveData()

    val btnList: LiveData<List<ButtonModel>> get() = _btnList

    private val _relatedCategories: MutableLiveData<List<SearchListModel>?> = MutableLiveData()
    val relatedCategories: MutableLiveData<List<SearchListModel>?> get() = _relatedCategories

    private val _liveCategory: MutableLiveData<String?> = MutableLiveData()
    val liveCategory: LiveData<String?> get() = _liveCategory

    init {


        _btnList.value = mutableListOf<ButtonModel>().apply {
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
            val currentList = btnList.value.orEmpty().toMutableList()
            val responseList = repository.getCategory() ?: return@launch

            currentList.addAll(responseList)

            _btnList.value = currentList
        }
    }


    fun setCategory(category: String) {
        viewModelScope.launch {

            // 수정된 API 엔드포인트 사용
            val response = repository.getSearch(query = category)

            val list = response ?: emptyList() // 검색 결과가 null이면 빈 리스트 반환
            var currentList = categoryList.value.orEmpty().toMutableList()

            currentList.clear() // 리스트를 초기화

            currentList.addAll(list) // 현재 리스트에 검색 결과 추가

            _categoryList.value = currentList
            _liveCategory.value = category
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

