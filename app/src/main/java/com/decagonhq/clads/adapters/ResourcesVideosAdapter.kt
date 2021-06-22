package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentResourcesVideoRecyclerviewItemBinding
import com.decagonhq.clads.models.VideoView

class ResourcesVideosAdapter(private var videoList: ArrayList<VideoView>) : RecyclerView.Adapter<ResourcesVideosAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(var binding: FragmentResourcesVideoRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var videoLink = binding.resourcesVideoRecyclerviewExoplayerPlayerview
        var videoTag = binding.resourceRecyclerviewSewingTextview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = FragmentResourcesVideoRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.itemView.apply {
            with(holder) {
                with(videoList[position]) {
                    videoLink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.home))
                    videoTag.text = videoTitle
                }
            }
        }
    }

    private var videoLimit = 5
    override fun getItemCount(): Int {
        return if (videoList.size > videoLimit) {
            videoLimit
        } else {
            videoList.size
        }
    }
}
