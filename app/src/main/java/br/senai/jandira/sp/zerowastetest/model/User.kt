package br.senai.jandira.sp.zero_wasteapplication.model

data class User(

    var id: Long = 0,
    var name: String = "",
    var cpf: String = "",
    var email: String = "",
    var phone: String = "",
    val endereco: Address? = null,
    var birthDay: String = "",
    var password: String = ""

)

//  {
//      "nome": "Larissa",
//      "endereco": {
//          "cep": "4444444",
//          "logradouro": "Rua da prata",
//          "bairro": "Bairro",
//          "cidade": "Osasco",
//          "estado": "SP",
//          "complemento": " "
//      },
//      "telefone": "99999999999999",
//      "email": "larissa@gmail.com",
//      "senha": "larissa123",
//      "materiais": ["4bae7e1f-1795-4bed-abd3-650f8df2502c"],
//      "cnpj": "1234-5679",
//      "data_nascimento": "2000-02-05T12:01:30.543Z"
//  }
