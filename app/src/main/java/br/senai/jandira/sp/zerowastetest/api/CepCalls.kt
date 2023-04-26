package br.senai.jandira.sp.zerowastetest.api

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelCEP.CepResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepCalls {

    @GET("{cep}/json")
    fun getAddressInfo(@Path("cep") cep: String): Call<CepResponse>

}