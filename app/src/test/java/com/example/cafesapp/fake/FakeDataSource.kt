package com.example.cafesapp.fake

import com.example.cafesapp.data.database.DbCafe
import com.example.cafesapp.data.database.asDbCafes
import com.example.cafesapp.network.ApiCafe
import com.example.cafesapp.network.GeoPoints
import com.example.cafesapp.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeDataSource {
    const val nameOne = "Aba-jour"
    const val poiOne = "http://stad.gent/id/place/tourism/1b42ed74-ba82-44ed-83a8-185a7e25f24a"
    const val descriptionNlOne = "Art-deco brasserie met zicht op de Leie"
    const val descriptionEnOne = "Art-deco brasserie with a view of the Lys"
    const val descriptionFrOne = "Brasserie art déco avec vue sur la Lys"
    const val descriptionDeOne = "Art-Deco-Brasserie mit Blick auf die Leie"
    const val descriptionEsOne = "Brasserie art déco con vistas al río Lys"
    const val urlOne = "https://visit.gent.be/nl/eten-drinken/aba-jour"
    const val modifiedOne = "2021-06-01T09:48:00.000Z"
    const val catNameNlOne = "Café"
    const val addressOne = "Oudburg 20"
    const val postalOne = "9000"
    const val cityOne = "Gent"
    const val iconOne = "https://visit.gent.be/themes/custom/vg_theme/icon/red/restaurant.svg"
    const val typeOne = "eat_drink"
    const val symbolOne = "restaurant"
    const val identifierOne = 1619
    const val lonOne = 3.724210977302197
    const val latOne = 51.05810546884239

    const val nameTwo = "Charlatan"
    const val poiTwo = "http://stad.gent/id/place/tourism/71ac8dbf-6f34-444a-9344-c5a6d5eab04e"
    const val descriptionNlTwo = "Het huis van verderf"
    const val descriptionEnTwo = "House of Perdition"
    const val descriptionFrTwo = "Maison de la perdition"
    const val descriptionDeTwo = "Das Haus der Verdammnis"
    const val descriptionEsTwo = "Garito de perdición"
    const val urlTwo = "https://visit.gent.be/nl/eten-drinken/charlatan"
    const val modifiedTwo = "2021-06-01T09:48:00.000Z"
    const val catNameNlTwo = "Café"
    const val addressTwo = "Vlasmarkt 6"
    const val postalTwo = "9000"
    const val cityTwo = "Gent"
    const val iconTwo = "https://visit.gent.be/themes/custom/vg_theme/icon/red/cafe.svg"
    const val typeTwo = "eat_drink"
    const val symbolTwo = "cafe"
    const val identifierTwo = 1675
    const val lonTwo = 3.728537082250816
    const val laTwo = 51.05596542377878

    val cafes = listOf(
        ApiCafe(
            objectid = 1,
            poi = poiOne,
            name_nl = nameOne,
            description_nl = descriptionNlOne,
            description_en = descriptionEnOne,
            description_fr = descriptionFrOne,
            description_de = descriptionDeOne,
            description_es = descriptionEsOne,
            url = urlOne,
            modified = modifiedOne,
            catname_nl = catNameNlOne,
            address = addressOne,
            postal = postalOne,
            local = cityOne,
            icon = iconOne,
            type = typeOne,
            symbol = symbolOne,
            identifier = identifierOne,
            geo_point_2d = GeoPoints(lonOne, latOne)
        ),
        ApiCafe(
            objectid = 2,
            poi = poiTwo,
            name_nl = nameTwo,
            description_nl = descriptionNlTwo,
            description_en = descriptionEnTwo,
            description_fr = descriptionFrTwo,
            description_de = descriptionDeTwo,
            description_es = descriptionEsTwo,
            url = urlTwo,
            modified = modifiedTwo,
            catname_nl = catNameNlTwo,
            address = addressTwo,
            postal = postalTwo,
            local = cityTwo,
            icon = iconTwo,
            type = typeTwo,
            symbol = symbolTwo,
            identifier = identifierTwo,
            geo_point_2d = GeoPoints(lonTwo, laTwo)
        )
    )
}

/**
 * Get all cafes as a flow.
 * @return a [Flow] containing a [List] of [DbCafe]s.
 */
fun FakeDataSource.getCafesAsDbFlow(): Flow<List<DbCafe>> = flow {
    emit(cafes.asDomainObjects().asDbCafes())
}
