package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser.modelCatador

data class CatadoresProximos(

    val id_catador: Int,
    val id_usuario: Int,
    val logradouro: String,
    val cidade: String,
    val numero: String,
    val foto: String,
    val nome: String?,
    val nome_fantasia: String?,
    val distance: Double

)