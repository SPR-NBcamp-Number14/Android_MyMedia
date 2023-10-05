package com.example.android_mymedia.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VideoEntity::class], version = 1)
abstract class VideoDatabase() : RoomDatabase() {
    abstract fun VideoDAO(): VideoDAO

    companion object {
        private var instance: VideoDatabase? = null

        fun getInstance(context: Context): VideoDatabase? {
            if (instance == null) {
                synchronized(VideoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideoDatabase::class.java,
                        "video-database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}