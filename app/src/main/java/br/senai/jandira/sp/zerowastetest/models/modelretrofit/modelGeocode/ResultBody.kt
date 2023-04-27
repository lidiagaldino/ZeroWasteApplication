package br.senai.jandira.sp.zerowastetest.models.modelretrofit.modelGeocode

data class ResultBody(

    var annotation: Annotation? = null,
    var bounds: Bounds? = null,
    var components: Components? = null,
    var confidence: Int = 0,
    var formatted: String = "",
    var geometry: Geometry? = null

)

//"results": [
//        {
//            "annotations": {
//                "DMS": {
//                    "lat": "23° 31' 20.82252'' S",
//                    "lng": "46° 50' 25.33632'' W"
//                },
//                "MGRS": "23KLP1210797438",
//                "Maidenhead": "GG66nl94do",
//                "Mercator": {
//                    "x": -5214246.271,
//                    "y": -2678276.623
//                },
//                "OSM": {
//                    "edit_url": "https://www.openstreetmap.org/edit?way=774861931#map=17/-23.52245/-46.84037",
//                    "note_url": "https://www.openstreetmap.org/note/new#map=17/-23.52245/-46.84037&layers=N",
//                    "url": "https://www.openstreetmap.org/?mlat=-23.52245&mlon=-46.84037#map=17/-23.52245/-46.84037"
//                },
//                "UN_M49": {
//                    "regions": {
//                        "AMERICAS": "019",
//                        "BR": "076",
//                        "LATIN_AMERICA": "419",
//                        "SOUTH_AMERICA": "005",
//                        "WORLD": "001"
//                    },
//                    "statistical_groupings": [
//                        "LEDC"
//                    ]
//                },
//                "callingcode": 55,
//                "currency": {
//                    "decimal_mark": ",",
//                    "html_entity": "R$",
//                    "iso_code": "BRL",
//                    "iso_numeric": "986",
//                    "name": "Brazilian Real",
//                    "smallest_denomination": 5,
//                    "subunit": "Centavo",
//                    "subunit_to_unit": 100,
//                    "symbol": "R$",
//                    "symbol_first": 1,
//                    "thousands_separator": "."
//                },
//                "flag": "🇧🇷",
//                "geohash": "6gydnjggu1ru08jfqwdk",
//                "qibla": 69.01,
//                "roadinfo": {
//                    "drive_on": "right",
//                    "road": "Rua Ângela Perioto Tolaine",
//                    "road_type": "residential",
//                    "speed_in": "km/h"
//                },
//                "sun": {
//                    "rise": {
//                        "apparent": 1682587620,
//                        "astronomical": 1682583060,
//                        "civil": 1682586240,
//                        "nautical": 1682584620
//                    },
//                    "set": {
//                        "apparent": 1682628180,
//                        "astronomical": 1682632740,
//                        "civil": 1682629560,
//                        "nautical": 1682631180
//                    }
//                },
//                "timezone": {
//                    "name": "America/Sao_Paulo",
//                    "now_in_dst": 0,
//                    "offset_sec": -10800,
//                    "offset_string": "-0300",
//                    "short_name": "BRT"
//                },
//                "what3words": {
//                    "words": "scan.paddock.kind"
//                }
//            },
//            "bounds": {
//                "northeast": {
//                    "lat": -23.520225,
//                    "lng": -46.8403544
//                },
//                "southwest": {
//                    "lat": -23.5235272,
//                    "lng": -46.8403991
//                }
//            },
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
//            },
//            "confidence": 9,
//            "formatted": "Rua Ângela Perioto Tolaine, Vila Caldas, Carapicuíba - SP, 06311-970, Brazil",
//            "geometry": {
//                "lat": -23.5224507,
//                "lng": -46.8403712
//            }
//        }