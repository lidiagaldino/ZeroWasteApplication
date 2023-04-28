package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class TimeZone(

    var name: String = "",
    var now_in_dst: Int = 0,
    var offset_sec: Int = 0,
    var offset_string: String = "",
    var short_name: String = ""

)

//    "timezone": {
//        "name": "America/Sao_Paulo",
//        "now_in_dst": 0,
//        "offset_sec": -10800,
//        "offset_string": "-0300",
//        "short_name": "BRT"
//    }