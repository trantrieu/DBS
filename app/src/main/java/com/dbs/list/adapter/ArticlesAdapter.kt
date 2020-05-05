package com.dbs.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbs.databinding.ItemArticleBinding
import com.squareup.picasso.Picasso

internal class ArticlesAdapter(private var list: List<ArticleModel> = emptyList()) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    fun update(list: List<ArticleModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindViewModel(list[position])
    }

    class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViewModel(articlesModel: ArticleModel) {
            binding.articleTitle.text = articlesModel.title
            binding.articleDescription.text = articlesModel.shortDescription
            binding.articleLastUpdate.text = articlesModel.lastUpdate
            Picasso.get().load(articlesModel.avatarUrl).into(binding.articleAvatar)
        }

    }
}