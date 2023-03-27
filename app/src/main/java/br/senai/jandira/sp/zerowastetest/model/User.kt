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

//"user": {
//    "id": "481aa0b6-0a8d-4039-8cea-12b7b5fce35e",
//    "email": "miguel@gmail.com",
//    "telefone": "98765432123456",
//    "catador": [],
//    "gerador": [
//      {
//          "id": "548beb8c-b220-4e4c-a376-04398980eb55",
//          "id_usuario": "481aa0b6-0a8d-4039-8cea-12b7b5fce35e"
//      }
//    ],
//    "pessoa_fisica": [
//      {
//           "id": "1c1c35ab-6fdb-4650-93ee-417efbefe624",
//           "cpf": "47189167733",
//           "nome": "Miguel",
//           "data_nascimento": "2000-02-05T12:01:30.543Z",
//           "id_usuario": "481aa0b6-0a8d-4039-8cea-12b7b5fce35e"
//      }
//    ],
//    "pessoa_juridica": [],
//    "endereco_usuario": [
//          {
//          "id": "07da6b21-c934-4e54-baa0-e65606eb7b13",
//          "id_endereco": "f9899502-fd4c-4e5b-8b54-92dfc88f0835",
//          "id_usuario": "481aa0b6-0a8d-4039-8cea-12b7b5fce35e",
//          "endereco": {
//              "id": "f9899502-fd4c-4e5b-8b54-92dfc88f0835",
//              "logradouro": "Estrada das Pitas",
//              "bairro": "Parque Viana",
//              "cidade": "Barueri",
//              "estado": "SP",
//              "cep": "06449-300",
//              "complemento": "134C"
//              }
//          }
//      ]
//}
