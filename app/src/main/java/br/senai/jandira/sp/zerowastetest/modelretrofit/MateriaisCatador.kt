package br.senai.jandira.sp.zerowastetest.modelretrofit

data class MateriaisCatador(

    var id: String = "",
    var id_materiais: String = "",
    var id_catador: String = "",
    var material: List<Materials>? = null

)

//{
////                        "id": "0a923586-87ba-4d48-883d-c50331667f55",
////                        "id_materiais": "c9dfd397-abd6-42e8-9d72-61edd880b328",
////                        "id_catador": "fc88207f-fd8e-4d87-9745-95a6fbfa7295",
////                        "material": {
////                            "id": "c9dfd397-abd6-42e8-9d72-61edd880b328",
////                            "nome": "Ferro"
////                        }
////                    }
