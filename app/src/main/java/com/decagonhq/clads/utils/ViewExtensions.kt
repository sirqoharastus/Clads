package com.decagonhq.clads.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewExtensions {

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

//    fun View.showKeyboard() {
//        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.showSoftInput(windowToken, 0)
//    }
}