package com.example.android_mymedia.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_mymedia.home.data.PlayListModel
import com.example.android_mymedia.home.repository.HomeRepository
import com.example.android_mymedia.home.repository.HomeRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _categoryList: MutableLiveData<List<PlayListModel>> = MutableLiveData()
    val categoryList: LiveData<List<PlayListModel>> get() = _categoryList

    private val _pageToken: MutableLiveData<String> = MutableLiveData()
    val pageToken: LiveData<String> get() = _pageToken

    init {
        setPopularList()
    }

    private fun setPopularList() { //이걸 주석하고 디버깅을 하면 홈 화면 API 사
        viewModelScope.launch {
            val list = repository.getPopularVideo().first
            val nextToken = repository.getPopularVideo().second
            var currentList = categoryList.value.orEmpty().toMutableList()

            currentList = list.toMutableList()

            _pageToken.value = nextToken
            _categoryList.value = currentList
        }
    }
}


class HomeViewModelFactory(

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                HomeRepositoryImpl()
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}