package com.task.news.ui.dashaboard.home

import com.task.news.base.BaseTestCase
import com.task.news.base.getOrAwaitValue
import com.task.news.data.dtos.responsedtos.newsrepodtos.MostViewArticleResponse
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.domain.DataRepository
import com.task.news.domain.IDataRepository
import com.task.news.domain.enums.ArticleDuration
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: HomeVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk<DataRepository>()
    }

    @Test
    fun `get article list success`() {
        //1- Mock calls
        val period = ArticleDuration.Month
        runTest {
            val response = mockk<ApiResponse.Success<MostViewArticleResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            coEvery {
                dataRepo.getMostViewedArticles(
                    period = period
                )
            } returns response
            //2-Call
            viewModel = HomeVM(dataRepo)
            viewModel.getMostViewedArticles(periodOfTime = period)

            //3-verify
            Assert.assertEquals(true, viewModel.isArticleApiSuccess.getOrAwaitValue())
        }
    }

    @Test
    fun `get article list error`() {
        //1- Mock calls
        val period = ArticleDuration.Month
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getMostViewedArticles(period = period)
            } returns response

            //2-Call
            viewModel = HomeVM(dataRepo)
            viewModel.getMostViewedArticles(periodOfTime = period)

            //3-verify
            Assert.assertEquals(false, viewModel.isArticleApiSuccess.getOrAwaitValue())
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}