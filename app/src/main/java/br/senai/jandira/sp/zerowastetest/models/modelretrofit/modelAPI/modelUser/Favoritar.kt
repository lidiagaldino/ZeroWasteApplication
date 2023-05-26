package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelUser

data class Favoritar(
    val id: Int = 0,
    val id_catador: Int,
    val id_gerador: Int,
    val action: String = ""
)