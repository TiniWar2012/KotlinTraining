package com.example.android.training

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(
    private val preference: SharedPreferences
) {
    companion object {
        const val PREFERENCE_NAME = "SharedPreference"
        const val PREFERENCE_LANGUAGE = "language"
        const val PREFERENCE_STATUS = "language"
    }

    fun getLoginCountry(): String {
        return preference.getString(PREFERENCE_LANGUAGE, "en").orEmpty()
    }

    fun setLoginCountry(language: String) {
        val editor = preference.edit()
        editor.putString(PREFERENCE_LANGUAGE, language)
        editor.apply()
    }

    fun setLoginStatus(token: String){
        val status = preference.edit()
        status.putString(PREFERENCE_STATUS, token)
        status.apply()
    }

    fun getLoginStatus(): String {
        return preference.getString(PREFERENCE_STATUS,"").orEmpty()
    }
}