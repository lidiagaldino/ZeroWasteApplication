package br.senai.jandira.sp.zerowastetest.model

data class UserLoginRequest(
    var email: String = "",
    var senha: String = ""
)