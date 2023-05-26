package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelGerador

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.Address

data class SignResponseGerador(

    var id: Long? = null,
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
//    "id": 24,
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
//    "senha": "$2a$10$Sbqhl4/suCNqA.o4yh/qJObcdfyEZYf8Ria7FfzaOX68B7ZzGVNvy",
//    "cpf": "1234-5678",
//    "data_nascimento": "2000-02-05T12:01:30.543Z"
//}
