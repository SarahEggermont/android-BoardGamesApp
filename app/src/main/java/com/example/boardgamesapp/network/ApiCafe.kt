package com.example.boardgamesapp.network

import com.example.boardgamesapp.model.Cafe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class GeoPoints(
    val lon: Double,
    val lat: Double
)

@Serializable
data class ApiCafe(
    val objectid: Int,
    val poi: String,
    val name_nl: String,
    val name_en: String,
    val name_fr: String,
    val name_de: String,
    val name_es: String,
    val description_nl: String,
    val description_en: String,
    val description_fr: String,
    val description_de: String,
    val description_es: String,
    val url: String,
    val modified: String,
    val catname_nl: String,
    val address: String,
    val postal: String,
    val local: String,
    val icon: String,
    val type: String,
    val symbol: String,
    val identifier: Int,
    val geo_point_2d: GeoPoints
)

fun Flow<List<ApiCafe>>.asDomainObjects(): Flow<List<Cafe>> {
    val list = this.map {
        it.asDomainObjects()
    }
    return list
}

fun Flow<ApiCafe>.asDomainObject(): Flow<Cafe> {
    val cafe = this.map {
        it.asDomainObject()
    }
    return cafe
}

fun ApiCafe.asDomainObject(): Cafe {
    return Cafe(
        id = this.objectid,
        poi = this.poi,
        nameNl = this.name_nl,
        nameEn = this.name_en,
        nameFr = this.name_fr,
        nameDe = this.name_de,
        nameEs = this.name_es,
        descriptionNl = this.description_nl,
        descriptionEn = this.description_en,
        descriptionFr = this.description_fr,
        descriptionDe = this.description_de,
        descriptionEs = this.description_es,
        url = this.url,
        modified = this.modified,
        catnameNl = this.catname_nl,
        address = this.address,
        postal = this.postal,
        local = this.local,
        icon = this.icon,
        type = this.type,
        symbol = this.symbol,
        identifier = this.identifier,
        geoPoint = listOf(this.geo_point_2d.lon, this.geo_point_2d.lat)
    )
}

fun List<ApiCafe>.asDomainObjects(): List<Cafe> {
    val list = this.map {
        it.asDomainObject()
    }
    return list
}
