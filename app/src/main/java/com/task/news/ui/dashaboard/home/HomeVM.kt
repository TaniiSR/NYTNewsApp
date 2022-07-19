package com.task.news.ui.dashaboard.home

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.data.remote.baseclient.ApiResponse
import com.task.news.domain.IDataRepository
import com.task.news.domain.enums.ArticleDuration
import com.task.news.ui.dashaboard.home.adapter.ArticleListAdapter
import com.task.news.utils.base.BaseViewModel
import com.task.news.utils.base.sealed.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IHome {

    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    private val _articleLists: MutableLiveData<List<Article>> = MutableLiveData()
    override val articleList: LiveData<List<Article>> = _articleLists

    private val originalArticle: MutableList<Article> = mutableListOf()

    private val _isArticleApiSuccess: MutableLiveData<Boolean> = MutableLiveData()
    override val isArticleApiSuccess: LiveData<Boolean> = _isArticleApiSuccess

    override val duration: ArticleDuration = ArticleDuration.Week

    override val adaptor: ArticleListAdapter = ArticleListAdapter(mutableListOf())


    override fun getMostViewedArticles(periodOfTime: ArticleDuration) {
        launch {
            _uiState.postValue(UIEvent.Loading)
            val response = dataRepository.getMostViewedArticles(periodOfTime)
            withContext(Dispatchers.Main) {
                when (response) {
                    is ApiResponse.Success -> {
                        _articleLists.value = response.data.results ?: listOf()
                        originalArticle.addAll(response.data.results ?: listOf())
                        _isArticleApiSuccess.value = true
                        _uiState.value = UIEvent.Success
                    }
                    is ApiResponse.Error -> {
                        _isArticleApiSuccess.value = false
                        _articleLists.value = listOf()
                        _uiState.value = UIEvent.Error(response.error.message)
                    }
                }
            }
        }
    }

    override val watcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            if (!s.isNullOrEmpty()) {
                _articleLists.value = originalArticle.filter { article ->
                    article.title?.contains(s.toString()) ?: false
                }
            } else {
                clearList()
            }
        }
    }

    override fun clearList() {
        _articleLists.value = originalArticle
    }
}