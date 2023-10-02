package com.example.android_mymedia.my_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_mymedia.detail.DetailModel
import com.example.android_mymedia.home.repository.HomeRepositoryImpl
import com.example.android_mymedia.home.viewmdoel.HomeViewModel
import com.example.android_mymedia.retrofit.RetrofitClient

class MyVideoViewModel(

) : ViewModel() {
    private val _liveBookMarkList: MutableLiveData<List<DetailModel>> = MutableLiveData()
    val liveBookMarkList: LiveData<List<DetailModel>> get() = _liveBookMarkList

    init {
        _liveBookMarkList.value = mutableListOf<DetailModel>().apply {

            for (i in 0..4) {
                add(
                    DetailModel(
                        videoUrl = "",
                        mediumImgUrl = "",
                        highImgUrl = "",
                        title = "",
                        channelTitle = "",
                        description = "",
                        isBooked = false
                    )
                )
            }
        }
    }


}