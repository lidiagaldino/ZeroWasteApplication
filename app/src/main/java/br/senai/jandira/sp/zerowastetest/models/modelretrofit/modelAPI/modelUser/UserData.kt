package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador.Catador
import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelGerador.Gerador

data class UserData(

    var id: Int = 0,
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