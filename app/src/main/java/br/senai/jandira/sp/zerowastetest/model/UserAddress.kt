package br.senai.jandira.sp.zerowastetest.model

import br.senai.jandira.sp.zero_wasteapplication.model.Address

data class UserAddress(
    var id: String = "",
    var id_usuario: String = "",
    var id_endereco: String = "",
    var endereco: Address? = null
)