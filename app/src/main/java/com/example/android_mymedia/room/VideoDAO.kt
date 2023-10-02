package com.example.android_mymedia.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo (Video : VideoEntity)

    @Query("SELECT * FROM video_table")
    suspend fun getAllVideo () : List<VideoEntity>

    @Query("SELECT * FROM video_table WHERE id = :id")
    suspend fun getVideoById(id : String) : List<VideoEntity>

    @Query("DELETE FROM video_table WHERE id = :id")
    suspend fun deleteVideoById(id: String)

    @Delete
    suspend fun deleteVideo (Video: VideoEntity)
}