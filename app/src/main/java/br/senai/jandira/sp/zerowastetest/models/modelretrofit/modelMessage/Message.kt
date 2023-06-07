package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelMessage

data class Message(
    val _id: String,
    val from: Int,
    val to: Int,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int,
    val message: Text
)
