package br.senai.jandira.sp.zerowastetest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object{
        private lateinit var instance: Retrofit

        fun getRetrofit(path: String): Retrofit{
            if (!Companion::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(path)
//                    .client(okhttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }


//        private fun okhttpClient(context: Context): OkHttpClient {
//            return OkHttpClient.Builder()
//                .addInterceptor(AuthInterceptor(context))
//                .build()
//        }


    }
}