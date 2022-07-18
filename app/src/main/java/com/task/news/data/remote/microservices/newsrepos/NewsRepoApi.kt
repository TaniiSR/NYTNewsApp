package com.task.news.data.remote.microservices.newsrepos

import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse

interface NewsRepoApi {
    suspend fun getMostViewArticles(
        period: Int
    ): ApiResponse<MostViewArticleResponse>
}