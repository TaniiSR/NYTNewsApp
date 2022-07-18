package com.task.news.di

import com.task.news.data.remote.microservices.newsrepos.NewsRepoApi
import com.task.news.data.remote.microservices.newsrepos.NewsRepositoryRemote
import com.task.news.domain.DataRepository
import com.task.news.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideNewsRemoteRepository(newsRepositoryRemote: NewsRepositoryRemote): NewsRepoApi

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): IDataRepository
}