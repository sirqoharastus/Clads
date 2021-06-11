package com.decagonhq.clads.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditProfileViewmodel :ViewModel() {

    var showroomAddressViewModel = MutableLiveData<String>()
    var workshopAddressViewModel = MutableLiveData<String>()
}