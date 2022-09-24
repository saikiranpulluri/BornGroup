package com.saikiran.practiseapp.retrofit

import com.google.gson.annotations.SerializedName

data class GitRepoNetworkEntity(
    @SerializedName("id")
    var id: Long,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String?,

    @SerializedName("visibility")
    var visibility: String

)
