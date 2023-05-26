package br.senai.jandira.sp.zerowastetest.dataSaving

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import br.senai.jandira.sp.zerowastetest.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_EMAIL = ""
        const val USER_ID: Int = 0
//        const val USER_PICTURE = "content://com.android.providers.media.documents/document/image%3A61"
    }

    // Function to save user Login Information

    fun saveUserLogin(email: String){
        val editor = prefs.edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getUserEmail(): String {
        return prefs.getString(USER_EMAIL, "").toString()
    }

    fun saveUserId(id: Int){
        val editor = prefs.edit()
        editor.putInt(USER_ID.toString(), id)
        editor.apply()
    }

    fun getUserId(): Int{
        return prefs.getInt(USER_ID.toString(), 0)
    }

//    fun saveUserPicture(picture: String){
//        val editor = prefs.edit()
//        editor.putString(USER_PICTURE, picture)
//        editor.apply()
//    }
//
//    fun getUserPicture(): String{
//        return prefs.getString(USER_PICTURE, "")
//    }


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