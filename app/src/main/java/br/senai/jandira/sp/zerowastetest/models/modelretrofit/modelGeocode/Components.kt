package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

import com.google.gson.annotations.SerializedName

data class Components(

    @SerializedName("ISO_3166-1_alpha-2")
    var ISO_1: String = "",

    @SerializedName("ISO_3166-1_alpha-3")
    var ISO_2: String = "",

    @SerializedName("ISO_3166-2")
    var ISO_3: List<String>? = null,

    var _category: String = "",
    var _type: String = "",
    var city: String = "",
    var city_district: String = "",
    var continent: String = "",
    var country: String = "",
    var country_code: String = "",
    var county: String = "",
    var municipality: String = "",
    var postcode: String = "",
    var region: String = "",
    var road: String = "",
    var road_type: String = "",
    var state: String = "",
    var state_code: String = "",
    var suburb: String = "",

)


//            "components": {
//                "ISO_3166-1_alpha-2": "BR",
//                "ISO_3166-1_alpha-3": "BRA",
//                "ISO_3166-2": [
//                    "BR-SP"
//                ],
//                "_category": "road",
//                "_type": "road",
//                "city": "Carapicuíba",
//                "city_district": "Carapicuíba",
//                "continent": "South America",
//                "country": "Brazil",
//                "country_code": "br",
//                "county": "Região Metropolitana de São Paulo",
//                "municipality": "Região Imediata de São Paulo",
//                "postcode": "06311-970",
//                "region": "Southeast Region",
//                "road": "Rua Ângela Perioto Tolaine",
//                "road_type": "residential",
//                "state": "São Paulo",
//                "state_code": "SP",
//                "state_district": "Região Geográfica Intermediária de São Paulo",
//                "suburb": "Vila Caldas"
//            }