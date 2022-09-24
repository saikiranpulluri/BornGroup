package com.saikiran.practiseapp.model

import java.io.Serializable

data class GitRepo(
    var id: Long,
    var repoName: String,
    var description: String?,
    var visibility: String
) : Serializable
