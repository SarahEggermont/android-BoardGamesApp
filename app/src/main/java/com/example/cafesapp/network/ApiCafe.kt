package com.example.cafesapp.network

import com.example.cafesapp.model.Cafe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Geo points
 *
 * @property lon the longitude or the x-coordinate of the cafe.
 * @property lat the latitude or the y-coordinate of the cafe.
 * @constructor Create empty Geo points
 */
@Serializable
data class GeoPoints(
    val lon: Double,
    val lat: Double,
)

/**
 * Api cafe
 *
 * @property objectid the id of the cafe.
 * @property poi the url to the "Point Of Interest"-page of the cafe.
 * @property nameNl the name of the cafe in Dutch.
 * @property descriptionNl the description of the cafe in Dutch.
 * @property descriptionEn the description of the cafe in English.
 * @property descriptionFr the description of the cafe in French.
 * @property descriptionDe the description of the cafe in German.
 * @property descriptionEs the description of the cafe in Spanish.
 * @property url the url of the cafe.
 * @property modified the date of last adjustment of the cafe's information.
 * @property catnameNl the category name of the cafe in Dutch.
 * @property address the address of the cafe.
 * @property postal the postal code of the cafe.
 * @property local the city of the cafe.
 * @property icon the icon of the cafe.
 * @property type the type of the cafe (see_do or eat_drink).
 * @property symbol the symbol-type of the icon of the cafe.
 * @property identifier the identifier of the cafe.
 * @property geoPoint2d The coordinates of the cafe.
 */
@Serializable
data class ApiCafe(
    val objectid: Int,
    val poi: String,
    @SerialName("name_nl") val nameNl: String,
    @SerialName("description_nl") val descriptionNl: String,
    @SerialName("description_en") val descriptionEn: String,
    @SerialName("description_fr") val descriptionFr: String,
    @SerialName("description_de") val descriptionDe: String,
    @SerialName("description_es") val descriptionEs: String,
    val url: String,
    val modified: String,
    @SerialName("catname_nl") val catnameNl: String,
    val address: String,
    val postal: String,
    val local: String,
    val icon: String,
    val type: String,
    val symbol: String,
    val identifier: Int,
    @SerialName("geo_point_2d") val geoPoint2d: GeoPoints,
)

/**
 * Extension function to convert a [ApiCafe] to a [Cafe].
 *
 * @return a [Cafe] object.
 */
fun Flow<List<ApiCafe>>.asDomainObjects(): Flow<List<Cafe>> {
    val list =
        this.map {
            it.asDomainObjects()
        }
    return list
}

/**
 * Extension function to convert a [ApiCafe] to a [Cafe].
 *
 * @return a [Cafe] object.
 */
fun Flow<ApiCafe>.asDomainObject(): Flow<Cafe> {
    val cafe =
        this.map {
            it.asDomainObject()
        }
    return cafe
}

/**
 * Extension function to convert a [ApiCafe] to a [Cafe].
 *
 * @return a [Cafe] object.
 */
fun ApiCafe.asDomainObject(): Cafe {
    return Cafe(
        id = this.objectid,
        poi = this.poi,
        nameNl = this.nameNl,
        descriptionNl = this.descriptionNl,
        descriptionEn = this.descriptionEn,
        descriptionFr = this.descriptionFr,
        descriptionDe = this.descriptionDe,
        descriptionEs = this.descriptionEs,
        url = this.url,
        modified = this.modified,
        catnameNl = this.catnameNl,
        address = this.address,
        postal = this.postal,
        local = this.local,
        icon = this.icon,
        type = this.type,
        symbol = this.symbol,
        identifier = this.identifier,
        geoPoint = listOf(this.geoPoint2d.lon, this.geoPoint2d.lat),
    )
}

/**
 * Extension function to convert a list of [ApiCafe] to a list of [Cafe].
 *
 * @return a [List] of [Cafe]s.
 */
fun List<ApiCafe>.asDomainObjects(): List<Cafe> {
    val list =
        this.map {
            it.asDomainObject()
        }
    return list
}
