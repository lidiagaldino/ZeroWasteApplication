package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.*
import retrofit2.Call
import retrofit2.http.*

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

    @PUT("user")
    fun updateUserData(@Header("Authorization") token: String, @Body attRequest: AttRequest): Call<AttResponse>

    @POST("endereco/{id_endereco}")
    fun catadoresProximos(@Header("Authorization") authToken: String, @Path("id_endereco") id_endereco: Int): Call<CatadoresProximos>

    @DELETE("materiais/{id_catador}/{id_material}")
    fun deletarMaterial(@Header("Authorization") authToken: String, @Path("id_catador") id_catador: Int, @Path("id_material") id_material: Int): Call<Void>

    @DELETE("endereco/{id_usuario}/{id_endereco}")
    fun deletarEndereco(@Header("Authorization") authToken: String, @Path("id_usuario") id_usuario: Int, @Path("id_endereco") id_endereco: Int): Call<Boolean>

}