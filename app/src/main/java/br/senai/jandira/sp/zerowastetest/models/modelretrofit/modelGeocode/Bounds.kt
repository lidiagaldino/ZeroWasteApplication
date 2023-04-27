package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class Bounds(

    var northeast: NorthEast? = null,
    var southwest: SouthWest? = null

)

//            "bounds": {
//                "northeast": {
//                    "lat": -23.520225,
//                    "lng": -46.8403544
//                },
//                "southwest": {
//                    "lat": -23.5235272,
//                    "lng": -46.8403991
//                }
//            }