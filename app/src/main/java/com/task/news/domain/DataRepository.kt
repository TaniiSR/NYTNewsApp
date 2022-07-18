package com.task.news.domain

import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.data.remote.microservices.newsrepos.NewsRepoApi
import com.task.news.domain.enums.ArticleDuration
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteNewsRepository: NewsRepoApi
) : IDataRepository {

    override suspend fun getMostViewedArticles(period: ArticleDuration): ApiResponse<MostViewArticleResponse> {
        return remoteNewsRepository.getMostViewArticles(period.duration)
    }
}