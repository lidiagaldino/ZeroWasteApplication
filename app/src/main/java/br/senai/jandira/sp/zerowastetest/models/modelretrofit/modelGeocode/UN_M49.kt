package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class UN_M49(

    var regions: Regions? = null,
    var statistical_groupings: List<String>? = null

)

//    "UN_M49": {
//        "regions": {
//        "AMERICAS": "019",
//        "BR": "076",
//        "LATIN_AMERICA": "419",
//        "SOUTH_AMERICA": "005",
//        "WORLD": "001"
//        },
//        "statistical_groupings": [
//        "LEDC"
//        ]
//    }