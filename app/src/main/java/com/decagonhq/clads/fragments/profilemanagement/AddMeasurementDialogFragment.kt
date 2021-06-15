package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.decagonhq.clads.databinding.FragmentAddMeasurementDialogBinding
import com.decagonhq.clads.utils.MeasurementData
import com.decagonhq.clads.viewModel.MeasurementViewModel


class AddMeasurementDialogFragment : DialogFragment() {
    private var _binding: FragmentAddMeasurementDialogBinding? = null
    val binding get() = _binding!!
    private val viewModel: MeasurementViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddMeasurementDialogBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val saveButton = binding.addMeasurementDialogFragmentSaveButton

        saveButton.setOnClickListener {
            val measurementName =
                binding.addMeasurementFragmentMeasurementNameEditText.text.toString()
            val measurementValue =
                binding.addMeasurementDialogFragmentMeasurementValueEditText.text.toString()


            val bundle = MeasurementData(measurementName, measurementValue)
//passing the updated measurments to the view model
            viewModel.getClientMeasurement(bundle)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}