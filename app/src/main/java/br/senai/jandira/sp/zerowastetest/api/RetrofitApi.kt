package br.senai.jandira.sp.zero_wasteapplication.api

import android.provider.SyncStateContract
import br.senai.jandira.sp.zero_wasteapplication.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        fun getRetrofit(path: String): Retrofit{
            if (!::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }
    }

}