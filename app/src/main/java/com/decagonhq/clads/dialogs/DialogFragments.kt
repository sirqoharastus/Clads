package com.decagonhq.clads.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.AddSpecialtyDialogFragmentLayoutBinding
import com.decagonhq.clads.databinding.DeliveryLeadTimeFragmentLayoutBinding
import com.decagonhq.clads.databinding.ObiomaTrainedDialogFragmentLayoutBinding
import com.decagonhq.clads.models.Specialty
import com.decagonhq.clads.viewmodels.EditProfileViewModel

class DialogFragments private constructor(private var layoutId: Int) : DialogFragment() {

    private val viewModel: EditProfileViewModel by viewModels({ requireActivity() })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container)
    }

    companion object {

        fun createFragment(layoutId: Int): DialogFragments {

            return DialogFragments(layoutId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        when (layoutId) {
            R.layout.add_specialty_dialog_fragment_layout -> {

                val binding = AddSpecialtyDialogFragmentLayoutBinding.bind(view)
                binding.addSpecialtyDialogFragmentAddButton.apply {
                    setOnClickListener {
                        viewModel.addSpecialty(
                            Specialty(
                                binding.addSpecialtyDialogFragmentSpecialtyNameEdittext.text.toString(),
                                false
                            )
                        )
                        dismiss()
                    }
                }

                binding.addSpecialtyDialogFragmentSpecialtyNameEdittext.apply {
                    doOnTextChanged { text, start, before, count ->
                        if (text?.length!! >= 1) {
                            binding.addSpecialtyDialogFragmentAddButton.apply {
                                setBackgroundColor(
                                    resources.getColor(
                                        R.color.deep_sky_blue,
                                        resources.newTheme()
                                    )
                                )
                                isEnabled = true
                            }
                        } else {
                            binding.addSpecialtyDialogFragmentAddButton.apply {
                                setBackgroundColor(
                                    resources.getColor(
                                        R.color.grey,
                                        resources.newTheme()
                                    )
                                )
                                isEnabled = false
                            }
                        }
                    }
                }
            }

            R.layout.delivery_lead_time_fragment_layout -> {

                val binding = DeliveryLeadTimeFragmentLayoutBinding.bind(view)
                var durationType = ""
                binding.radioGroup.apply {
                    setOnCheckedChangeListener { group, checkedId ->
                        durationType = (findViewById<RadioButton>(group.checkedRadioButtonId)).text.toString()
                    }
                }

                binding.deliveryLeadTimeOkButton.apply {
                    setOnClickListener {
                        if (durationType != "") {
                            viewModel.addDeliveryTime(
                                "${binding.addDeliveryTimeEditText.text} " + durationType
                            )
                            dismiss()
                        }
                    }
                }

                binding.deliveryLeadTimeCancelButton.setOnClickListener {
                    dismiss()
                }

                binding.addDeliveryTimeEditText.apply {
                    doOnTextChanged { text, _, _, _ ->
                        if (text?.length!! > 0) {

                            binding.deliveryLeadTimeOkButton.apply {
                                setTextColor(
                                    resources.getColor(
                                        R.color.deep_sky_blue,
                                        resources.newTheme()
                                    )
                                )
                                binding.deliveryLeadTimeOkButton.isEnabled = true
                            }
                        } else {
                            binding.deliveryLeadTimeOkButton.apply {
                                setTextColor(
                                    resources.getColor(
                                        R.color.grey,
                                        resources.newTheme()
                                    )
                                )
                                binding.deliveryLeadTimeOkButton.isEnabled = false
                            }
                        }
                    }
                }
            }

            R.layout.obioma_trained_dialog_fragment_layout -> {
                val binding = ObiomaTrainedDialogFragmentLayoutBinding.bind(view)
                var trained = ""
                binding.obiomaTrainedRadioGroup.apply {
                    setOnCheckedChangeListener { group, checkedId ->
                        binding.obiomaTrainedDialogFragmentOkButton.apply {
                            isEnabled = true
                            setTextColor(
                                resources.getColor(
                                    R.color.deep_sky_blue,
                                    resources.newTheme()
                                )
                            )
                        }
                        if (group.checkedRadioButtonId == binding.obiomaCertifiedNoButton.id) {
                            trained = binding.obiomaCertifiedNoButton.text.toString()
                        } else if (group.checkedRadioButtonId == binding.obiomaCertifiedYesButton.id) {
                            trained = binding.obiomaCertifiedYesButton.text.toString()
                        }
                    }
                }
                binding.obiomaTrainedDialogFragmentOkButton.apply {
                    setOnClickListener {

                        if (trained != "") {
                            viewModel.obiomaTrained(trained)
                            dismiss()
                        }
                    }
                }

                binding.obiomaTrainedDialogCancelButton.setOnClickListener {
                    dismiss()
                }
            }
        }
    }
}
