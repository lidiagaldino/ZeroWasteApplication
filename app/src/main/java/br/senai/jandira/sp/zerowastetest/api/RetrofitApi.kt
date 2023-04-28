package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instanceMain: Retrofit
        private lateinit var instanceCep: Retrofit
        private lateinit var instanceGeoCode: Retrofit

        fun getMainApi(): Retrofit{
            if (!Companion::instanceMain.isInitialized) {
                instanceMain = Retrofit
                    .Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instanceMain
        }

        fun getCepApi(): Retrofit{
            if (!Companion::instanceCep.isInitialized) {
                instanceCep = Retrofit
                    .Builder()
                    .baseUrl(Constants.CEP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instanceCep
        }

        fun getGeoCodeApi(): Retrofit{
            if (!Companion::instanceGeoCode.isInitialized) {
                instanceGeoCode = Retrofit
                    .Builder()
                    .baseUrl(Constants.GEO_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instanceGeoCode
        }

    }
}