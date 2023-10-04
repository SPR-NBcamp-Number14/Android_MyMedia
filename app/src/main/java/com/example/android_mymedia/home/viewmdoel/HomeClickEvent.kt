package com.example.android_mymedia.home.viewmdoel

import com.example.android_mymedia.room.VideoEntity

sealed interface HomeClickEvent {
    data class OpenDetail(
        val item: VideoEntity
    ) : HomeClickEvent

}