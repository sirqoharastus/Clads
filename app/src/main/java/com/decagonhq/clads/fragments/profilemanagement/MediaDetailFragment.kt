package com.decagonhq.clads.fragments.profilemanagement

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentMediaDetailBinding
import com.decagonhq.clads.models.Photo
import com.decagonhq.clads.utils.DataListener
import com.decagonhq.clads.utils.PhotoProvider.CAPTION
import com.decagonhq.clads.utils.PhotoProvider.SELECT_IMAGE_REQUEST_CODE
import com.decagonhq.clads.utils.PhotoProvider.photosProvidersList

class MediaDetailFragment : Fragment() {

    private val args: MediaDetailFragmentArgs by navArgs()
    private lateinit var photo: Uri
    private lateinit var photoImageView: ImageView
    private var _binding: FragmentMediaDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaDetailBinding.inflate(inflater, container, false)
        photo = args.imageUri.toUri()
        photoImageView = binding.mediaDetailFragmentImageView
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoImageView = binding.mediaDetailFragmentImageView
        DataListener.imageListener.value = false

        Glide.with(this)
            .load(photo)
            .into(photoImageView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.media_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashboard_media_fragment_menu_share -> sharePhoto()
            R.id.dashboard_media_fragment_menu_edit -> editPhoto()
            R.id.dashboard_media_fragment_menu_delete -> deletePhoto()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sharePhoto() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, photo)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_with)))
    }

    private fun editPhoto() {

        val photoData = Photo(photo, CAPTION)
        photosProvidersList.remove(photoData)
        val imageIntent = Intent(Intent.ACTION_PICK)
        imageIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        imageIntent.type = "image/*"
        startActivityForResult(imageIntent, SELECT_IMAGE_REQUEST_CODE)
    }

    private fun deletePhoto() {

        val photoData = Photo(photo, CAPTION)
        photosProvidersList.remove(photoData)

        val action = MediaDetailFragmentDirections
            .actionMediaDetailFragmentToDashboardMediaFragment()
        findNavController().navigate(action)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_IMAGE_REQUEST_CODE) {
            photo = data?.data!!
            val photoData = Photo(photo, CAPTION)

            Glide.with(this)
                .load(photo)
                .into(photoImageView)

            photosProvidersList.add(photoData)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
