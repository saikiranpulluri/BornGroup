package com.saikiran.practiseapp.repository

import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.retrofit.ApiService
import com.saikiran.practiseapp.retrofit.NetworkMapper
import com.saikiran.practiseapp.room.CacheMapper
import com.saikiran.practiseapp.room.GitRepoDao
import com.saikiran.practiseapp.util.DataState
import com.saikiran.practiseapp.util.PrefUtil
import com.saikiran.practiseapp.util.TimeUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException

class MainRepository constructor(
    private val dao: GitRepoDao,
    private val apiService: ApiService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getRepos(): Flow<DataState<List<GitRepo>>> = flow {
        emit(DataState.Loading)
        try {
            // App tries to refresh data if last synced time is more than 2 hours and purge old data after success
            // Else it will use cache data from db
            if (TimeUtils.getDiffMinutes(
                    PrefUtil.getLong(PrefUtil.SP_LAST_SYNCED_TIME),
                    System.currentTimeMillis()
                ) > 120
            ) {
                val gitRepos = apiService.getRepos()
                val repos = networkMapper.mapFromEntityList(gitRepos)
                for (repo in repos) {
                    dao.insert(cacheMapper.mapToEntity(repo))
                }
                PrefUtil.setLong(PrefUtil.SP_LAST_SYNCED_TIME, System.currentTimeMillis())
            }
            val cachedRepos = dao.getRepos()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedRepos)))
        } catch (e: Exception) {
            // When internet is not there show offline data else show error message
            if (e is UnknownHostException) {
                val cachedRepos = dao.getRepos()
                if (cachedRepos.isNotEmpty()) {
                    emit(DataState.Success(cacheMapper.mapFromEntityList(cachedRepos)))
                } else {
                    emit(DataState.Error(e))
                }
            } else {
                emit(DataState.Error(e))
            }
        }
    }
}