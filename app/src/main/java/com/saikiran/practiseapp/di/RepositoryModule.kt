package com.saikiran.practiseapp.di

import com.saikiran.practiseapp.repository.MainRepository
import com.saikiran.practiseapp.retrofit.ApiService
import com.saikiran.practiseapp.retrofit.NetworkMapper
import com.saikiran.practiseapp.room.CacheMapper
import com.saikiran.practiseapp.room.GitRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        dao: GitRepoDao,
        apiService: ApiService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(dao, apiService, cacheMapper, networkMapper)
    }
}