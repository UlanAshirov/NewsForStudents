package com.ulan.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ulan.newsapp.data.model.Article
import com.ulan.newsapp.databinding.ItemNewsBinding

class HomeAdapter
    (private val onItemClick: (model: Article) -> Unit)
    : ListAdapter<Article, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {

    class HomeViewHolder(private val binding: ItemNewsBinding) : ViewHolder(binding.root) {
        fun onBind(item: Article?) {
            Glide.with(binding.root).load(item?.urlToImage).centerCrop().into(binding.itemImgNews)
            binding.itemTvAuthor.text = item?.author
            binding.itemTvDate.text = item?.publishedAt
            binding.itemTvTitle.text = item?.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            val model = getItem(position)
            onItemClick(model)
        }
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
