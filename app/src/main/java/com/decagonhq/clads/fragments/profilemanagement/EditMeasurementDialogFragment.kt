package com.decagonhq.clads.fragments.profilemanagement

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.decagonhq.clads.databinding.FragmentEditMeasurementDialogBinding
import com.decagonhq.clads.utils.MeasurementData
import com.decagonhq.clads.utils.clientsDataMeasurement
import com.decagonhq.clads.utils.temporaryMeasurement
import com.decagonhq.clads.viewModel.MeasurementViewModel


class EditMeasurementDialogFragment(bundle: Bundle) : DialogFragment() {
    private var _binding: FragmentEditMeasurementDialogBinding? = null
    val binding get() = _binding!!
    private val viewModel: MeasurementViewModel by viewModels({ requireActivity() })

    //the switch checks if the user enters the fragment by entering a measurement or exits the fragment by backpress
    var switch: Boolean = false


    val data: MeasurementData = bundle?.getParcelable("measurementToEdit")!!
    var editedData: MeasurementData = MeasurementData("", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditMeasurementDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val saveButton = binding.editMeasurementDialogFragmentSaveButton

        saveButton.setOnClickListener {
            switch = true
            val measurementName =
                binding.editMeasurementFragmentMeasurementNameEditText.text.toString()
            val measurementValue =
                binding.editMeasurementDialogFragmentMeasurementValueEditText.text.toString()
            editedData = MeasurementData(measurementName, measurementValue)

            if (editedData != null && editedData != temporaryMeasurement) {
                viewModel.getClientMeasurement(editedData)
            } else {
                viewModel.getClientMeasurement(temporaryMeasurement)
            }


            dismiss()
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        //to return the temporary measurement collected in the onMeasurement click function in case the user decides not to send data from the dialog fragment

        if (switch == false) {
            viewModel.getClientMeasurement(temporaryMeasurement)
        }

    }

}
