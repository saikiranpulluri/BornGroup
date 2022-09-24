package com.saikiran.practiseapp.model

data class GitRepo(
    var id: Long,
    var repoName: String,
    var description: String?,
    var visibility: String
)
