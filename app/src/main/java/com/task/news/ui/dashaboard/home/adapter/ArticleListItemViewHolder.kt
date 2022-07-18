package com.task.news.ui.dashaboard.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.news.data.dtos.responsedtos.newsrepodtos.Article
import com.task.news.databinding.LayoutItemArticleListViewBinding
import com.task.news.utils.base.interfaces.OnItemClickListener
import com.task.news.utils.extensions.loadImage


class ArticleListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun onBind(
        data: Article,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemArticleListViewBinding -> {
                data.media?.let { media ->
                    media.firstOrNull()?.mediaMetadata?.let { metaData ->
                        metaData.firstOrNull()?.let { mediaMetaData ->
                            binding.ivImage.loadImage(mediaMetaData.url)
                        }
                    }
                }
                binding.ivImage.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(data.url)
                    startActivity(binding.root.context, intent, null)
                }
                binding.tvTitle.text = data.title
                binding.tvDescription.text = data.byline
                binding.tvDate.text = data.published_date
            }
        }
    }
}