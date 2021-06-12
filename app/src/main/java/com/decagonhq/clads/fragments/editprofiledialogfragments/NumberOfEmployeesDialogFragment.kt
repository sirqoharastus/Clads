package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileNumberOfEmployeesDialogBinding

class NumberOfEmployeesDialogFragment : DialogFragment() {
    // initializing binding variables
    var _binding: EditProfileNumberOfEmployeesDialogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the dialog layout when this view is created
        _binding = EditProfileNumberOfEmployeesDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var numberOfEmployeesLiveData = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileAgeDialogFragmentCancelTextview.setOnClickListener {
            dismiss()
        }

        binding.editProfileAgeDialogFragmentOkTextview.setOnClickListener {
            val numberOfEmployees = binding.editProfileAgeDialogFragmentInputEdittext.text.toString()
            if (numberOfEmployees != "") {
                numberOfEmployeesLiveData.value = numberOfEmployees
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
