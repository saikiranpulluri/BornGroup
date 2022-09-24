package com.saikiran.practiseapp.repository

import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.retrofit.ApiService
import com.saikiran.practiseapp.retrofit.NetworkMapper
import com.saikiran.practiseapp.room.CacheMapper
import com.saikiran.practiseapp.room.GitRepoDao
import com.saikiran.practiseapp.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val dao: GitRepoDao,
    private val apiService: ApiService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getRepos(): Flow<DataState<List<GitRepo>>> = flow {
        emit(DataState.Loading)
        delay(1000) //Just to show progress
        try {
            val gitRepos = apiService.getRepos()
            val repos = networkMapper.mapFromEntityList(gitRepos)
            for (repo in repos) {
                dao.insert(cacheMapper.mapToEntity(repo))
            }
            val cachedRepos = dao.getRepos()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedRepos)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}