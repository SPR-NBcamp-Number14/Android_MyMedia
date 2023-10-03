package com.example.android_mymedia.my_video

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_mymedia.my_video.repository.MyRepository
import com.example.android_mymedia.my_video.repository.MyRepositoryImpl
import com.example.android_mymedia.room.VideoEntity
import kotlinx.coroutines.launch

class MyVideoViewModel(
    private val repository: MyRepository
) : ViewModel() {
    private val _liveBookMarkList: MutableLiveData<List<VideoEntity>> = MutableLiveData()
    val liveBookMarkList: LiveData<List<VideoEntity>> get() = _liveBookMarkList

    init {
        setLiveData()
    }

    fun setLiveData() {
        viewModelScope.launch {
            val videoList = repository.getLivedata()
            val currentList = liveBookMarkList.value.orEmpty().toMutableList()
            currentList.addAll(videoList)
            _liveBookMarkList.value = currentList
        }
    }

}

class MyVideoViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyVideoViewModel::class.java)) {
            return MyVideoViewModel(
                MyRepositoryImpl(context)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}