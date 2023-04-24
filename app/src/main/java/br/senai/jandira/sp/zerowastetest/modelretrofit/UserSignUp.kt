package br.senai.jandira.sp.zerowastetest.modelretrofit

import br.senai.jandira.sp.zerowastetest.modelretrofit.Address

data class UserSignUp(

    var nome: String = "",
    val endereco: Address? = null,
    var cpf: String? = null,
    var cnpj: String? = null,
    var email: String = "",
    var phone: String = "",
    var birthDay: String = "",
    var password: String = ""

)

//nome: usuario.nome,
//endereco: {
//    cep: usuario.endereco.cep,
//    logradouro: usuario.endereco.logradouro,
//    bairro: usuario.endereco.bairro,
//    cidade: usuario.endereco.cidade,
//    estado: usuario.endereco.estado,
//    complemento: usuario.endereco.complemento,
//    numero: usuario.endereco.numero,
//    latitude: usuario.endereco.latitude,
//    longitude: usuario.endereco.longitude,
//},
//telefone: usuario.telefone,
//email: usuario.email,
//senha: result.senha,
//cpf: usuario.cpf,
//data_nascimento: usuario.data_nascimento,
//}