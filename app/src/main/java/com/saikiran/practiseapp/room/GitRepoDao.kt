package com.saikiran.practiseapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GitRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitRepo: GitRepoCacheEntity): Long

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<GitRepoCacheEntity>
}