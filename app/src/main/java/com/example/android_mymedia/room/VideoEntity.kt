package com.example.android_mymedia.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "video_table")
data class VideoEntity(
    @PrimaryKey
    val id: String, //동영상 고유 id
    val videoUrl: String, //동영상 url
    val mediumImgUrl: String,
    val highImgUrl: String, //동영상 이미지 high
    val title: String, // 동영상 제목
    val channelTitle : String,
    val description: String, // 동영상 상세 내용
) : Parcelable