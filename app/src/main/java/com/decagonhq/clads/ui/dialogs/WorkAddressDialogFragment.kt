package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.databinding.EditProfileWorkshopAddressDialogBinding
import com.decagonhq.clads.viewmodels.AccontViewModel

class WorkAddressDialogFragment : DialogFragment() {
    // declaring binding variable
    var _binding: EditProfileWorkshopAddressDialogBinding? = null
    val binding get() = _binding!!
    lateinit var viewmodel: AccontViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating layout when view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = EditProfileWorkshopAddressDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var workshopAddressLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(AccontViewModel::class.java)
        binding.workshopAddressDialogCancelTextview.setOnClickListener {
            dismiss()
        }
        // getting data from edittext and saving them in the mutable live data
        binding.workshopAddressDialogOkTextview.setOnClickListener {
            var street = binding.workshopAddressDialogStreetEdittext.text.toString()
            var city = binding.workshopAddressDialogCityEdittext.text.toString()
            var state = binding.workshopAddressDialogStateEdittet.text.toString()
            val address = "$street, $city, $state"
            if (address != null) {
                workshopAddressLiveData.value = address
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
