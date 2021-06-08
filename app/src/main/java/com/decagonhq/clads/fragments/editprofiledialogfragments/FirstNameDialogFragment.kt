package com.decagonhq.clads.fragments.editprofiledialogfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.decagonhq.clads.databinding.EditProfileFirstNameDialogFragmentBinding

class FirstNameDialogFragment: DialogFragment() {
    var _binding: EditProfileFirstNameDialogFragmentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditProfileFirstNameDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = binding.firstNameDialogInputEdittext
        val okBtn = binding.firstNameDialogOkTextview
        okBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("first name", name.text.toString())
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}