package com.decagonhq.clads.ui.fragments.profilemanagement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentAddDeliveryAddressBinding
import com.decagonhq.clads.utils.AddressData
import com.decagonhq.clads.viewmodels.ClientAddressViewModel

class AddDeliveryAddressFragment : Fragment() {

    private var preBinding: FragmentAddDeliveryAddressBinding? = null
    private val binding get() = preBinding!!
    lateinit var addressViewModel: ClientAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preBinding = FragmentAddDeliveryAddressBinding.inflate(inflater, container, false)
        val view = binding.root

        addressViewModel =
            ViewModelProvider(requireParentFragment()).get(ClientAddressViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addDeliveryAddressSaveButton.setOnClickListener {
            val deliveryAddress =
                binding.addDeliveryAddressFragmentEnterDeliveryAddressEditText.text.toString()
            val city = binding.addDeliveryAddressCityEditText.text.toString()
            val state = binding.addDeliveryAddressFragmentStateAutoCompleteTextView.text.toString()

            val newClientAddress = AddressData(deliveryAddress, city, state)

            // add the client address via the view model
            addressViewModel.getClientAddress(newClientAddress)

            binding.addDeliveryAddressCityEditText.text?.clear()
            binding.addDeliveryAddressFragmentEnterDeliveryAddressEditText.text?.clear()

            findNavController().navigate(R.id.deliveryAddressListFragment2)
        }
    }

    // updating the autotextview
    override fun onResume() {
        super.onResume()
        val states = resources.getStringArray(R.array.states_array)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.add_measurement_autotextview, states)
        binding.addDeliveryAddressFragmentStateAutoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        preBinding = null
    }
}
