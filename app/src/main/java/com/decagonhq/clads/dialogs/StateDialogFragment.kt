package com.decagonhq.clads.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileStateDialogFragmentLayoutBinding

class StateDialogFragment : DialogFragment() {
    // initializing binding variables
    var _binding: EditProfileStateDialogFragmentLayoutBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the dialog layout when this view is created
        _binding = EditProfileStateDialogFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var stateLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileStateDialogFragmentCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.editProfileStateDialogFragmentOkTextview.setOnClickListener {
            val state = binding.editProfileStateDialogFragmentInputEdittext.text.toString()
            if (state != "") {
                stateLiveData.value = state
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
