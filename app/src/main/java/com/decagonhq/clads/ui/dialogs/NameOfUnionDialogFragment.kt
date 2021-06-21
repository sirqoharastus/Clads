package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileNameOfUnionDialogFragmentLayoutBinding

class NameOfUnionDialogFragment : DialogFragment() {
    // initializing binding variables
    var _binding: EditProfileNameOfUnionDialogFragmentLayoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the dialog layout when the view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = EditProfileNameOfUnionDialogFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var nameOfUnionLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editProfileNameOfUnionDialogFragmentCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.editProfileNameOfUnionDialogFragmentOkTextview.setOnClickListener {
            val nameOfUnion = binding.editProfileNameOfUnionDialogFragmentInputEdittext.text.toString()
            if (nameOfUnion != "") {
                nameOfUnionLiveData.value = nameOfUnion
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
