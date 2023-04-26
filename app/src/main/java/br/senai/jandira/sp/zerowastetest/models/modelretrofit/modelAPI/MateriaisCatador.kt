package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class MateriaisCatador(

    var id: String = "",
    var id_materiais: String = "",
    var id_catador: String = "",
    var material: Materials? = null

)

//  "materiais_catador": [
//      {
//          "id": "326185ac-8de0-403e-89d5-56a9c6e5599f",
//          "id_materiais": "c9dfd397-abd6-42e8-9d72-61edd880b328",
//          "id_catador": "6d1ef35c-4ab4-4279-81c9-db0b320e0e1c",
//          "material": {
//          "id": "c9dfd397-abd6-42e8-9d72-61edd880b328",
//          "nome": "Ferro"
//          }
//      }
