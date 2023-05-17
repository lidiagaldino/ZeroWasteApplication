package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class AttRequest(

    var nome: String = "",
    var email: String = "",
    var telefone: String = "",
    var senha: String = "",
    var cpf: String = "",
    var biografia: String = "",
    var foto: String = ""


)

//{
//    "nome": "Salsicha",
//    "email": "salsicha@gmail.com",
//    "telefone": "(11) 91234-1234",
//    "senha": "salsicha123",
//    "cpf": "12345678900",
//    "biografia": "Eu adoro Reciclagem",
//    "foto": "https://firebasestorage.googleapis.com/v0/b/teste---zerowaste.appspot.com/o/images%2Fimagem.jpg?alt=media&token=d07e44e6-b51f-4176-99b6-9fb923a9552a"
//}
