package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.decagonhq.clads.databinding.FragmentTablayoutPaymentMethodBinding
import com.decagonhq.clads.fragments.editprofiledialogfragments.AddPaymentTermsDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.PaymentOptionsDialogFragment
import com.decagonhq.clads.fragments.editprofiledialogfragments.PaymentTermsDialogFragment

class TablayoutPaymentMethodFragment : Fragment() {
    // declaring binding variables
    var _binding: FragmentTablayoutPaymentMethodBinding? = null
    val binding get() = _binding!!
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
        val fab = binding.paymentMethodsFramentFab
        val paymentTextTermsTextView = binding.paymentTermsTextTextview
        val paymentTermsTextview = binding.paymentTermsTextview
        val paymentOptionsTextTextview = binding.paymentOptionsTextTextview
        val paymentOptionsTextview = binding.paymentOptionsTextview

        // setting account profile values on click to inflate respective dialogs in the process
        paymentTextTermsTextView.setOnClickListener {
            val paymentTermsDialogFragment = PaymentTermsDialogFragment()
            paymentTermsDialogFragment.show(requireActivity().supportFragmentManager, "payment terms dialog fragment")
        }
        paymentOptionsTextTextview.setOnClickListener {
            val paymentOptionsDialogFragment = PaymentOptionsDialogFragment()
            paymentOptionsDialogFragment.show(requireActivity().supportFragmentManager, "payment options dialog fragment")
        }
        fab.setOnClickListener {
            val addPaymentOptionsDialogFragment = AddPaymentTermsDialogFragment()
            addPaymentOptionsDialogFragment.show(requireActivity().supportFragmentManager, "add payment dialog fragment")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
