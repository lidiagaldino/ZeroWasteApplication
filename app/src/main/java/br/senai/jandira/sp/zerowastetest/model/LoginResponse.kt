package br.senai.jandira.sp.zerowastetest.model

import br.senai.jandira.sp.zero_wasteapplication.model.User

data class LoginResponse(
    var user: User? = null,
    var token:String = ""
)
