package com.task.news.ui.dashaboard.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.task.news.R
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.databinding.FragmentHomeBinding
import com.task.news.ui.dashaboard.detail.DetailFragment
import com.task.news.utils.base.BaseNavFragment
import com.task.news.utils.base.interfaces.OnItemClickListener
import com.task.news.utils.base.sealed.UIEvent
import com.task.news.utils.extensions.gone
import com.task.news.utils.extensions.observe
import com.task.news.utils.extensions.visible
import com.task.news.utils.uikit.searchview.SearchView
import com.task.news.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseNavFragment<FragmentHomeBinding, IHome>(R.layout.fragment_home),
    SearchView.OnSearchViewClickListener, ToolBarView.OnToolBarViewClickListener {

    override val viewModel: IHome by viewModels<HomeVM>()
    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setUpRecyclerView()
        mViewBinding.searchView.textWatcher = viewModel.watcher
        viewModel.getMostViewedArticles(viewModel.duration)
    }

    private fun setUpRecyclerView() {
        viewModel.adaptor.onItemClickListener = rvItemClickListener
        viewModel.adaptor.allowFullItemClickListener = true
        mViewBinding.recyclerView.adapter = viewModel.adaptor
    }

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is Article -> {
                    startDetailScreen(data)
                }
            }
        }
    }

    private fun startDetailScreen(data: Article) {
        navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf(DetailFragment::class.java.name to data)
        )
    }

    private fun setListener() {
        mViewBinding.errorView.btnRetry.setOnClickListener(this)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        mViewBinding.searchView.setOnToolBarViewClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            onCancelClicked()
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.getMostViewedArticles(viewModel.duration)
        }
    }

    override fun onEndIconClicked() {
        mViewBinding.searchView.openSearch()
        mViewBinding.tbView.visibility = View.INVISIBLE
    }

    override fun onCancelClicked() {
        mViewBinding.searchView.closeSearch()
        mViewBinding.tbView.visibility = View.VISIBLE
        viewModel.clearList()
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnRetry -> viewModel.getMostViewedArticles(viewModel.duration)
        }
    }

    private fun handleUiState(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.Loading -> setLoadingView()
            is UIEvent.Success -> setSuccessView()
            is UIEvent.Error -> setErrorView()
            is UIEvent.Message -> showToast(uiEvent.message)
        }
    }

    private fun setErrorView() {
        mViewBinding.errorView.errorView.visible()
        mViewBinding.recyclerView.gone()
        hideShimmerLoading()
    }

    private fun setSuccessView() {
        mViewBinding.recyclerView.visible()
        mViewBinding.errorView.errorView.gone()
        hideShimmerLoading()
    }

    private fun setLoadingView() {
        mViewBinding.errorView.errorView.gone()
        mViewBinding.recyclerView.gone()
        showShimmerLoading()
    }

    private fun showShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.visible()
        mViewBinding.shimmerLayout.shimmerFrameLayout.startShimmer()

    }

    private fun hideShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.gone()
        mViewBinding.shimmerLayout.shimmerFrameLayout.stopShimmer()
    }


    private fun handleArticleSuccess(isSuccess: Boolean) {
        if (!isSuccess) setErrorView()
    }

    private fun handleArticleList(articles: List<Article>) {
        viewModel.adaptor.updateArticleListItems(articles)
    }

    private fun viewModelObservers() {
        observe(viewModel.articleList, ::handleArticleList)
        observe(viewModel.isArticleApiSuccess, ::handleArticleSuccess)
        observe(viewModel.uiState, ::handleUiState)
    }
}