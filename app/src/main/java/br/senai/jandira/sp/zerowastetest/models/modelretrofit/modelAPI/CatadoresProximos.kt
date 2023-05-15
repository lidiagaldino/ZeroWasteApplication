package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelAPI

data class CatadoresProximos(

    var id_catador: Int = 0,
    var id_usuario: Int = 0,
    var logradouro: String = "",
    var cidade: String = "",
    var numero: String = "",
    var foto: String = "",
    var nome: String = "",
    var nome_fantasia: String? = null,
    var distance: Float? = 0.0f

)

//    {
//        "id_catador": 1,
//        "id_usuario": 1,
//        "logradouro": "Estrada das Pitas",
//        "cidade": "Barueri",
//        "numero": "952",
//        "foto": "https://cdn-icons-png.flaticon.com/512/5231/5231019.png",
//        "nome": "Lídia Galdino",
//        "nome_fantasia": null,
//        "distance": 103.51182885768662
//    }