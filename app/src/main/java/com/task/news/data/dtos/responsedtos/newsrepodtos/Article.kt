package com.task.news.data.dtos.responsedtos.newsrepodtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("abstract")
    val abstract: String?,
    @SerializedName("adx_keywords")
    val adxKeywords: String?,
    @SerializedName("asset_id")
    val assetId: Long?,
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("eta_id")
    val etaId: Int?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("media")
    val media: List<Media>?,
    @SerializedName("nytdsection")
    val nytdsection: String?,
    @SerializedName("published_date")
    val published_date: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("subsection")
    val subsection: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable