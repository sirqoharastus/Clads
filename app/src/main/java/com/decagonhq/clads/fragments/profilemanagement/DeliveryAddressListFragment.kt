package com.decagonhq.clads.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentDeliveryAddressListBinding
import com.decagonhq.clads.viewmodels.ClientAddressViewModel

class DeliveryAddressListFragment : Fragment() {

    var _binding: FragmentDeliveryAddressListBinding? = null
    val binding get() = _binding!!
    lateinit var addressListViewModel: ClientAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryAddressListBinding.inflate(inflater, container, false)
        val view = binding.root

        addressListViewModel =
            ViewModelProvider(requireActivity()).get(ClientAddressViewModel::class.java)

        addressListViewModel.clientaddress.observe(
            viewLifecycleOwner,
            Observer {
                binding.deliveryAddressListFragmentNoDeliveryAddressTextView.text =
                    "${it.DeliveryAddress}, ${it.City}, ${it.State}."
            }
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // to open the add delivery address fragment
        binding.deliveryAddressListFragmentAddAddressButton.setOnClickListener {
            findNavController().navigate(R.id.addDeliveryAddressFragment)
        }
    }
}
