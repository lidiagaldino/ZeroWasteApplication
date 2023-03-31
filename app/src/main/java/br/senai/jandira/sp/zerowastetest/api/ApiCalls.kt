package br.senai.jandira.sp.zero_wasteapplication.api

import br.senai.jandira.sp.zero_wasteapplication.model.UserSignUp
import br.senai.jandira.sp.zerowastetest.model.LoginResponse
import br.senai.jandira.sp.zerowastetest.model.UserData
import br.senai.jandira.sp.zerowastetest.model.UserLoginRequest
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
    fun getUserData(): Call<UserData>

}