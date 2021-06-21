package com.decagonhq.clads.utils

import androidx.lifecycle.MutableLiveData

object DataListener {
    var imageListener = MutableLiveData<Boolean>()

    init {
        imageListener.value = false
    }
}