package com.decagonhq.clads.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleData(
    val title: String,
    val body: String
) : Parcelable
