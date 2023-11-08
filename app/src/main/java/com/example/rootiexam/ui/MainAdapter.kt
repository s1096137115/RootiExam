package com.example.rootiexam.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.rootiexam.R
import com.example.rootiexam.databinding.ItemSimpleBinding
import com.example.rootiexam.model.news.vo.ArticleVo


class MainAdapter : ListAdapter<ArticleVo, MainAdapter.SimpleViewHolder>(object : DiffUtil.ItemCallback<ArticleVo?>() {
    override fun areItemsTheSame(oldItem: ArticleVo, newItem: ArticleVo): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: ArticleVo, newItem: ArticleVo): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        return SimpleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false))
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SimpleViewHolder(itemView: View) : ViewHolder(itemView) {
        private val binding by lazy { ItemSimpleBinding.bind(itemView) }
        fun bind(articleVo: ArticleVo) {
            binding.title.text = articleVo.title

            Glide.with(itemView)
                .load(articleVo.urlToImage)
                .transform(CenterCrop(), RoundedCorners(itemView.resources.getDimensionPixelOffset(R.dimen.image_corner_radius)))
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                .into(binding.image)
        }
    }
}