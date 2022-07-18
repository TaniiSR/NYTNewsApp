package com.task.news.data.remote.baseclient.interfaces


import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.data.remote.baseclient.BaseApiResponse
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}