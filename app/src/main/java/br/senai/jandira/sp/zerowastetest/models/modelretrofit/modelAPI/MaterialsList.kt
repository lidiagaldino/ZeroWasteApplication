package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

import com.google.gson.annotations.SerializedName

data class MaterialsList(

    @SerializedName("message")
    var materials: List<Materials>? = null

)
