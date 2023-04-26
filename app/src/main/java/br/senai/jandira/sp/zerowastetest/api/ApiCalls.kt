package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.NewCatador
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.LoginResponse
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserData
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.UserLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiCalls {

    @POST("gerador")
    fun saveReciclador(@Body newCatador: NewCatador): Call<UserData>

    @POST("catador")
    fun saveCatador(@Body newCatador: NewCatador): Call<UserData>

    @POST("user/auth")
    fun verifyLogin(@Body userLoginRequest: UserLoginRequest): Call<LoginResponse>

    @GET("user")
    fun getUserData(@Header("Authorization") token: String): Call<UserData>

//    @PUT("user")
//    fun updateUserData(@Header("Authorization") token: String):

}