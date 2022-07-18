package com.task.news.data.dtos.responsedtos.newsrepodtos


import com.google.gson.annotations.SerializedName
import com.task.news.data.remote.baseclient.BaseApiResponse


data class MostViewArticleResponse(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<Article>?,
    @SerializedName("status")
    val status: String?
) : BaseApiResponse()