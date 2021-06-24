package com.decagonhq.clads.ui.fragments.profilemanagement

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.decagonhq.clads.adapters.MediaFragmentPhotoAdapter
import com.decagonhq.clads.databinding.FragmentDashboardMediaBinding
import com.decagonhq.clads.models.Photo
import com.decagonhq.clads.utils.DataListener
import com.decagonhq.clads.utils.PhotoProvider.SELECT_IMAGE_REQUEST_CODE
import com.decagonhq.clads.utils.PhotoProvider.photosProvidersList

class DashboardMediaFragment : Fragment() {

    private var _binding: FragmentDashboardMediaBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var imageUri: Uri
    private lateinit var photoGalleryRecyclerAdapter: MediaFragmentPhotoAdapter
    private lateinit var noPhotoImage: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noPhotoImage = binding.dashboardMediaFragmentFrameLayout

        findNavController()
            .currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("IMAGE_KEY")
            ?.observe(viewLifecycleOwner) {
                val imageName = it.getString("IMAGE_NAME_BUNDLE_KEY")
                val imageData = it.getString("IMAGE_DATA_BUNDLE_KEY")
                imageUri = imageData!!.toUri()
                val photo = Photo(imageUri, imageName)
                if (DataListener.imageListener.value == true) {
                    photosProvidersList.add(photo)
                    photoGalleryRecyclerAdapter.notifyDataSetChanged()
                }
                binding.apply {
                    noPhotoImage.visibility = View.INVISIBLE
                    dashboardMediaFragmentPhotoRecyclerView.visibility = View.VISIBLE
                }
            }

        binding.apply {
            dashboardMediaFragmentPhotoRecyclerView.visibility = View.VISIBLE
            dashboardMediaFragmentPhotoRecyclerView.apply {
                photoGalleryRecyclerAdapter =
                    MediaFragmentPhotoAdapter(photosProvidersList)
                adapter = photoGalleryRecyclerAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                photoGalleryRecyclerAdapter.notifyDataSetChanged()
            }
            if (photosProvidersList.isEmpty()) {
                noPhotoImage.visibility = View.VISIBLE
            } else {
                noPhotoImage.visibility = View.INVISIBLE
                dashboardMediaFragmentPhotoRecyclerView.visibility = View.VISIBLE
            }
        }

        binding.dashboardMediaFragmentAddPhotoFab.setOnClickListener {
            if (checkPermission()) {
                selectImageFromGallery()
            } else {
                requestPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    "ACCESS MEDIA",
                    SELECT_IMAGE_REQUEST_CODE
                )
            }
        }
    }

    private fun checkPermission(): Boolean {
        return (
            checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            )
    }

    private fun requestPermission(permission: String, name: String, requestCode: Int) = when {
        shouldShowRequestPermissionRationale(permission) -> showDialog(
            permission,
            name,
            requestCode
        )
        else ->
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
    }

    private fun selectImageFromGallery() {
        val imageIntent = Intent(Intent.ACTION_PICK)
        imageIntent.type = "image/*"
        startActivityForResult(imageIntent, SELECT_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE_REQUEST_CODE) {

            imageUri = data?.data!!
            val imageData = imageUri.toString()
            val action =
                DashboardMediaFragmentDirections
                    .actionDashboardMediaFragmentToMediaUploadFragment(imageData)
            findNavController().navigate(action)
            DataListener.imageListener.value = false
            if (photosProvidersList.isNotEmpty()) {
                noPhotoImage.visibility = View.INVISIBLE
                photoGalleryRecyclerAdapter.notifyDataSetChanged()
            }
        }
    }

    // function to check the result from permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showToast("$name Permission not granted")
            } else {
                selectImageFromGallery()
                showToast("$name Permission granted")
            }
        }
        when (requestCode) {
            SELECT_IMAGE_REQUEST_CODE -> innerCheck("calls")
        }
    }

    // build the permission and show the permission dialog
    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK") { _, _ ->
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(permission),
                    requestCode
                )
            }
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
