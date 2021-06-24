package com.decagonhq.clads.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.PaymentTermsDialogFragmentBinding

class PaymentTermsDialogFragment : DialogFragment() {
    // deeclaring binding variables
    var _binding: PaymentTermsDialogFragmentBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout when the view is created
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
        _binding = PaymentTermsDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    var paymentTermsLiveData = MutableLiveData<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setting cancel button to dismiss dialog when clicked
        binding.paymentTermsDialogCancelButton.setOnClickListener {
            dismiss()
        }

        selectOption()
    }

    private fun selectOption() {
        binding.paymentTermsRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.payment_terms_dialog_100pc_checkBox) {
                paymentTermsLiveData.value =
                    getString(R.string.payment_terms_dialog_fragment_100pc_deposit_text)
                this.dismiss()
            } else if (checkedId == R.id.payment_terms_dialog_50pc_checkBox) {
                paymentTermsLiveData.value =
                    getString(R.string.payment_terms_dialog_fragment_50pc_deposit_text)
                this.dismiss()
            } else if (checkedId == R.id.payment_terms_dialog_0pc_checkBox) {
                paymentTermsLiveData.value =
                    getString(R.string.payment_terms_dialog_fragment_0pc_deposit_text)
                this.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
