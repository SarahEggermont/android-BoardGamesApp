package com.example.cafesapp.model

/**
 * Cafe model.
 *
 * @property id Unique identifier of the cafe.
 * @property poi Url to the "Point Of Interest"-page of the cafe.
 * @property nameNl Name of the cafe in Dutch.
 * @property descriptionNl Description of the cafe in Dutch.
 * @property descriptionEn Description of the cafe in English.
 * @property descriptionFr Description of the cafe in French.
 * @property descriptionDe Description of the cafe in German.
 * @property descriptionEs Description of the cafe in Spanish.
 * @property url Url of the cafe.
 * @property modified Date of last adjustment of the cafe's information.
 * @property catnameNl Category name of the cafe in Dutch.
 * @property address Address of the cafe.
 * @property postal Postal code of the cafe.
 * @property local City of the cafe.
 * @property icon Icon of the cafe.
 * @property type Type of the cafe (see_do or eat_drink).
 * @property symbol Symbol-type of the icon of the cafe.
 * @property identifier Identifier of the cafe.
 * @property geoPoint The coordinates of the cafe.
 * @constructor Creates a [Cafe] with empty fields.
 */
data class Cafe(
    var id: Int = 0,
    val poi: String = "",
    val nameNl: String = "",
    val descriptionNl: String = "",
    val descriptionEn: String = "",
    val descriptionFr: String = "",
    val descriptionDe: String = "",
    val descriptionEs: String = "",
    val url: String = "",
    val modified: String = "",
    val catnameNl: String = "",
    val address: String = "",
    val postal: String = "",
    val local: String = "",
    val icon: String = "",
    val type: String = "",
    val symbol: String = "",
    val identifier: Int = 0,
    val geoPoint: List<Double> = listOf(0.0, 0.0)
)
