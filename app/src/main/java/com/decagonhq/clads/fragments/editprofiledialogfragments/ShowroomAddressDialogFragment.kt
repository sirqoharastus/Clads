package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.EditProfileShowroomAddressDialogBinding
import com.decagonhq.clads.models.EditProfileViewmodel

class ShowroomAddressDialogFragment : DialogFragment() {
    // declaring binding variables
    var _binding: EditProfileShowroomAddressDialogBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: EditProfileViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when the view is created
        _binding = EditProfileShowroomAddressDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    var streetLiveData = MutableLiveData<String>()
    var cityLiveData = MutableLiveData<String>()
    var stateLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(EditProfileViewmodel::class.java)

        binding.showroomAddressDialogOkTextview.setOnClickListener {
            val street = binding.showroomAddressDialogStreetEdittext.text.toString()
            val city = binding.showroomAddressDialogCityEdittext.text.toString()
            val state = binding.showroomAddressDialogStateEdittet.text.toString()
            viewModel.showroomAddressViewModel.value = "$street, $city, $state"
            dismiss()
        }

        binding.showroomAddressDialogCancelTextview.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
