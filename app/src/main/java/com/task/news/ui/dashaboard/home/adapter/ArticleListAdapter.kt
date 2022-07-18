package com.task.news.ui.dashaboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.task.news.R
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.databinding.LayoutItemArticleListViewBinding
import com.task.news.utils.base.BaseRecyclerAdapter

class ArticleListAdapter(
    private val list: MutableList<Article>,
) : BaseRecyclerAdapter<Article, ArticleListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): ArticleListItemViewHolder {
        return ArticleListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_article_list_view
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemArticleListViewBinding.inflate(layoutInflater, viewGroup, false)
    }

    fun updateArticleListItems(articleList: List<Article>) {
        val diffCallback = ArticleDiffCallback(list, articleList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        this.setList(articleList)
        diffResult.dispatchUpdatesTo(this)
    }
}