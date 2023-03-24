package br.senai.jandira.sp.zero_wasteapplication.model

data class Address (

    var cep: String = "",
    var logradouro: String = "",
    var bairro: String = "",
    var cidade: String = "",
    var estado: String = "",
    var complemento: String = ""

)

//  "endereco": {
//      "cep": "4444444",
//      "logradouro": "Rua da prata",
//      "bairro": "Bairro",
//      "cidade": "Osasco",
//      "estado": "SP",
//      "complemento": " "
//  }