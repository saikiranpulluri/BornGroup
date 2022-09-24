package com.saikiran.practiseapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class GitRepoCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var repoName: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "visibility")
    var visibility: String
)
