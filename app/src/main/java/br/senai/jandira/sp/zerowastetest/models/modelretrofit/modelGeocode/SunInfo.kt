package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class SunInfo(

    var rise: Rise? = null,
    var set: SetData? = null

)

//    "sun": {
//        "rise": {
//          "apparent": 1682587620,
//          "astronomical": 1682583060,
//          "civil": 1682586240,
//          "nautical": 1682584620
//        },
//        "set": {
//          "apparent": 1682628180,
//          "astronomical": 1682632740,
//          "civil": 1682629560,
//          "nautical": 1682631180
//        }
//    }