package br.senai.jandira.sp.zerowastetest.model

data class UserData(

    var id: Long = 0,
    var email: String = "",
    var telephone: String = "",
    var catador: Catador? = null,
    var gerador: Gerador? = null,
    var pessoa_fisica: PessoaFisica? = null,
    var pessoa_juridica: PessoaJuridica? = null,
    var endereco_usuario: UserAddress? = null,
    var password: String = ""

)

//     var cpf: String = "",
