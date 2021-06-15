package com.decagonhq.clads.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagonhq.clads.utils.MeasurementData

class MeasurementViewModel : ViewModel() {

    val _clientMeasurementLiveData = MutableLiveData<MeasurementData>()
    val clientMeasurementLiveData: LiveData<MeasurementData> get() = _clientMeasurementLiveData


    fun getClientMeasurement(measurement: MeasurementData) {
        _clientMeasurementLiveData.value = measurement
    }

}