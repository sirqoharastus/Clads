package com.decagonhq.clads.fragments.profilemanagement

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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.decagonhq.clads.adapters.MediaFragmentPhotoAdapter
import com.decagonhq.clads.databinding.FragmentDashboardMediaBinding
import com.decagonhq.clads.models.Photo

class DashboardMediaFragment : Fragment() {

    private var _binding: FragmentDashboardMediaBinding? = null
    private val binding
        get() = _binding!!

    private val SELECT_IMAGE_REQUEST_CODE = 100
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

        binding.apply {
            dashboardMediaFragmentPhotoRecyclerView.apply {
                photoGalleryRecyclerAdapter = MediaFragmentPhotoAdapter(photosProvidersList)
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
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, "ACCESS MEDIA", SELECT_IMAGE_REQUEST_CODE)
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

    private fun requestPermission(permission: String, name: String, requestCode: Int) {
        when {
            shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
            else -> ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
        }
    }

    private fun selectImageFromGallery() {
        val imageIntent = Intent(Intent.ACTION_PICK)
        imageIntent.type = "image/*"
        startActivityForResult(imageIntent, SELECT_IMAGE_REQUEST_CODE)
    }

    fun selectMultipleImagesFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE_REQUEST_CODE) {
            imageUri = data?.data!!
            val CAPTION = "Beautiful Outfits"
            val photo = Photo(
                imageUri,
                CAPTION
            )
            photosProvidersList.add(photo)
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
            setPositiveButton("OK") { dialog, which ->
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
            }
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

//    fun getAllImages() {
//        imageList.clear()
//        val cursor: Cursor? = requireActivity().contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                val absolutePathOfImage: String =
//                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
//                val imageModel = Photo(image = absolutePathOfImage, "Beautiful Fashion")
//                imageList.add(imageModel)
//            }
//        }
//        cursor?.close()
//    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

var photosProvidersList: ArrayList<Photo> = arrayListOf()
