package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class SignResponseGerador(

    var id: String = "",
    var nome: String = "",
    var endereco: Address? = null,
    var telefone: String = "",
    var email: String = "",
    var senha: String = "",

    var cpf: String? = null,
    var cnpj: String? = null,

    var data_nascimento: String = ""

)

//{
//    "id": 18,
//    "nome": "Miguel B",
//    "endereco": {
//        "cep": "4444444",
//        "logradouro": "Rua da prata",
//        "bairro": "Bairro",
//        "cidade": "Osasco",
//        "estado": "SP",
//        "complemento": " ",
//        "numero": "222",
//        "latitude": "-23.549294",
//        "longitude": "-46.872740"
//    },
//    "telefone": "99999999999999",
//    "email": "lilian@gmail.com",
//    "senha": "$2a$10$ea9QbQNKbUBIuLL6p2E7BuWZjeA64ZJjv0vN6lZrPt.4NQTGbdk8G",
//    "cpf": "1234-5678",
//    "data_nascimento": "2000-02-05T12:01:30.543Z"
//}
