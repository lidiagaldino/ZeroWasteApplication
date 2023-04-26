package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class LoginResponse(

    var user: UserData? = null,
    var token:String = ""

)
