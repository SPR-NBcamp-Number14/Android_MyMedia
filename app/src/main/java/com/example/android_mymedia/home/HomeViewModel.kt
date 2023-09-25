package com.example.android_mymedia.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_mymedia.home.data.PlayListModel

class HomeViewModel : ViewModel() {

    private val _shortsList: MutableLiveData<List<PlayListModel>> = MutableLiveData()
    val shortsList: LiveData<List<PlayListModel>> get() = _shortsList

    private val _categoryList: MutableLiveData<List<PlayListModel>> = MutableLiveData()
    val categoryList: LiveData<List<PlayListModel>> get() = _categoryList

    init {
        _shortsList.value = mutableListOf<PlayListModel>().apply {
            for (i in 0..4) {
                add(PlayListModel(title = "1", description = "1", imgUrl = "1", datetime = "$i"))
            }
        }
        _categoryList.value = mutableListOf<PlayListModel>().apply {
            for (i in 0..4) {
                add(PlayListModel(title = "1", description = "1", imgUrl = "1", datetime = "$i"))
            }
        }
    }


}

class HomeViewModelFactory(

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}