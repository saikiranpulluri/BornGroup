package com.saikiran.practiseapp.di

import android.content.Context
import androidx.room.Room
import com.saikiran.practiseapp.room.AppDatabase
import com.saikiran.practiseapp.room.GitRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun providesGitRepoDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideGitRepoDao(appDatabase: AppDatabase): GitRepoDao {
        return appDatabase.gitRepoDao()
    }
}