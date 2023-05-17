package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class AttResponse(

    var email: String = "",
    var nome: String = "",
    var telefone: String = "",
    var senha: String = "",
    var cpf: String = "",
    var foto: String = ""

)

//{
//    "email": "salsicha@gmail.com",
//    "nome": "Salsicha",
//    "telefone": "(11) 91234-1234",
//    "senha": "salsicha123",
//    "cpf": "12345678900",
//    "foto": "https://firebasestorage.googleapis.com/v0/b/teste---zerowaste.appspot.com/o/images%2Fimagem.jpg?alt=media&token=d07e44e6-b51f-4176-99b6-9fb923a9552a"
//}