package com.decagonhq.clads.models

import android.net.Uri

data class Photo(
    var uri: Uri?,
    var caption: String?
)

data class PhotoData(
    val status: Int,
    val message: String,
    val payload: Payload
)

data class Payload(
    val fileId: String,
    val fileType: String,
    val fileName: String,
    val downloadUri: String,
    val uploadStatus: Boolean
)
