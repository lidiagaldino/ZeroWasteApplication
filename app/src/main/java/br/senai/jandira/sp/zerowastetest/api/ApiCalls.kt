package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.modelretrofit.UserSignUp
import br.senai.jandira.sp.zerowastetest.modelretrofit.LoginResponse
import br.senai.jandira.sp.zerowastetest.modelretrofit.UserData
import br.senai.jandira.sp.zerowastetest.modelretrofit.UserLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiCalls {

    @POST("gerador")
    fun saveReciclador(@Body userSignUp: UserSignUp): Call<UserData>

    @POST("catador")
    fun saveCatador(@Body userSignUp: UserSignUp): Call<UserData>

    @POST("user/auth")
    fun verifyLogin(@Body userLoginRequest: UserLoginRequest): Call<LoginResponse>

    @GET("user")
    fun getUserData(@Header("Authorization") token: String): Call<UserData>

//    @PUT("user")
//    fun updateUserData(@Header("Authorization") token: String):

}