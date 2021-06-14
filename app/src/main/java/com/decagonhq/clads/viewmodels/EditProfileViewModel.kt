package com.decagonhq.clads.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.models.Specialty

class EditProfileViewModel() : ViewModel() {

    private val _specialty = MutableLiveData<Specialty>()
    val specialty: LiveData<Specialty> get() = _specialty

    private val _deliveryLeadTime = MutableLiveData<String>()
    val deliveryLeadTime: LiveData<String> get() = _deliveryLeadTime

    private val _obiomaTrained = MutableLiveData<String>()
    val obiomaTrained: LiveData<String> get() = _obiomaTrained

    fun addSpecialty(specialty: Specialty) {
        this._specialty.value = specialty
    }

    fun addDeliveryTime(deliveryTime: String) {
        this._deliveryLeadTime.value = deliveryTime
    }

    fun obiomaTrained(obiomaTrained: String) {
        this._obiomaTrained.value = obiomaTrained
    }
}
