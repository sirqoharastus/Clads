package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.databinding.PaymentOptionsDialogFragmentBinding

class PaymentOptionsDialogFragment : DialogFragment() {
    // declaring binding variables
    var _binding: PaymentOptionsDialogFragmentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when the view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = PaymentOptionsDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    // initializing mutable livedata which is used to save edittext data for access
    var paymentOptionsLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setting cancel button on click to dismiss the dialog
        binding.paymentOptionsDialogCancelButton.setOnClickListener {
            dismiss()
        }

        binding.paymentOptionsDialogFragmentOkButton.setOnClickListener {
            var myChoices = ""
            if (binding.paymentOptionsDialogFragmentCashCheckbox.isChecked) {
                myChoices += " Cash"
            }
            if (binding.paymentOptionsDialogFragmentBankDepositCheckbox.isChecked) {
                myChoices += " Bank Deposit (Naira)"
            }
            if (binding.paymentOptionsDialogFragmentForeignBankDepositCheckbox.isChecked) {
                myChoices += " Bank Deposit (USD, GBP, EUR)"
            }
            if (binding.paymentOptionsDialogFragmentPayoneerCheckbox.isChecked) {
                myChoices += " Payoneer"
            }
            if (binding.paymentOptionsDialogFragmentVcashCheckbox.isChecked) {
                myChoices += " VCash"
            }
            paymentOptionsLiveData.value = myChoices

            this.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
