package com.decagonhq.clads.ui.fragments.resourcesmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.adapters.ArticlesAdapter
import com.decagonhq.clads.databinding.FragmentArticlesBinding
import com.decagonhq.clads.utils.ArticleData

class ArticlesFragment : Fragment(), ArticlesAdapter.OnArticleClickListener {

    private var preBinding: FragmentArticlesBinding? = null
    private val binding: FragmentArticlesBinding get() = preBinding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticlesAdapter

    val articlesList: ArrayList<ArticleData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        preBinding = FragmentArticlesBinding.inflate(layoutInflater, container, false)
        val view = preBinding!!.root

        articlesList.add(ArticleData("Doing Adire", "By Roger Federer"))
        articlesList.add(ArticleData("Tearing Cloth", "By Terry G"))
        articlesList.add(ArticleData("Be a Good Girl", "By Lana Rhoades"))
        articlesList.add(ArticleData("How to keep shut", "By Alariwo"))
        articlesList.add(ArticleData("Kilometre", "By Burna Boy"))
        articlesList.add(ArticleData("Essence", "By Tems"))
        articlesList.add(ArticleData("Doing Adire", "By Roger Federer"))
        articlesList.add(ArticleData("Doing Adire", "By Roger Federer"))
        articlesList.add(ArticleData("Doing Adire", "By Roger Federer"))

        recyclerView = binding.articleFragmentRecyclerView
        adapter = ArticlesAdapter(articlesList, this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)

        return view
    }

    override fun onArticleClick(articlesList: ArrayList<ArticleData>, position: Int) {
        findNavController().navigate(R.id.read_articles_fragment)
    }
}
