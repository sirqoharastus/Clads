package com.decagonhq.clads.ui.fragments.resourcesmanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.adapters.ResourcesAllVideosAdapter
import com.decagonhq.clads.databinding.FragmentResourcesAllVideosBinding
import com.decagonhq.clads.utils.ResourcesDummyData

class ResourcesAllVideosFragment : Fragment() {

    private var _binding: FragmentResourcesAllVideosBinding? = null
    private val binding get() = _binding!!

    // Declare variables for video item
    private lateinit var allVideoListAdapter: ResourcesAllVideosAdapter
    private lateinit var resourcesAllVideoRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResourcesAllVideosBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Layout manager for all video recyclerview fragment
        resourcesAllVideoRecyclerView = binding.resourcesAllVideosRecyclerview
        allVideoListAdapter = ResourcesAllVideosAdapter(ResourcesDummyData.videoList)
        resourcesAllVideoRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        resourcesAllVideoRecyclerView.adapter = allVideoListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
