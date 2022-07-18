package com.task.news.data.remote.microservices.newsrepos

import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsRetroService {
    @GET(NewsRepositoryRemote.URL_GET_MOST_VIEW_ARTICLE_LIST)
    suspend fun getMostViewArticles(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): Response<MostViewArticleResponse>
}