package com.decagonhq.clads.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagonhq.clads.databinding.PhotoRecyclerViewItemBinding
import com.decagonhq.clads.fragments.profilemanagement.DashboardMediaFragmentDirections
import com.decagonhq.clads.models.Photo

class MediaFragmentPhotoAdapter(private val list: List<Photo>) :
    RecyclerView.Adapter<MediaFragmentPhotoAdapter.ViewHolder>() {

    class ViewHolder(val binding: PhotoRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var photoImage = binding.photoRecyclerviewImageview
        private var photoCaption = binding.photoRecyclerviewCaption

        fun bindView(item: Photo) {
            photoCaption.text = item.caption

            Glide.with(binding.root.context)
                .load(item.uri)
                .into(photoImage)

            binding.photoRecyclerviewImageview.setOnClickListener {
                val imageUri = item.uri.toString()
                val imageCaption = item.caption
                // use actions to pass data from one fragment to the other
                val action = DashboardMediaFragmentDirections
                    .actionDashboardMediaFragmentToMediaDetailFragment(imageUri, imageCaption?: "")
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = PhotoRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int = list.size
}
