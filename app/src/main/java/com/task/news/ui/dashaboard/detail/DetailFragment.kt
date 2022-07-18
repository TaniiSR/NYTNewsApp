package com.task.news.ui.dashaboard.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.task.news.R
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.databinding.FragmentDetailBinding
import com.task.news.utils.base.BaseNavFragment
import com.task.news.utils.extensions.loadImage
import com.task.news.utils.extensions.observe
import com.task.news.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseNavFragment<FragmentDetailBinding, IDetail>(R.layout.fragment_detail),
    ToolBarView.OnToolBarViewClickListener {

    override val viewModel: IDetail by viewModels<DetailVM>()
    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        viewModel.setArticleData(arguments?.getParcelable(DetailFragment::class.java.name))
    }

    private fun handleArticle(article: Article?) {
        article?.let { data ->
            data.media?.let { media ->
                media.firstOrNull()?.mediaMetadata?.let { metaData ->
                    metaData.firstOrNull()?.let { mediaMetaData ->
                        mViewBinding.ivImage.loadImage(mediaMetaData.url)
                    }
                }
            }
            mViewBinding.ivImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(data.url)
                ContextCompat.startActivity(requireContext(), intent, null)
            }
            mViewBinding.tvTitle.text = data.title
            mViewBinding.tvDescription.text = data.abstract
            mViewBinding.tvAuthor.text = data.byline
            mViewBinding.tvDate.text = data.published_date
        }
    }

    override fun onStartIconClicked() {
        requireActivity().onBackPressed()
    }


    private fun viewModelObservers() {
        observe(viewModel.articleData, ::handleArticle)
    }
}