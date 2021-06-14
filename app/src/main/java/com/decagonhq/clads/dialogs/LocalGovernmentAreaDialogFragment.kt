package com.decagonhq.clads.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileLocalGovernmentAreaDialogFragmentLayoutBinding

class LocalGovernmentAreaDialogFragment : DialogFragment() {
    // initializing binding variables
    var _binding: EditProfileLocalGovernmentAreaDialogFragmentLayoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the dialog layout when the view is created
        _binding = EditProfileLocalGovernmentAreaDialogFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var localGovernmentAreaLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editProfileLocalGovernmentAreDialogFragmentCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.editProfileLocalGovernmentAreaDialogFragmentOkTextview.setOnClickListener {
            val localGovernmentArea = binding.editProfileLocalGovernmentAreaDialogFragmentInputEdittext.text.toString()
            if (localGovernmentArea != "") {
                localGovernmentAreaLiveData.value = localGovernmentArea
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
