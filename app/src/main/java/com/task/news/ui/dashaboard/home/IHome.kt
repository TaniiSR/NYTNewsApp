package com.task.news.ui.dashaboard.home

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.domain.enums.ArticleDuration
import com.task.news.ui.dashaboard.home.adapter.ArticleListAdapter
import com.task.news.utils.base.interfaces.IBaseViewModel
import com.task.news.utils.base.sealed.UIEvent

interface IHome : IBaseViewModel {
    val uiState: LiveData<UIEvent>
    val articleList: LiveData<List<Article>>
    val adaptor: ArticleListAdapter
    val watcher: TextWatcher
    val duration: ArticleDuration
    val isArticleApiSuccess: LiveData<Boolean>
    fun clearList()
    fun getMostViewedArticles(periodOfTime: ArticleDuration)
}