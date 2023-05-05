package br.senai.jandira.sp.zerowastetest.dataSaving

import android.content.Context
import android.content.SharedPreferences
import br.senai.jandira.sp.zerowastetest.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_EMAIL = "Email"
        const val USER_ID: Long = 0
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

    fun saveUserId(id: Long){
        val editor = prefs.edit()
        editor.putLong(USER_ID.toString(), id)
        editor.apply()
    }

    fun getUserId(): Long{
        return prefs.getLong(USER_ID.toString(), 0)
    }


//  Function to save auth token

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun eraseAuthToken() {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, "")
        editor.apply()
    }


//  Function to fetch auth token

    fun fetchAuthToken(): String {
        return prefs.getString(USER_TOKEN, "").toString()
    }
}