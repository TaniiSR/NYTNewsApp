package com.task.news.domain

import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.domain.enums.ArticleDuration

interface IDataRepository {
    suspend fun getMostViewedArticles(
        period: ArticleDuration
    ): ApiResponse<MostViewArticleResponse>
}