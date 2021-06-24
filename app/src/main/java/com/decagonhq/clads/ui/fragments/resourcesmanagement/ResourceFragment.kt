package com.decagonhq.clads.ui.fragments.resourcesmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.adapters.ResourcesArticlesAdapter
import com.decagonhq.clads.adapters.ResourcesVideosAdapter
import com.decagonhq.clads.databinding.FragmentResourceBinding
import com.decagonhq.clads.utils.ResourcesDummyData
import com.decagonhq.clads.utils.ViewIndividualVideoClickListner

class ResourceFragment : Fragment(), ViewIndividualVideoClickListner {

    private var _binding: FragmentResourceBinding? = null
    private val binding get() = _binding!!

    // Declare variables for video item
    private lateinit var videoListAdapter: ResourcesVideosAdapter
    private lateinit var exoplayerRecyclerview: RecyclerView

    // Declare variables for article item
    private lateinit var articleListAdapter: ResourcesArticlesAdapter
    private lateinit var articlerRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Layout manager for video recyclerview item
        exoplayerRecyclerview = binding.resourcesVideosRecyclerview
        videoListAdapter = ResourcesVideosAdapter(ResourcesDummyData.videoList, this)
        exoplayerRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        exoplayerRecyclerview.adapter = videoListAdapter

        // Layout manager for article recyclerview item
        articlerRecyclerView = binding.resourcesArticleRecyclerview
        articleListAdapter = ResourcesArticlesAdapter(ResourcesDummyData.articleList)
        articlerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        articlerRecyclerView.adapter = articleListAdapter

        // Action to navigate to view all videos fragment from videos & articles fragment
        binding.resourcesViewAllTopTextview.setOnClickListener {
            val action =
                ResourceFragmentDirections.actionResourceFragmentToResourcesAllVideosFragment()
            findNavController().navigate(action)
        }
    }

    override fun onItemClicked(videoUrl: String) {
        val action = ResourceFragmentDirections.actionResourceFragmentToViewIndividualVideoWithExoplayerScreenFragment(videoUrl)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
