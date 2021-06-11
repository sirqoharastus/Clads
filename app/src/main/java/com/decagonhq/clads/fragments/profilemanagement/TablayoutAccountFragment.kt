package com.decagonhq.clads.fragments.profilemanagement

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentTablayoutAccountBinding
import com.decagonhq.clads.fragments.editprofiledialogfragments.FirstNameDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.GenderDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.LastNameDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.OtherNameDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.ShowroomAddressDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.WorkAddressDialogFragment
import com.decagonhq.clads.models.EditProfileViewmodel

class TablayoutAccountFragment : Fragment() {
    // declaring binding variables
    var _binding: FragmentTablayoutAccountBinding? = null
    val binding get() = _binding!!
    private lateinit var viewmodel: EditProfileViewmodel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating layout when the view is created
        _binding = FragmentTablayoutAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
    fun getShowroomAddress(street: String, city: String, state: String) {
        binding.editProfileShowroomAddressValueTextview.text = "$street, $city, $state"
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this).get(EditProfileViewmodel::class.java)

        // setting account profile values on click to inflate respective dialogs in the process

        binding.editProfileFirstNameValueTextview.setOnClickListener {
            val firstNameDialogFragment = FirstNameDialogFragment()
            firstNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.first_name_dialog_fragment))
            firstNameDialogFragment.firstNameLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileFirstNameValueTextview.text = it.toString()
                }
            )
        }
        binding.editProfileLastNameValue.setOnClickListener {
            val lastNameDialogFragment = LastNameDialogFragment()
            lastNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.last_name_dialog_fragment))
            lastNameDialogFragment.lastNameLiveData.observe(viewLifecycleOwner,) {
                binding.editProfileLastNameValue.text = it.toString()
            }
        }
        binding.editProfileOtherNameValueTextview.setOnClickListener {
            val otherNameDialogFragment = OtherNameDialogFragment()
            otherNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.other_name_dialog_text))
            otherNameDialogFragment.otherNameDialogLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileOtherNameValueTextview.text = it
                }
            )
        }

        binding.editProfileGenderValueTextview.setOnClickListener {
            val genderDialogFragment = GenderDialogFragment()
            genderDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.gender_dialog_text))
            genderDialogFragment.genderLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileGenderValueTextview.text = it
                }
            )
        }

        binding.editProfileWorkAddressValueTextview.setOnClickListener {
            val workAddressDialogFragment = WorkAddressDialogFragment()
            workAddressDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.worshop_address_text))
        }

        binding.editProfileShowroomAddressValueTextview.setOnClickListener {
            val showroomAddressDialogFragment = ShowroomAddressDialogFragment()
            showroomAddressDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.showroom_address_dialog_text))
            showroomAddressDialogFragment.streetLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileShowroomAddressValueTextview.text = it
                }
            )
        }

        viewmodel.showroomAddressViewModel.observe(viewLifecycleOwner, {
            binding.editProfileShowroomAddressValueTextview.text = it
        })

        viewmodel.workshopAddressViewModel.observe(viewLifecycleOwner, {
            binding.editProfileWorkAddressValueTextview.text = it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
