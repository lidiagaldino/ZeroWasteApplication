package br.senai.jandira.sp.zero_wasteapplication.api

import android.content.Context
import br.senai.jandira.sp.zerowastetest.dataSaving.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        fun getRetrofit(path: String, context: Context): Retrofit{
            if (!::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(path)
//                    .client(okhttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }

        private fun okhttpClient(context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .build()
        }

    }

}