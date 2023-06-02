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
        const val ID_TYPE: Long = 0
    }


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

    fun saveUserIdType(id_gerador: Int){
        val editor = prefs.edit()
        editor.putInt(ID_TYPE.toString(), id_gerador)
        editor.apply()
    }

    fun getUserIdType(): Int{
        return prefs.getInt(ID_TYPE.toString(), 0)
    }


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