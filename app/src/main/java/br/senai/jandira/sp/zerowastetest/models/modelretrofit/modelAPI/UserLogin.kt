package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class UserLoginRequest(
    var email: String = "",
    var senha: String = ""
)