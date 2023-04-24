package br.senai.jandira.sp.zerowastetest.modelretrofit

data class Address (

    var id: String = "",
    var cep: String = "",
    var logradouro: String = "",
    var bairro: String = "",
    var cidade: String = "",
    var estado: String = "",
    var complemento: String = "",
    var latitude: String = "",
    var longitude: String = "",
    var numero : String = "",
    var apelido: String? = null

)

//bairro: endereco.bairro,
//cep: endereco.cep,
//cidade: endereco.cidade,
//estado: endereco.estado,
//logradouro: endereco.logradouro,
//complemento: endereco.complemento,
//latitude: endereco.latitude,
//longitude: endereco.longitude,
//apelido: endereco.apelido,
//numero: endereco.numero,