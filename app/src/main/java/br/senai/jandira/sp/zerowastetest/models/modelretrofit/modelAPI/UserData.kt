package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class UserData(

    var id: String = "",
    var email: String = "",
    var telefone: String = "",
    var catador: List<Catador>? = null,
    var gerador: List<Gerador>? = null,
    var pessoa_fisica: List<PessoaFisica>? = null,
    var pessoa_juridica: List<PessoaJuridica>? = null,
    var endereco_usuario: List<UserAddress>? = null,
    var senha: String = "",
    var biografia: String = "",
    var foto: String = ""

)

//     var cpf: String = "",
