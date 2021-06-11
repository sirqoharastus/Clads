package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.EditProfileWorkshopAddressDialogBinding
import com.decagonhq.clads.models.EditProfileViewmodel

class WorkAddressDialogFragment : DialogFragment() {
    // declaring binding variable
    var _binding: EditProfileWorkshopAddressDialogBinding? = null
    val binding get() = _binding!!
    lateinit var viewmodel: EditProfileViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating layout when view is created
        _binding = EditProfileWorkshopAddressDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(EditProfileViewmodel::class.java)
        binding.workshopAddressDialogCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.workshopAddressDialogOkTextview.setOnClickListener {
            var street = binding.workshopAddressDialogStreetEdittext.text.toString()
            var city = binding.workshopAddressDialogCityEdittext.text.toString()
            var state = binding.workshopAddressDialogStateEdittet.text.toString()

            viewmodel.workshopAddressViewModel.value = "$street, $city, $state"
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
