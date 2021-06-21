package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileOtherNameDialogBinding

class OtherNameDialogFragment : DialogFragment() {
    // declaring binding variables
    var _binding: EditProfileOtherNameDialogBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when the view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = EditProfileOtherNameDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    val otherNameDialogLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstNameDialogOkTextview.setOnClickListener {
            if (binding.firstNameDialogInputEdittext.text.toString() != "") {
                otherNameDialogLiveData.value = binding.firstNameDialogInputEdittext.text.toString()
                dismiss()
            }
        }

        binding.firstNameDialogCancelTextview.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
