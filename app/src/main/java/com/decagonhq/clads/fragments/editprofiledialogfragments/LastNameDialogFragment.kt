package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.EditProfileLastNameDialogBinding

class LastNameDialogFragment : DialogFragment() {
    // declaring binding variables
    var _binding: EditProfileLastNameDialogBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when view is created
        _binding = EditProfileLastNameDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    var lastNameLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.firstNameDialogOkTextview.setOnClickListener {
            lastNameLiveData.value = binding.firstNameDialogInputEdittext.text.toString()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
