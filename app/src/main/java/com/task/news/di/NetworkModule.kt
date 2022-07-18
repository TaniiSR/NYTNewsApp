package com.task.news.di

import com.task.news.BuildConfig
import com.task.news.data.remote.baseclient.RetroNetwork
import com.task.news.data.remote.baseclient.remoteconfigs.KeyConfigs
import com.task.news.data.remote.microservices.newsrepos.NewsRetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesKeyConfigs(): KeyConfigs =
        KeyConfigs(
            apiKey = BuildConfig.API_KEY
        )

    @Provides
    fun providesNewsRepoRetroService(): NewsRetroService =
        RetroNetwork().createService(NewsRetroService::class.java)
}