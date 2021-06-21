package com.decagonhq.clads.ui.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentAccountBinding
import com.decagonhq.clads.ui.dialogs.FirstNameDialogFragment
import com.decagonhq.clads.ui.dialogs.GenderDialogFragment
import com.decagonhq.clads.ui.dialogs.LastNameDialogFragment
import com.decagonhq.clads.ui.dialogs.LegalStatusDialogFragment
import com.decagonhq.clads.ui.dialogs.LocalGovernmentAreaDialogFragment
import com.decagonhq.clads.ui.dialogs.NameOfUnionDialogFragment
import com.decagonhq.clads.ui.dialogs.NumberOfEmployeesDialogFragment
import com.decagonhq.clads.ui.dialogs.OtherNameDialogFragment
import com.decagonhq.clads.ui.dialogs.ShowroomAddressDialogFragment
import com.decagonhq.clads.ui.dialogs.StateDialogFragment
import com.decagonhq.clads.ui.dialogs.WardDialogFragment
import com.decagonhq.clads.ui.dialogs.WorkAddressDialogFragment
import com.decagonhq.clads.viewmodels.AccontViewModel

class AccountFragment : Fragment() {

    // declaring binding variables
    var _binding: FragmentAccountBinding? = null
    val binding get() = _binding!!
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
            legalStatusDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.edit_profile_fragment_legal_status_text))
            legalStatusDialogFragment.legalStatusLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileLegalStatusValueTextview.text = it
                }
            )
        }

        binding.editProfileNameOfUnionValueTextview.setOnClickListener {
            val nameOfUnionDialogFragment = NameOfUnionDialogFragment()
            nameOfUnionDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.edit_profile_fragment_name_of_dialog_fragment_text))
            nameOfUnionDialogFragment.nameOfUnionLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileNameOfUnionValueTextview.text = it
                }
            )
        }

        binding.editProfileWardValueTextview.setOnClickListener {
            val wardDialogFragment = WardDialogFragment()
            wardDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.edit_profile_fragment_ward_dialog_fragment_text))
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
            stateDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.edit_profile_fragment_state_dialog_fragment_text))
            stateDialogFragment.stateLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.editProfileShowroomAddressValueTextview.text = it
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
