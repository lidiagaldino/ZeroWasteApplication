package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser

data class Address (

    var id: Int = 0,
    var cep: String = "",
    var logradouro: String = "",
    var bairro: String = "",
    var cidade: String = "",
    var estado: String = "",
    var complemento: String? = null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var numero : String = "",
    var apelido: String? = null,
    var id_usuario: Int? = null

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