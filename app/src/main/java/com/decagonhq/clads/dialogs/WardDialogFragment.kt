package com.decagonhq.clads.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileWardDialogFragmentLayoutBinding

class WardDialogFragment : DialogFragment() {
    // initializing binding variables
    var _binding: EditProfileWardDialogFragmentLayoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when this view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = EditProfileWardDialogFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var wardLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileWardDialogFragmentCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.editProfileWardDialogFragmentOkTextview.setOnClickListener {
            val ward = binding.editProfileWardDialogFragmentInputEdittext.text.toString()
            if (ward != "") {
                wardLiveData.value = ward
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
