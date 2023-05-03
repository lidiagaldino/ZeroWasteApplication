package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiCalls {

    @GET("")

    @POST("gerador")
    fun saveReciclador(@Body newCatador: NewCatador): Call<UserData>

    @POST("catador")
    fun saveCatador(@Body newCatador: NewCatador): Call<SignResponseCatador>

    @POST("user/auth")
    fun verifyLogin(@Body userLoginRequest: UserLoginRequest): Call<LoginResponse>

    @GET("user")
    fun getUserData(@Header("Authorization") token: String): Call<UserData>

    @GET("materiais")
    fun getMateriaisList(): Call<MaterialsList>

//    @PUT("user")
//    fun updateUserData(@Header("Authorization") token: String):

}