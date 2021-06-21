package com.decagonhq.clads.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager@Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveToSharedPreference(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun loadFromSharedPreference(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
