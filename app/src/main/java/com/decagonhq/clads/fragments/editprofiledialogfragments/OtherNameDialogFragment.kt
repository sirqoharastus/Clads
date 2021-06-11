package com.decagonhq.clads.fragments.editprofiledialogfragments

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
        _binding = EditProfileOtherNameDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    val otherNameDialogLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstNameDialogOkTextview.setOnClickListener {
            otherNameDialogLiveData.value = binding.firstNameDialogInputEdittext.text.toString()
            dismiss()
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
