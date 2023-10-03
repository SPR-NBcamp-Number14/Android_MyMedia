package com.example.android_mymedia.my_video.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.android_mymedia.room.VideoDatabase
import com.example.android_mymedia.room.VideoEntity

class MyRepositoryImpl(
    private val context: Context,
) : MyRepository {

    private val dataBase = VideoDatabase.getInstance(context)
    private val videoDAO = dataBase?.VideoDAO()
    override fun getLivedata(): LiveData<List<VideoEntity>> {
        if (videoDAO == null) {
            throw IllegalStateException("videoDAO is not initialized")
        }
        return videoDAO.getAllVideo()
    }



}