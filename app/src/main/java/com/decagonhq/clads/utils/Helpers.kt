package com.decagonhq.clads.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.ui.activities.AuthActivity
import retrofit2.Retrofit

fun Fragment.handleApiError(
    activity: Activity,
    failure: Resource.Failure,
    retrofit: Retrofit,
    view: View,
    message: String = "",
    navDestinationId: Int = 0
) {
    val errorUtils = ErrorUtils(retrofit)
    when {
        failure.isNetworkError -> {
            Toast.makeText(requireContext(), "Connection time out or bad network connection. Try again", Toast.LENGTH_SHORT).show()
        }
        else -> {
            try {
                val error = failure.errorBody?.let { it1 -> errorUtils.parseError(it1) }
                val errorMessage = error?.errors?.message
                if (failure.errorCode == 401) {
                    logOut(activity)
                }
                if (errorMessage == message) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(navDestinationId)
                } else {
                    println(errorMessage)
                    if (errorMessage != null) {
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                            .show()
                        // showSnackBar(requireView(), errorMessage)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Bad request. Check input again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
fun Fragment.handleApiError(
    failure: Resource.Failure,
    retrofit: Retrofit,
    view: View,
    message: String = "",
    navDestinationId: Int = 0,
    data: String? = null,
    dataType: String? = null
) {
    val errorUtils = ErrorUtils(retrofit)
    when {
        failure.isNetworkError -> {
            Toast.makeText(requireContext(), "Connection time out or bad network connection. Try again", Toast.LENGTH_SHORT).show()
        }
        else -> {
            try {
                val error = failure.errorBody?.let { it1 -> errorUtils.parseError(it1) }
                val errorMessage = error?.errors?.message
                if (errorMessage == message) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                } else {
                    if (errorMessage != null) {
                        Toast.makeText(requireContext(), "$errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Bad request. Check input again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun Activity.handleApiError(
    failure: Resource.Failure,
    retrofit: Retrofit,
    view: View
) {
    val errorUtils = ErrorUtils(retrofit)
    when {
        failure.isNetworkError -> {
            Toast.makeText(
                applicationContext, "Connection time out or bad network connection. Try again", Toast.LENGTH_SHORT
            ).show()
        }
        else -> {
            try {
                val error = failure.errorBody?.let { it1 -> errorUtils.parseError(it1) }
                error?.errors?.let {
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Bad request. Check input again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun Fragment.logOut(activity: Activity) {
    Intent(activity, AuthActivity::class.java).also {
        startActivity(it)
        requireActivity().finish()
    }
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retrofit: Retrofit,
    view: View,
) {
    val errorResponseUtil = ErrorUtils(retrofit)
    when {
        failure.isNetworkError -> {
            Toast.makeText(requireContext(), "Poor Internet Connection. Retry", Toast.LENGTH_SHORT).show()
        }
        else -> {
            try {
                val error = failure.errorBody?.let { it1 -> errorResponseUtil.parseError(it1) }
                val errorMessage = error?.errors?.message
                if (errorMessage != null) {
                    Toast.makeText(requireContext(), "$errorMessage", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Something went wrong!... Retry", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Bad request. Check Input again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
