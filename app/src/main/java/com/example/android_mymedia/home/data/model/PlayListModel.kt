package com.example.android_mymedia.home.data.model

import android.os.Parcelable
import com.example.android_mymedia.room.VideoEntity
import kotlinx.android.parcel.Parcelize


data class PlayListModel(
    val id: String, //동영상 고유 id
    val videoUrl: String, //동영상 url
    val publishAt: String, // 동영상 게시일
    val defaultImgUrl: String, // 동영상 이미지 default
    val mediumImgUrl: String, // 동영상 이미지 medium
    val highImgUrl: String, //동영상 이미지 high
    val title: String, // 동영상 제목
    val channelTitle: String, // 게시 채널 제목
    val description: String, // 동영상 상세 내용
    val viewCount: String?, // 동영상 조회수
    val likeCount: String?, // 동영상 좋아요 수
    val commentCount: String?, // 동영상 댓글 수
)

fun PlayListModel.toVideoEntity(): VideoEntity {
    return VideoEntity(
        id = id,
        videoUrl = videoUrl,
        mediumImgUrl = mediumImgUrl,
        highImgUrl = highImgUrl,
        title = title,
        channelTitle = channelTitle,
        description = description
    )
}