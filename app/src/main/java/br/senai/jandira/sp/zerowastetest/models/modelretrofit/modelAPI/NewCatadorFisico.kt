package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class NewCatadorFisico(

    var nome: String = "",
    val endereco: Address? = null,
    var telefone: String = "",
    var email: String = "",
    var senha: String = "",
    var materiais: List<Int?>? = null,

    var cpf: String? = null,

    var data_nascimento: String = "",

    )

//{
//    "nome": "Miguel",
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
//    "email": "miguel@gmail.com",
//    "senha": "miguel123",
//    "materiais": ["3"],
//    "cpf":"43524382842",
//    "data_nascimento": "2000-02-05T12:01:30.543Z"
//}