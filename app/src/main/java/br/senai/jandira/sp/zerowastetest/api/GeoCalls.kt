package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeoCalls {

    @GET("json?")
    fun getLatiLong(@Query("q") q: String, @Query("key") key: String): Call<Results>

}