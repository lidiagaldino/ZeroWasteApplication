package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido

import br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.Address

data class Pedido(
    val FilaPedidoCatador: List<FilaPedidoCatador>,
    val MateriaisPedido: List<MateriaisPedido>,
    val created_at: String,
    val endereco: Address,
    val finished_at: String? = null,
    val id: Int,
    var id_catador: Int? = null,
    val id_endereco: Int,
    val id_gerador: Int,
    var id_status: Int,
    val tbl_gerador: TblGerador?
)