package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

import com.google.gson.annotations.SerializedName

data class DMS(

    @SerializedName("lat")
    var lat: String = "",

    @SerializedName("lng")
    var lng: String = "",

)

//    "DMS": {
//        "lat": "23° 31' 41.60856'' S",
//        "lng": "46° 54' 7.21080'' W"
//    }