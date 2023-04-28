package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class Currency(

    var decimal_mark: String = "",
    var html_entity: String = "",
    var iso_code: String = "",
    var iso_numeric: String = "",
    var name: String = "",
    var smallest_denomination: Int = 0,
    var subunit: String = "",
    var subunit_to_unit: Int = 0,
    var symbol: String = "",
    var symbol_first: Int = 0,
    var thousands_separator: String = ""

)

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
//     },