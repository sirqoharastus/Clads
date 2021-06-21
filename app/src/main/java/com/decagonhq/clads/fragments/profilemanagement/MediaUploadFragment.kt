package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decagonhq.clads.databinding.FragmentMediaUploadBinding
import com.decagonhq.clads.utils.DataListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MediaUploadFragment : Fragment() {

    private val args: MediaUploadFragmentArgs by navArgs()
    private lateinit var sendPhoto: FloatingActionButton

    private var _binding: FragmentMediaUploadBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMediaUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendPhoto = binding.mediaUploadFragmentSendFab
        val photoView = binding.mediaUploadFragmentPhotoImageView
        val photo = args.imageUri

        /*load the image sent from media fragment*/
        Glide.with(this)
            .load(photo)
            .into(photoView)

        /*click the send fab to send photo and name of photo back to media fragment*/
        sendPhoto.setOnClickListener {
            val imageName = binding.mediaUploadFragmentPhotoEditText.text.toString()
            val imageData = args.imageUri

            DataListener.imageListener.value = true

            val bundle =
                bundleOf("IMAGE_NAME_BUNDLE_KEY" to imageName, "IMAGE_DATA_BUNDLE_KEY" to imageData)
            if (imageName.isEmpty()) {
                Toast.makeText(requireContext(), "Enter Image Name", Toast.LENGTH_SHORT).show()
            } else {
                findNavController()
                    .previousBackStackEntry?.savedStateHandle?.set("IMAGE_KEY", bundle)
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
