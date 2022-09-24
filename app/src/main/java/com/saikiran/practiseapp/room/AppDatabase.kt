package com.saikiran.practiseapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitRepoCacheEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gitRepoDao(): GitRepoDao

    companion object {
        val DATABASE_NAME: String = "git_repo_db"
    }
}