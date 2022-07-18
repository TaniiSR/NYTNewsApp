package com.task.news.ui.dashaboard.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor() : BaseViewModel(), IDetail {

    private val _articleData: MutableLiveData<Article?> = MutableLiveData()
    override val articleData: LiveData<Article?> = _articleData

    override fun setArticleData(articleData: Article?) {
        _articleData.value = articleData
    }

}