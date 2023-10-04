package com.example.android_mymedia.my_video.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.android_mymedia.room.VideoDatabase
import com.example.android_mymedia.room.VideoEntity

class MyRepositoryImpl(
    private val dataBase : VideoDatabase?
) : MyRepository {


    override fun getLivedata(): LiveData<List<VideoEntity>> {
        val videoDAO = dataBase?.VideoDAO() ?: throw IllegalStateException("videoDAO is not initialized")

        return videoDAO.getAllVideo()
    }



}