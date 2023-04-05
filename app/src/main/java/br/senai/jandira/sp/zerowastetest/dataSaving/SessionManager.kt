package br.senai.jandira.sp.zerowastetest.dataSaving

import android.content.Context
import android.content.SharedPreferences
import br.senai.jandira.sp.zerowastetest.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_EMAIL = "Email"
    }

    // Function to save user Login Information

    fun saveUserLogin(email: String){
        val editor = prefs.edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getUserEmail(): String {
        return prefs.getString(USER_EMAIL, "Email").toString()
    }


//  Function to save auth token

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }


//  Function to fetch auth token

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, "").toString()
    }
}