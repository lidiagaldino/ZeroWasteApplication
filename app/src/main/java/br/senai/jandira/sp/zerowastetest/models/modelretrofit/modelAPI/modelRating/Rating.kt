package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelRating

data class Rating(
    val id: Int = 0,
    val id_catador: Int,
    val id_gerador: Int,
    val nota: Int
)
