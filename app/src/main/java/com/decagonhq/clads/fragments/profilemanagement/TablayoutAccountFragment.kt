package com.decagonhq.clads.fragments.profilemanagement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentTablayoutAccountBinding
import com.decagonhq.clads.fragments.editprofiledialogfragments.*

class TablayoutAccountFragment : Fragment() {
    var _binding : FragmentTablayoutAccountBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTablayoutAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent()
        binding.editProfileFirstNameTextview.setOnClickListener {
            val firstNameDialogFragment = FirstNameDialogFragment()
            firstNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.first_name_dialog_fragment))
        }
        binding.editProfileFirstNameValueTextview.setOnClickListener {
            val firstNameDialogFragment = FirstNameDialogFragment()
            firstNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.first_name_dialog_fragment))
        }
        binding.editProfileLastNameTextview.setOnClickListener {
            val lastNameDialogFragment = LastNameDialogFragment()
            lastNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.last_name_dialog_fragment))
        }
        binding.editProfileLastNameValue.setOnClickListener {
            val lastNameDialogFragment = LastNameDialogFragment()
            lastNameDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.last_name_dialog_fragment))
        }
        binding.editProfileOtherNameTextview.setOnClickListener {
            val otherNameDialogFragment = OtherNameDialogFragment()
            otherNameDialogFragment.show(requireActivity().supportFragmentManager, "other name dialog fragment")
        }
        binding.editProfileOtherNameValueTextview.setOnClickListener {
            val otherNameDialogFragment = OtherNameDialogFragment()
            otherNameDialogFragment.show(requireActivity().supportFragmentManager, "other name dialog fragment")
        }
        binding.editProfileGenderTextview.setOnClickListener {
            val genderDialogFragment = GenderDialogFragment()
            genderDialogFragment.show(requireActivity().supportFragmentManager, "gender dialog button")
        }
        binding.editProfileGenderValueTextview.setOnClickListener {
            val genderDialogFragment = GenderDialogFragment()
            genderDialogFragment.show(requireActivity().supportFragmentManager, "gender dialog button")
        }
        binding.editProfileWorkAddressTextview.setOnClickListener {
            val workAddressDialogFragment = WorkAddressDialogFragment()
            workAddressDialogFragment.show(requireActivity().supportFragmentManager, "workshop address dialog")
        }
        binding.editProfileWorkAddressValueTextview.setOnClickListener {
            val workAddressDialogFragment = WorkAddressDialogFragment()
            workAddressDialogFragment.show(requireActivity().supportFragmentManager, "workshop address dialog")
        }
        binding.editProfileShowroomAddrressTextview.setOnClickListener {
            val showroomAddressDialogFragment = ShowroomAddressDialogFragment()
            showroomAddressDialogFragment.show(requireActivity().supportFragmentManager, "showroom address dilaog")
        }
        binding.editProfileShowroomAddressValueTextview.setOnClickListener {
            val showroomAddressDialogFragment = ShowroomAddressDialogFragment()
            showroomAddressDialogFragment.show(requireActivity().supportFragmentManager, "showroom address dilaog")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
