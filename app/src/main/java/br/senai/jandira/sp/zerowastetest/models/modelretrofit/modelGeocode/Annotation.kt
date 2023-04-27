package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

import com.google.gson.annotations.SerializedName

data class Annotation(

    @SerializedName("DMS")
    var dms: DMS? = null,

    @SerializedName("MGRS")
    var mgrs: String = "",

    @SerializedName("Maidenhead")
    var maidenhead: String = "",

    @SerializedName("Mercator")
    var mercator: Mercator? = null,

    @SerializedName("OSM")
    var osm: OSM? = null,

    @SerializedName("UN_M49")
    var un_m49: UN_M49? = null,



)

//"annotations": {
//    "DMS": {
//        "lat": "23° 31' 41.60856'' S",
//        "lng": "46° 54' 7.21080'' W"
//    },
//    "MGRS": "23KLP0582296717",
//    "Maidenhead": "GG66nl13sf",
//    "Mercator": {
//        "x": -5221107.091,
//        "y": -2678973.686
//    },
//    "OSM": {
//        "edit_url": "https://www.openstreetmap.org/edit?relation=298226#map=17/-23.52822/-46.90200",
//        "note_url": "https://www.openstreetmap.org/note/new#map=17/-23.52822/-46.90200&layers=N",
//        "url": "https://www.openstreetmap.org/?mlat=-23.52822&mlon=-46.90200#map=17/-23.52822/-46.90200"
//    },
//    "UN_M49": {
//        "regions": {
//        "AMERICAS": "019",
//        "BR": "076",
//        "LATIN_AMERICA": "419",
//        "SOUTH_AMERICA": "005",
//        "WORLD": "001"
//    },
//        "statistical_groupings": [
//        "LEDC"
//        ]
//    },
//    "callingcode": 55,
//    "currency": {
//        "decimal_mark": ",",
//        "html_entity": "R$",
//        "iso_code": "BRL",
//        "iso_numeric": "986",
//        "name": "Brazilian Real",
//        "smallest_denomination": 5,
//        "subunit": "Centavo",
//        "subunit_to_unit": 100,
//        "symbol": "R$",
//        "symbol_first": 1,
//        "thousands_separator": "."
//    },
//    "flag": "🇧🇷",
//    "geohash": "6gydhsz40j5c2ehgxuks",
//    "qibla": 69.04,
//    "roadinfo": {
//        "drive_on": "right",
//        "speed_in": "km/h"
//    },
//    "sun": {
//        "rise": {
//        "apparent": 1682587620,
//        "astronomical": 1682583060,
//        "civil": 1682586240,
//        "nautical": 1682584620
//    },
//        "set": {
//        "apparent": 1682628180,
//        "astronomical": 1682632740,
//        "civil": 1682629560,
//        "nautical": 1682631180
//    }
//    },
//    "timezone": {
//        "name": "America/Sao_Paulo",
//        "now_in_dst": 0,
//        "offset_sec": -10800,
//        "offset_string": "-0300",
//        "short_name": "BRT"
//    },
//    "what3words": {
//        "words": "battling.romance.stars"
//    },
//    "wikidata": "Q1646970"
//}
