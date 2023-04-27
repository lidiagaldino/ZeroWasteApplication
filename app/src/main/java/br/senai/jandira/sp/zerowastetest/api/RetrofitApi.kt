package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        fun getMainApi(): Retrofit{
            if (!Companion::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

        fun getCepApi(): Retrofit{
            if (!Companion::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(Constants.CEP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

        fun getGeoCodeApi(): Retrofit{
            if (!Companion::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(Constants.GEO_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

    }
}