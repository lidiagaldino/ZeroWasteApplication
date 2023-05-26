package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.Address

data class UserAddress(

    var id: Int = 0,
    var id_usuario: Int = 0,
    var id_endereco: Int = 0,
    var endereco: Address? = null

)