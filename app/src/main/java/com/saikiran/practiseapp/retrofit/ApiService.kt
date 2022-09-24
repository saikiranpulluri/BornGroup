package com.saikiran.practiseapp.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("repos")
    suspend fun getRepos():List<GitRepoNetworkEntity>
}