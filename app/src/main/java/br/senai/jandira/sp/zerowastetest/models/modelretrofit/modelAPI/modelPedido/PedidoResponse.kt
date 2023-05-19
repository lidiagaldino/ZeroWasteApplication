package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.Address


data class PedidoResponse(
    val created_at: String,
    val endereco: Address,
    val finished_at: String? = null,
    val id: Int,
    val id_catador: Int,
    val id_gerador: Int,
    val id_material: List<MateriaisPedido>,
    val id_status: Int,
    val distancia: Int?
)