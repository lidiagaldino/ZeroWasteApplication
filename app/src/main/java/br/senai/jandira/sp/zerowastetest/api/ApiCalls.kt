package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiCalls {

    @POST("gerador")
    fun saveGeradorFisico(@Body newGeradorFisico: NewGeradorFisico): Call<SignResponseGerador>

    @POST("gerador")
    fun saveGeradorJuridico(@Body newGeradorJuridico: NewGeradorJuridico): Call<SignResponseGerador>

    @POST("catador")
    fun saveCatadorFisico(@Body newCatadorFisico: NewCatadorFisico): Call<SignResponseCatador>

    @POST("catador")
    fun saveCatadorJuridico(@Body newCatadorFisico: NewCatadorJuridico): Call<SignResponseCatador>

    @POST("user/auth")
    fun verifyLogin(@Body userLoginRequest: UserLoginRequest): Call<LoginResponse>

    @GET("user")
    fun getUserData(@Header("Authorization") token: String): Call<UserData>

    @GET("materiais")
    fun getMateriaisList(): Call<MaterialsList>

    @POST("endereco")
    fun newEnderecoUser(@Header("Authorization") authToken: String, @Body address: Address): Call<UserAddress>

//    @PUT("user")
//    fun updateUserData(@Header("Authorization") token: String):

}