package com.example.android_mymedia.my_video.repository

import androidx.lifecycle.LiveData
import com.example.android_mymedia.room.VideoEntity

interface MyRepository {

    suspend fun getLivedata() : List<VideoEntity>
}