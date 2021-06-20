package com.decagonhq.clads.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccontViewModel : ViewModel() {

    var showroomAddressViewModel = MutableLiveData<String>()
    var workshopAddressViewModel = MutableLiveData<String>()
    var legalStatusViewModelLiveData = MutableLiveData<String>()
    var numberOfEmployeesLiveData = MutableLiveData<String>()
}
