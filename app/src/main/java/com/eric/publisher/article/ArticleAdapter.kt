package com.eric.publisher.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eric.publisher.data.Article
import com.eric.publisher.databinding.ItemMainBinding

class ArticleAdapter  : androidx.recyclerview.widget.ListAdapter<Article,
        ArticleAdapter.ArticleViewHolder>(
    DiffCallback
) {

    class ArticleViewHolder(private var binding: ItemMainBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder:ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }


}