package com.task.news.domain

import com.task.news.base.BaseTestCase
import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.data.remote.microservices.newsrepos.NewsRepoApi
import com.task.news.data.remote.microservices.newsrepos.NewsRepositoryRemote
import com.task.news.domain.enums.ArticleDuration
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest : BaseTestCase() {
    // Subject under test
    lateinit var dataRepository: DataRepository

    // Use a fake UseCase to be injected into the DataRepository
    lateinit var remoteData: NewsRepoApi

    @Before
    fun setUp() {
        remoteData = mockk<NewsRepositoryRemote>()
    }

    @Test
    fun `get  most viewed article list success`() {
        //1- Mock calls
        val period = ArticleDuration.Month
        runTest {
            val response = mockk<ApiResponse.Success<MostViewArticleResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }

            coEvery {
                remoteData.getMostViewArticles(
                    period = period.duration
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData)
            dataRepository.getMostViewedArticles(period = period)

            //3-verify
            coVerify { dataRepository.getMostViewedArticles(period = period) }

        }
    }

    @Test
    fun `get  most viewed article list error`() {
        //1- Mock calls
        val period = ArticleDuration.Month
        runTest {
            val response = mockk<ApiResponse.Error>()

            coEvery {
                remoteData.getMostViewArticles(
                    period = period.duration
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData)
            dataRepository.getMostViewedArticles(period = period)

            //3-verify
            coVerify { dataRepository.getMostViewedArticles(period = period) }

        }
    }


    @After
    fun cleanUp() {
        clearAllMocks()
    }
}