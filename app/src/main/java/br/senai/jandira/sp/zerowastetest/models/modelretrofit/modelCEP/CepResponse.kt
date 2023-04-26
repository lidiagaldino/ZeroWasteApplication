package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelCEP

data class CepResponse(

    var cep: String = "",
    var logradouro: String = "",
    var complemento: String = "",
    var bairro: String = "",
    var localidade: String = "",
    var uf: String = "",
    var ibge: String = "",
    var gia: String = "",
    var ddd: String = "",
    var siafi: String = ""

)



//{
//  "cep": "06315-180",
//  "logradouro": "Rua Ângela Perioto Tolaine",
//  "complemento": "até 399/400",
//  "bairro": "Jardim das Belezas",
//  "localidade": "Carapicuíba",
//  "uf": "SP",
//  "ibge": "3510609",
//  "gia": "2550",
//  "ddd": "11",
//  "siafi": "6313"
//}
