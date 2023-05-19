package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido

data class FinishOrder(
    val created_at: String,
    val finished_at: String,
    val id: Int,
    val id_catador: Int,
    val id_endereco: Int,
    val id_gerador: Int,
    val id_status: Int
)