package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileFirstNameDialogFragmentBinding

class FirstNameDialogFragment : DialogFragment() {
    // creating binding variables
    var _binding: EditProfileFirstNameDialogFragmentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = EditProfileFirstNameDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    var firstNameLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // declaring variables
        val okBtn = binding.firstNameDialogOkTextview
        // setting ok button on click
        okBtn.setOnClickListener {
            if (binding.firstNameDialogInputEdittext.text.toString() != "") {
                firstNameLiveData.value = binding.firstNameDialogInputEdittext.text.toString()
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
