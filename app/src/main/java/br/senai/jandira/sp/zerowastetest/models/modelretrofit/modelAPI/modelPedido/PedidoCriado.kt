package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI.modelPedido

data class PedidoCriado(

    val id: Int = 0,
    val id_endereco: Int,
    val id_gerador: Int,
    val id_materiais: List<Int>,
    val status: Int

)
