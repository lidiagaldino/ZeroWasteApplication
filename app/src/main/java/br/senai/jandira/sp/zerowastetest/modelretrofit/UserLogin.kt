package br.senai.jandira.sp.zerowastetest.modelretrofit

data class UserLoginRequest(
    var email: String = "",
    var senha: String = ""
)