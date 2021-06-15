package com.decagonhq.clads.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MeasurementData(
    var measurementName: String,
    var measurementValue: String
) : Parcelable
