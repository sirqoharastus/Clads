package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.utils.ArticleData

class ArticlesAdapter(
    val articlesList: ArrayList<ArticleData>,
    val listener: OnArticleClickListener
) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val articleImageView: ImageView = itemView.findViewById(R.id.article_recycler_view_item_layout_image_view)
        val articleTitle: TextView = itemView.findViewById(R.id.article_recycler_view_item_layout_article_name_text_view)
        val articleAuthorName: TextView = itemView.findViewById(R.id.article_recycler_view_item_layout_author_name_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onArticleClick(articlesList, position)
                notifyDataSetChanged()
            }
        }
    }

    interface OnArticleClickListener {
        fun onArticleClick(articlesList: ArrayList<ArticleData>, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_recycler_view_item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = articlesList[position]

        holder.articleTitle.text = currentItem.title
        holder.articleAuthorName.text = currentItem.body

        holder.articleImageView.setOnClickListener {
            listener.onArticleClick(articlesList, position)
        }
    }
}
