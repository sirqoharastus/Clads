package com.decagonhq.clads.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit

fun Fragment.handleApiErrors(
    failure: Resource.Failure,
    retrofit: Retrofit,
    view: View
) {
    val errorResponseUtil = ErrorUtils(retrofit)

    when {
        failure.isNetworkError -> requireView().snackbar(
            "Please check your internet connection",
        )
        else -> {
            try {
                val error = failure.errorBody?.let { it1 -> errorResponseUtil.parseError(it1) }
                val errorMessage = error?.message
                if (errorMessage != null) {
                    requireView().snackbar(errorMessage)
                } else {
                    requireView().snackbar("Something went wrong!... Retry")
                }
            } catch (e: Exception) {
                requireView().snackbar("Bad request. Check Input again.")
            }
        }
    }
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}