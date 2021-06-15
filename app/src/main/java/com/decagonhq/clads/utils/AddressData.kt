package com.decagonhq.clads.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressData(
    val DeliveryAddress: String,
    val City: String,
    val State: String
) : Parcelable