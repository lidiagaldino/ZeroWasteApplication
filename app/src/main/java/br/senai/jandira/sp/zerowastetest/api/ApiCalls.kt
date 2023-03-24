package br.senai.jandira.sp.zero_wasteapplication.api

import br.senai.jandira.sp.zero_wasteapplication.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCalls {

    @GET("gerador")
    fun getAll(): Call<List<User>>

    @POST("gerador")
    fun saveReciclador(@Body user: User): Call<User>

    @POST("catador")
    fun saveCatador(@Body user: User): Call<User>

    @POST("user/auth")
    fun verifyLogin(@Body user: User): Call<User>

}