package com.example.android_mymedia.home.data

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
    val viewCount: Long, // 동영상 조회수
    val likeCount: String, // 동영상 좋아요 수
    val commentCount: Long, // 동영상 댓글 수

)