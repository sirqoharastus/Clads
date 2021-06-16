package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentPaymentMethodBinding
import com.decagonhq.clads.dialogs.AddPaymentTermsDialogFragment
import com.decagonhq.clads.dialogs.PaymentOptionsDialogFragment
import com.decagonhq.clads.dialogs.PaymentTermsDialogFragment
import com.decagonhq.clads.viewmodels.AccontViewModel

class PaymentMethodFragment : Fragment() {
    var _binding: FragmentPaymentMethodBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: AccontViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = binding.paymentMethodsFramentFab
        val paymentTermsTextTextview = binding.paymentTermsTextTextview
        val paymentOptionsTextTextview = binding.paymentOptionsTextTextview

        paymentTermsTextTextview.setOnClickListener {
            val paymentTermsDialogFragment = PaymentTermsDialogFragment()
            paymentTermsDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.payment_terms_dialog_fragment_payment_terms_dialog_fragment_text))

            paymentTermsDialogFragment.paymentTermsLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.paymentTermsTextTextview.text = it
                }
            )
        }

        paymentOptionsTextTextview.setOnClickListener {
            val paymentOptionsDialogFragment = PaymentOptionsDialogFragment()
            paymentOptionsDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.payment_options_dialog_fragment_payment_options_dialog_fragment_text))
            paymentOptionsDialogFragment.paymentOptionsLiveData.observe(
                viewLifecycleOwner,
                {
                    binding.paymentOptionsTextTextview.text = it
                }
            )
        }

        fab.setOnClickListener {
            val addPaymentDialogFragment = AddPaymentTermsDialogFragment()
            addPaymentDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.add_payment_dialog_fragment_add_payment_dialog_fragment_text))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
