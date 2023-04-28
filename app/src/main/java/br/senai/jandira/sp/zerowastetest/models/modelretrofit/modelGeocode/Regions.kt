package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

import com.google.gson.annotations.SerializedName

data class Regions(

    @SerializedName("AMERICAS")
    var americas: String = "",

    @SerializedName("BR")
    var br: String = "",

    @SerializedName("LATIN_AMERICA")
    var latin_america: String = "",

    @SerializedName("SOUTH_AMERICA")
    var south_america: String = "",

    @SerializedName("WORLD")
    var world: String = "",

)


//        "regions": {
//        "AMERICAS": "019",
//        "BR": "076",
//        "LATIN_AMERICA": "419",
//        "SOUTH_AMERICA": "005",
//        "WORLD": "001"
//        }