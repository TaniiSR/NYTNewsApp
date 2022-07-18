package com.task.news.ui.dashaboard.detail

import androidx.lifecycle.LiveData
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.utils.base.interfaces.IBaseViewModel

interface IDetail : IBaseViewModel {
    val articleData: LiveData<Article?>
    fun setArticleData(articleData: Article?)
}