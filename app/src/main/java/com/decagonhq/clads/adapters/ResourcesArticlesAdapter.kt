package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentResourcesArticleRecyclerviewItemBinding
import com.decagonhq.clads.models.ArticleView

class ResourcesArticlesAdapter(var articleList: ArrayList<ArticleView>) : RecyclerView.Adapter<ResourcesArticlesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(var binding: FragmentResourcesArticleRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var articleLink = binding.resourcesArticleImageview
        var articleTag = binding.resourcesArticleStitchingTextview
        var articleWriter = binding.resourcesArticleAuthorTextview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = FragmentResourcesArticleRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.itemView.apply {
            with(holder) {
                with(articleList[position]) {

                    articleLink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_resources_article_image1))
                    articleTag.text = articleTitle
                    articleWriter.text = articleArthur
                }
            }
        }
    }

    private var articleLimit = 5
    override fun getItemCount(): Int {
        return if (articleList.size > articleLimit) {
            articleLimit
        } else {
            articleList.size
        }
    }
}
