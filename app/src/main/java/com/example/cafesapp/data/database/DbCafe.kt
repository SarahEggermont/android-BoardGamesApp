package com.example.cafesapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cafesapp.model.Cafe

/**
 * Entity for the cafes.
 *
 * @property objectid: Id of the cafe.
 * @property poi: Url to the "Point Of Interest"-page of the cafe.
 * @property nameNl: Name of the cafe in Dutch.
 * @property descriptionNl: Description of the cafe in Dutch.
 * @property descriptionEn: Description of the cafe in English.
 * @property descriptionFr: Description of the cafe in French.
 * @property descriptionDe: Description of the cafe in German.
 * @property descriptionEs: Description of the cafe in Spanish.
 * @property url: Url of the cafe.
 * @property modified: Date of last adjustment of the cafe's information.
 * @property catnameNl: Category name of the cafe in Dutch.
 * @property address: Address of the cafe.
 * @property postal: Postal code of the cafe.
 * @property local: City of the cafe.
 * @property icon: Icon of the cafe.
 * @property type: Type of the cafe (see_do or eat_drink).
 * @property symbol: Symbol-type of the icon of the cafe.
 * @property identifier: Identifier of the cafe.
 * @property geoPointX: Geo point x of the cafe.
 * @property geoPointY: Geo point y of the cafe.
 * @constructor Creates a [DbCafe] with default values.
 */
@Entity(tableName = "cafes")
data class DbCafe(
    @PrimaryKey
    val objectid: Int,
    val poi: String = "",
    @ColumnInfo(name = "name_nl") val nameNl: String = "",
    @ColumnInfo(name = "description_nl") val descriptionNl: String = "",
    @ColumnInfo(name = "description_en") val descriptionEn: String = "",
    @ColumnInfo(name = "description_fr") val descriptionFr: String = "",
    @ColumnInfo(name = "description_de") val descriptionDe: String = "",
    @ColumnInfo(name = "description_es") val descriptionEs: String = "",
    val url: String = "",
    val modified: String = "",
    @ColumnInfo(name = "catname_nl") val catnameNl: String = "",
    val address: String = "",
    val postal: String = "",
    val local: String = "",
    val icon: String = "",
    val type: String = "",
    val symbol: String = "",
    val identifier: Int = 0,
    @ColumnInfo(name = "geo_point_x") val geoPointX: Double = 0.0,
    @ColumnInfo(name = "geo_point_y") val geoPointY: Double = 0.0,
)

/**
 * Converts a [DbCafe] to a [Cafe].
 *
 * @return The converted [Cafe].
 */
fun DbCafe.asDomainCafe(): Cafe {
    return Cafe(
        this.objectid,
        this.poi,
        this.nameNl,
        this.descriptionNl,
        this.descriptionEn,
        this.descriptionFr,
        this.descriptionDe,
        this.descriptionEs,
        this.url,
        this.modified,
        this.catnameNl,
        this.address,
        this.postal,
        this.local,
        this.icon,
        this.type,
        this.symbol,
        this.identifier,
        listOf(this.geoPointX, this.geoPointY),
    )
}

/**
 * Converts a [Cafe] to a [DbCafe].
 *
 * @return The converted [DbCafe].
 */
fun Cafe.asDbCafe(): DbCafe {
    return DbCafe(
        objectid = this.id,
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
        geoPointX = this.geoPoint[0],
        geoPointY = this.geoPoint[1],
    )
}

/**
 * Converts a [List] of [DbCafe] to a [List] of [Cafe].
 *
 * @return The converted [List] of [Cafe]s.
 */
fun List<DbCafe>.asDomainCafes(): List<Cafe> {
    val cafeList =
        this.map {
            Cafe(
                it.objectid,
                it.poi,
                it.nameNl,
                it.descriptionNl,
                it.descriptionEn,
                it.descriptionFr,
                it.descriptionDe,
                it.descriptionEs,
                it.url,
                it.modified,
                it.catnameNl,
                it.address,
                it.postal,
                it.local,
                it.icon,
                it.type,
                it.symbol,
                it.identifier,
                listOf(it.geoPointX, it.geoPointY),
            )
        }
    return cafeList
}
