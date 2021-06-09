package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentTablayoutAccountBinding
import com.decagonhq.clads.fragments.editprofiledialogfragments.*

class TablayoutAccountFragment : Fragment() {
    // declaring binding variables
    var _binding: FragmentTablayoutAccountBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating layout when the view is created
        _binding = FragmentTablayoutAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setting account profile values on click to inflate respective dialogs in the process

        binding.editProfileFirstNameValueTextview.setOnClickListener {
            val firstNameDialogFragment = FirstNameDialogFragment()
            firstNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.first_name_dialog_fragment))
        }
        binding.editProfileLastNameValue.setOnClickListener {
            val lastNameDialogFragment = LastNameDialogFragment()
            lastNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.last_name_dialog_fragment))
        }
        binding.editProfileOtherNameValueTextview.setOnClickListener {
            val otherNameDialogFragment = OtherNameDialogFragment()
            otherNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.other_name_dialog_text))
        }

        binding.editProfileGenderValueTextview.setOnClickListener {
            val genderDialogFragment = GenderDialogFragment()
            genderDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.gender_dialog_text))
        }

        binding.editProfileWorkAddressValueTextview.setOnClickListener {
            val workAddressDialogFragment = WorkAddressDialogFragment()
            workAddressDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.worshop_address_text)
        }

        binding.editProfileShowroomAddressValueTextview.setOnClickListener {
            val showroomAddressDialogFragment = ShowroomAddressDialogFragment()
            showroomAddressDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.showroom_address_dialog_text))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
