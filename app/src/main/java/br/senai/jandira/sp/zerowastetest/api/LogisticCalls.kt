package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido.FinishOrder
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido.PedidoResponse
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido.PedidoReturn
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode.Geometry
import retrofit2.Call
import retrofit2.http.*

interface LogisticCalls {
    @GET("/order")
    fun getOrder(@Header("Authorization") authToken: String): Call<PedidoReturn>

    @PUT("/order/{id}")
    fun acceptOrder(@Header("Authorization") authToken: String, @Path("id") id: Int?): Call<PedidoResponse>

    @PUT("/order/finish/{id}")
    fun finishOrder(@Header("Authorization") authToken: String, @Path("id") id: Int?, @Body latLong: Geometry): Call<FinishOrder>

    @PUT("/order/deny/{id}")
    fun denyOrder(@Header("Authorization") authToken: String, @Path("id") id: Int?): Call<Void>

}