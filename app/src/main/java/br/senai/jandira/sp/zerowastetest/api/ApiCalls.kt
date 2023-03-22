package br.senai.jandira.sp.zero_wasteapplication.api

import br.senai.jandira.sp.zero_wasteapplication.model.RecicladorUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCalls {

    @GET("gerador")
    fun getAll(): Call<List<RecicladorUser>>

    @POST("gerador")
    fun saveReciclador(@Body recicladorUser: RecicladorUser): Call<RecicladorUser>

    @POST("catador")
    fun saveCatador(@Body recicladorUser: RecicladorUser): Call<RecicladorUser>

    @POST("user/auth")
    fun verifyLogin(@Body recicladorUser: RecicladorUser): Call<RecicladorUser>

}