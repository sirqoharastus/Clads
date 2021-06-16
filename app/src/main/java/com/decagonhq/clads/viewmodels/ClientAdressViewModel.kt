package com.decagonhq.clads.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.utils.AddressData

class ClientAddressViewModel : ViewModel() {

    val _clientAddress = MutableLiveData<AddressData>()
    val clientaddress: LiveData<AddressData> get() = _clientAddress

    fun getClientAddress(address: AddressData) {
        _clientAddress.value = address
    }
}
