package com.example.cafesapp.fake

import com.example.cafesapp.network.ApiCafe
import com.example.cafesapp.network.GeoPoints

/**
 * Fake data source for testing.
 */
object FakeDataSource {
    private const val nameOne = "Aba-jour"
    private const val poiOne = "http://stad.gent/id/place/tourism/1b42ed74-ba82-44ed-83a8-185a7e25f24a"
    private const val descriptionNlOne = "Art-deco brasserie met zicht op de Leie"
    private const val descriptionEnOne = "Art-deco brasserie with a view of the Lys"
    private const val descriptionFrOne = "Brasserie art déco avec vue sur la Lys"
    private const val descriptionDeOne = "Art-Deco-Brasserie mit Blick auf die Leie"
    private const val descriptionEsOne = "Brasserie art déco con vistas al río Lys"
    private const val urlOne = "https://visit.gent.be/nl/eten-drinken/aba-jour"
    private const val modifiedOne = "2021-06-01T09:48:00.000Z"
    private const val catNameNlOne = "Café"
    private const val addressOne = "Oudburg 20"
    private const val postalOne = "9000"
    private const val cityOne = "Gent"
    private const val iconOne = "https://visit.gent.be/themes/custom/vg_theme/icon/red/restaurant.svg"
    private const val typeOne = "eat_drink"
    private const val symbolOne = "restaurant"
    private const val identifierOne = 1619
    private const val lonOne = 3.724210977302197
    private const val latOne = 51.05810546884239

    private const val nameTwo = "Charlatan"
    private const val poiTwo = "http://stad.gent/id/place/tourism/71ac8dbf-6f34-444a-9344-c5a6d5eab04e"
    private const val descriptionNlTwo = "Het huis van verderf"
    private const val descriptionEnTwo = "House of Perdition"
    private const val descriptionFrTwo = "Maison de la perdition"
    private const val descriptionDeTwo = "Das Haus der Verdammnis"
    private const val descriptionEsTwo = "Garito de perdición"
    private const val urlTwo = "https://visit.gent.be/nl/eten-drinken/charlatan"
    private const val modifiedTwo = "2021-06-01T09:48:00.000Z"
    private const val catNameNlTwo = "Café"
    private const val addressTwo = "Vlasmarkt 6"
    private const val postalTwo = "9000"
    private const val cityTwo = "Gent"
    private const val iconTwo = "https://visit.gent.be/themes/custom/vg_theme/icon/red/cafe.svg"
    private const val typeTwo = "eat_drink"
    private const val symbolTwo = "cafe"
    private const val identifierTwo = 1675
    private const val lonTwo = 3.728537082250816
    private const val laTwo = 51.05596542377878

    val cafes =
        listOf(
            ApiCafe(
                objectid = 1,
                poi = poiOne,
                nameNl = nameOne,
                descriptionNl = descriptionNlOne,
                descriptionEn = descriptionEnOne,
                descriptionFr = descriptionFrOne,
                descriptionDe = descriptionDeOne,
                descriptionEs = descriptionEsOne,
                url = urlOne,
                modified = modifiedOne,
                catnameNl = catNameNlOne,
                address = addressOne,
                postal = postalOne,
                local = cityOne,
                icon = iconOne,
                type = typeOne,
                symbol = symbolOne,
                identifier = identifierOne,
                geoPoint2d = GeoPoints(lonOne, latOne),
            ),
            ApiCafe(
                objectid = 2,
                poi = poiTwo,
                nameNl = nameTwo,
                descriptionNl = descriptionNlTwo,
                descriptionEn = descriptionEnTwo,
                descriptionFr = descriptionFrTwo,
                descriptionDe = descriptionDeTwo,
                descriptionEs = descriptionEsTwo,
                url = urlTwo,
                modified = modifiedTwo,
                catnameNl = catNameNlTwo,
                address = addressTwo,
                postal = postalTwo,
                local = cityTwo,
                icon = iconTwo,
                type = typeTwo,
                symbol = symbolTwo,
                identifier = identifierTwo,
                geoPoint2d = GeoPoints(lonTwo, laTwo),
            ),
        )
}
