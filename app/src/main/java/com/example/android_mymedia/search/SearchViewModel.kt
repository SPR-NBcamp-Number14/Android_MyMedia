package com.example.android_mymedia.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_mymedia.home.data.model.ButtonModel
import com.example.android_mymedia.retrofit.RetrofitClient
import com.example.android_mymedia.search.searchdata.SearchListModel
import com.example.android_mymedia.search.searchrepository.SearchRepository
import com.example.android_mymedia.search.searchrepository.SearchRepositoryImpl
import kotlinx.coroutines.launch

class SearchViewModel( //서치 프래그먼트에 정보를 날려줌. 레포지토리로 부터! 뷰모델이 끝나면 onclear가 불려짐
    private val repository: SearchRepository
) : ViewModel() {

    //검색 결과를 저장한 mutableLiveData
    private val _searchList: MutableLiveData<List<SearchListModel>?> =
        MutableLiveData() //서치 뷰모델에서만 데이터를 변경할거야
    val searchList: LiveData<List<SearchListModel>?> get() = _searchList //수정하지 못하게만 이렇게 사용을 해준다.

    private var currentQuery: String = ""

    //선택한 카테고리를 저장할 MutableLiveData
    private val _searchCategory: MutableLiveData<List<ButtonModel>> =
        MutableLiveData()//내부 개인 데이터 변동
    val searchCategory: LiveData<List<ButtonModel>> get() = _searchCategory //외부에서 볼수 있는 참조형 변수 읽기전용

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
        viewModelScope.launch {//코루틴
            val list = repository.getSearch(currentQuery)
            _searchList.value = list
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

    fun getSearchWithCategory(category: String) {
        _searchList.value = null
        viewModelScope.launch {
            val responseList = repository.getSearchWithCategory(currentQuery, category)
            val currentList = searchList.value.orEmpty().toMutableList()//문법 적인
            if (responseList != null) {
                currentList.addAll(responseList)
            }
            _searchList.value = currentList
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

