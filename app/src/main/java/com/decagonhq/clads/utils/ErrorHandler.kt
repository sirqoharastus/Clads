package com.decagonhq.clads.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.decagonhq.clads.ui.fragments.authentication.LoginScreenFragment
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
                val errorMessage = error?.errors?.message
                if (errorMessage != null) {
                    Toast.makeText(requireContext(), "$errorMessage", Toast.LENGTH_SHORT).show()
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