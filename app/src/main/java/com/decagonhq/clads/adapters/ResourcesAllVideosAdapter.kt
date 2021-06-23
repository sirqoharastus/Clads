package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentResourcesAllVideosRecyclerviewItemBinding
import com.decagonhq.clads.models.VideoView

class ResourcesAllVideosAdapter(private var allVideoList: ArrayList<VideoView>) : RecyclerView.Adapter<ResourcesAllVideosAdapter.AllVideoViewHolder>() {

    inner class AllVideoViewHolder(var binding: FragmentResourcesAllVideosRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var allVideoLink = binding.resourcesAllVideoRecyclerviewExoplayerPlayerview
        var allVideoTag = binding.resourceRecyclerviewSewingTextview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllVideoViewHolder {
        val binding = FragmentResourcesAllVideosRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AllVideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllVideoViewHolder, position: Int) {
        holder.itemView.apply {
            with(holder) {
                with(allVideoList[position]) {
                    allVideoLink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.home))
                    allVideoTag.text = videoTitle
                }
            }
        }
    }

    override fun getItemCount(): Int = allVideoList.size
}
