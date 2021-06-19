package com.decagonhq.clads.fragments.profilemanagement

import android.Manifest.permission.ACCESS_MEDIA_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media.MediaBrowserServiceCompat.RESULT_OK
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentAccountBinding
import com.decagonhq.clads.dialogs.FirstNameDialogFragment
import com.decagonhq.clads.dialogs.GenderDialogFragment
import com.decagonhq.clads.dialogs.LastNameDialogFragment
import com.decagonhq.clads.dialogs.LegalStatusDialogFragment
import com.decagonhq.clads.dialogs.LocalGovernmentAreaDialogFragment
import com.decagonhq.clads.dialogs.NameOfUnionDialogFragment
import com.decagonhq.clads.dialogs.NumberOfEmployeesDialogFragment
import com.decagonhq.clads.dialogs.OtherNameDialogFragment
import com.decagonhq.clads.dialogs.ShowroomAddressDialogFragment
import com.decagonhq.clads.dialogs.StateDialogFragment
import com.decagonhq.clads.dialogs.WardDialogFragment
import com.decagonhq.clads.dialogs.WorkAddressDialogFragment
import com.decagonhq.clads.viewmodels.AccontViewModel
import java.util.jar.Manifest

class AccountFragment : Fragment() {

    // declaring binding variables
    var _binding: FragmentAccountBinding? = null
    val binding get() = _binding!!
    private val permissionRequestCode = 100
    lateinit var imageUri: Uri
    private lateinit var viewmodel: AccontViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // inflating layout when the view is created
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getShowroomAddress(street: String, city: String, state: String) {
        binding.editProfileShowroomAddressValueTextview.text = "$street, $city, $state"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this).get(AccontViewModel::class.java)

        // setting account profile values on click to inflate respective dialogs in the process

        binding.editProfileFirstNameValueTextview.setOnClickListener {
            val firstNameDialogFragment = FirstNameDialogFragment()
            firstNameDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.first_name_dialog_fragment)
            )
            firstNameDialogFragment.firstNameLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileFirstNameValueTextview.text = it.toString()
                }
            )
        }
        binding.editProfileLastNameValue.setOnClickListener {
            val lastNameDialogFragment = LastNameDialogFragment()
            lastNameDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.last_name_dialog_fragment)
            )
            lastNameDialogFragment.lastNameLiveData.observe(viewLifecycleOwner) {
                binding.editProfileLastNameValue.text = it.toString()
            }
        }
        binding.editProfileOtherNameValueTextview.setOnClickListener {
            val otherNameDialogFragment = OtherNameDialogFragment()
            otherNameDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.other_name_dialog_text)
            )
            otherNameDialogFragment.otherNameDialogLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileOtherNameValueTextview.text = it
                }
            )
        }

        binding.editProfileGenderValueTextview.setOnClickListener {
            val genderDialogFragment = GenderDialogFragment()
            genderDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.gender_dialog_text)
            )
            genderDialogFragment.genderLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileGenderValueTextview.text = it
                }
            )
        }

        binding.editProfileWorkAddressValueTextview.setOnClickListener {
            val workAddressDialogFragment = WorkAddressDialogFragment()
            workAddressDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.worshop_address_text)
            )
            workAddressDialogFragment.workshopAddressLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileWorkAddressValueTextview.text = it
                }
            )
        }

        binding.editProfileShowroomAddressValueTextview.setOnClickListener {
            val showroomAddressDialogFragment = ShowroomAddressDialogFragment()
            showroomAddressDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.showroom_address_dialog_text)
            )
            showroomAddressDialogFragment.showroomAddressLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileShowroomAddressValueTextview.text = it
                }
            )
        }

        binding.editProfileNumberOfEmployeesValueTextview.setOnClickListener {
            val numberOfEmployeesDialogFragment = NumberOfEmployeesDialogFragment()
            numberOfEmployeesDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(
                    R.string.edit_profile_fragment_numner_of_employees_text
                )
            )
            numberOfEmployeesDialogFragment.numberOfEmployeesLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileNumberOfEmployeesValueTextview.text = it
                }
            )
        }

        binding.editProfileLegalStatusValueTextview.setOnClickListener {
            val legalStatusDialogFragment = LegalStatusDialogFragment()
            legalStatusDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.edit_profile_fragment_legal_status_text)
            )
            legalStatusDialogFragment.legalStatusLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileLegalStatusValueTextview.text = it
                }
            )
        }

        binding.editProfileNameOfUnionValueTextview.setOnClickListener {
            val nameOfUnionDialogFragment = NameOfUnionDialogFragment()
            nameOfUnionDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.edit_profile_fragment_name_of_dialog_fragment_text)
            )
            nameOfUnionDialogFragment.nameOfUnionLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileNameOfUnionValueTextview.text = it
                }
            )
        }

        binding.editProfileWardValueTextview.setOnClickListener {
            val wardDialogFragment = WardDialogFragment()
            wardDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.edit_profile_fragment_ward_dialog_fragment_text)
            )
            wardDialogFragment.wardLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileWardValueTextview.text = it
                }
            )
        }

        binding.editProfileLocalGovernmentAreaValueTextview.setOnClickListener {
            val localGovernmentAreaDialogFragment = LocalGovernmentAreaDialogFragment()
            localGovernmentAreaDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(
                    R.string.edit_profile_fragment_local_government_dialog_fragment
                )
            )
            localGovernmentAreaDialogFragment.localGovernmentAreaLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileLocalGovernmentAreaValueTextview.text = it
                }
            )
        }

        binding.editProfileStateValueTextview.setOnClickListener {
            val stateDialogFragment = StateDialogFragment()
            stateDialogFragment.show(
                requireActivity().supportFragmentManager,
                getString(R.string.edit_profile_fragment_state_dialog_fragment_text)
            )
            stateDialogFragment.stateLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileStateTextview.text = it
                }
            )
        }

        binding.editProfileAccountTabChangePictureTextview.setOnClickListener {
            if (checkPermission2()){
                pickImage()
            }
            else {
                requestPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE, "ACCESS MEDIA", 100)

            }

        }
    }



    private fun requestPermission(permission: String, name: String, requestCode: Int) {
        when {
            shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
            else -> ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
        }
    }

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "Permission not granted", Toast.LENGTH_SHORT).show()
            } else {
                pickImage()
                Toast.makeText(requireActivity(), "Permission granted", Toast.LENGTH_SHORT).show()
            }
        }
        when (requestCode) {
            100 -> innerCheck("calls")
        }
    }


    fun checkPermission2(): Boolean{
        return ActivityCompat.checkSelfPermission(requireActivity().applicationContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }

    private fun pickImage2() {
        val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select New Profile Picture"), 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == permissionRequestCode && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val selectedPhotoUri = data.data
            binding.editProfileAccountTabImageview.setImageURI(selectedPhotoUri)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
