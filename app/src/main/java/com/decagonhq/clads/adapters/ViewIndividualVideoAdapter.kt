package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.databinding.ViewIndividualVideoScreenRecyclerviewAdapterLayoutBinding
import com.decagonhq.clads.models.VideoResources
import com.decagonhq.clads.utils.ViewIndividualVideoClickListner

class ViewIndividualVideoAdapter(private val listener: ViewIndividualVideoClickListner) :
    RecyclerView.Adapter<ViewIndividualVideoAdapter.ViewIndividualVideoViewHolder>() {

    inner class ViewIndividualVideoViewHolder(var binding: ViewIndividualVideoScreenRecyclerviewAdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    // This will hold the list of videos to be recycled in the recyclerview
    private var videoResources = mutableListOf<VideoResources>()

    // this function is responsible for setting up the list to be displayed in the recyclerview
    fun setVideoResources(videos: MutableList<VideoResources>) {
        this.videoResources = videos
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewIndividualVideoViewHolder {
        return ViewIndividualVideoViewHolder(
            ViewIndividualVideoScreenRecyclerviewAdapterLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewIndividualVideoViewHolder, position: Int) {
        var currentVideo = videoResources[position]
        holder.binding.viewIndividualVideoScreenRecyclerviewLayoutVideoTitleTextView.text =
            currentVideo.title
        holder.binding.viewIndividualVideoScreenRecyclerviewVideoLengthTextView.text =
            currentVideo.time

        // Using Glide to load the thumbnail from the remote API and display it on an image view
        Glide.with(holder.binding.root.context)
            // the URL from which the images are loaded from
            .load(currentVideo.imageThumbnailUrl)
            .into(holder.binding.viewIndividualVideoScreenRecyclerviewLayoutImage)

        /**
         * setting a clickListener on the image cardview
         * when the image is clicked the video URL at the position of click is obtained
         * the obtained video URL is forwarded to the fragment that will recycle the videos
         * and in that fragment the video at the position of click is loaded/played with exoplayer
         */
        holder.binding.viewIndividualVideoScreenRecyclerviewLayoutCardview.setOnClickListener {
            val url = currentVideo.videoUrl
            listener.onItemClicked(url)
        }
    }

    override fun getItemCount(): Int {
        return videoResources.size
    }

}
