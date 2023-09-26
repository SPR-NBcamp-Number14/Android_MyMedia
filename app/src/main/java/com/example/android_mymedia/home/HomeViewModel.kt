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

    private val _shortsList: MutableLiveData<List<PlayListModel>> = MutableLiveData()
    val shortsList: LiveData<List<PlayListModel>> get() = _shortsList

    private val _categoryList: MutableLiveData<List<PlayListModel>> = MutableLiveData()
    val categoryList: LiveData<List<PlayListModel>> get() = _categoryList

    init {
        _shortsList.value = mutableListOf<PlayListModel>().apply {
            for (i in 0..4) {
                add(PlayListModel(id = "$i", title = "1", description = "1"))
            }
        }

        setPopularList()

    }

    private fun setPopularList() {
        viewModelScope.launch {
            val list = repository.getPopularVideo()
            var currentList = categoryList.value.orEmpty().toMutableList()

            currentList = list.toMutableList()

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