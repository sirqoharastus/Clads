package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.EditProfileGenderDialogBinding

class GenderDialogFragment : DialogFragment() {
    // declaring binding variables
    var _binding: EditProfileGenderDialogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when view is created
        _binding = EditProfileGenderDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    // initializing mutable livedata which is used to save edittext data for access
    var genderLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.genderDialogRadioGroup.setOnCheckedChangeListener { group, checkId ->
            if (checkId == R.id.gender_dialog_male_radio_button) {
                genderLiveData.value = getString(R.string.gender_dialog_fragment_male_text)
                this.dismiss()
            } else if (checkId == R.id.gender_dialog_female_radio_button) {
                genderLiveData.value = getString(R.string.gender_dialog_fragment_female_text)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
