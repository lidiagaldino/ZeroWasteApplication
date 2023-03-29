package br.senai.jandira.sp.zerowastetest.model

import br.senai.jandira.sp.zero_wasteapplication.model.UserSignUp

data class LoginResponse(
    var user: UserData? = null,
    var token:String = ""
)
