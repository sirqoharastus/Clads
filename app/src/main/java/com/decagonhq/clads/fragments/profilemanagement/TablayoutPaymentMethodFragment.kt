package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentTablayoutPaymentMethodBinding
import com.decagonhq.clads.fragments.editprofiledialogfragments.AddPaymentTermsDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.PaymentOptionsDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.PaymentTermsDialogFragment
import com.decagonhq.clads.models.EditProfileViewmodel

class TablayoutPaymentMethodFragment : Fragment() {
    // declaring binding variables
    var _binding: FragmentTablayoutPaymentMethodBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: EditProfileViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating layout when the view is created
        _binding = FragmentTablayoutPaymentMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EditProfileViewmodel::class.java)

        val fab = binding.paymentMethodsFramentFab
        val paymentTextTermsTextView = binding.paymentTermsTextTextview
        val paymentTermsTextview = binding.paymentTermsTextview
        val paymentOptionsTextTextview = binding.paymentOptionsTextTextview
        val paymentOptionsTextview = binding.paymentOptionsTextview

        // setting account profile values on click to inflate respective dialogs in the process
        paymentTextTermsTextView.setOnClickListener {
            val paymentTermsDialogFragment = PaymentTermsDialogFragment()
            paymentTermsDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.payment_terms_dialog_fragment_payment_terms_dialog_fragment_text))
        }
        paymentOptionsTextTextview.setOnClickListener {
            val paymentOptionsDialogFragment = PaymentOptionsDialogFragment()
            paymentOptionsDialogFragment.show(requireActivity().supportFragmentManager, getString(R.string.payment_options_dialog_fragment_payment_options_dialog_fragment_text))
        }
        fab.setOnClickListener {
            val addPaymentOptionsDialogFragment = AddPaymentTermsDialogFragment()
            addPaymentOptionsDialogFragment.show(requireActivity().supportFragmentManager, getString(
                            R.string.add_payment_dialog_fragment_add_payment_dialog_fragment_text))
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
