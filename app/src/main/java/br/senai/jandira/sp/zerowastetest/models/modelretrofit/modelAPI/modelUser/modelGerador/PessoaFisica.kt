package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelGerador

data class PessoaFisica(
    var cpf: String = "",
    var data_nascimento: String = "",
    var id: Int = 0,
    var id_usuario: Int = 0,
    var nome: String = ""
)