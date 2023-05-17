package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class Address (

    var id: Int = 0,
    var cep: String = "",
    var logradouro: String = "",
    var bairro: String = "",
    var cidade: String = "",
    var estado: String = "",
    var complemento: String? = null,
    var latitude: Float? = 0.0f,
    var longitude: Float? = 0.0f,
    var numero : String = "",
    var apelido: String? = null,
    var id_usuario: Long? = null

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