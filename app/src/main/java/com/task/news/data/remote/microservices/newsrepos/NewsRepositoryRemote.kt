package com.task.news.data.remote.microservices.newsrepos

import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.data.remote.baseclient.BaseRepository
import com.task.news.data.remote.baseclient.remoteconfigs.KeyConfigs
import javax.inject.Inject

class NewsRepositoryRemote @Inject constructor(
    private val service: NewsRetroService,
    private val keyConfigs: KeyConfigs
) : BaseRepository(), NewsRepoApi {

    companion object {
        const val URL_GET_MOST_VIEW_ARTICLE_LIST =
            "/svc/mostpopular/v2/mostviewed/all-sections/{period}.json"
    }

    override suspend fun getMostViewArticles(
        period: Int
    ): ApiResponse<MostViewArticleResponse> {
        return executeSafely(call = {
            service.getMostViewArticles(
                period = period,
                apiKey = keyConfigs.apiKey
            )
        })
    }
}
