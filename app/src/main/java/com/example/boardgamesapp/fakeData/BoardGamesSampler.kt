package com.example.boardgamesapp.fakeData

import com.example.boardgamesapp.model.Game

object BoardGamesSampler {
    val boardGames = listOf(
        Game(
            id = "1",
            title = "Thorgal: The Board Game",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__thumb/img/0hdUKkeZA4E25_VUPUXpSvvJgls=/fit-in/200x150/filters:strip_icc()/pic6724739.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "2",
            title = "Imperial Settlers: Empires of the North",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/w6HMeWkxsTJM6zC8oxwUfQ__thumb/img/g1qP3Gm2krXidqUYAJCS-xZVz0I=/fit-in/200x150/filters:strip_icc()/pic4543694.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "3",
            title = "Catan",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__thumb/img/8a9HeqFydO7Uun_le9bXWPnidcA=/fit-in/200x150/filters:strip_icc()/pic2419375.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "4",
            title = "Carcassonne Big Box 6",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/RuVaR99haabPBv8xQP-K7g__thumb/img/nGTNaDvoURPZrg7Jv6ZPL3cXswE=/fit-in/200x150/filters:strip_icc()/pic6882456.png" // ktlint-disable max-line-length
        ),
        Game(
            id = "5",
            title = "Cascadia",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/MjeJZfulbsM1DSV3DrGJYA__thumb/img/tVSFjSxYEcw7sKj3unIIQV8kxoc=/fit-in/200x150/filters:strip_icc()/pic5100691.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "6",
            title = "Sagrada",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/PZt3EAAGV3dFIVuwMR0AEw__thumb/img/1m4aryOW1MOpq-8jGkF6gDTJmCY=/fit-in/200x150/filters:strip_icc()/pic3525224.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "7",
            title = "Ark Nova",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/SoU8p28Sk1s8MSvoM4N8pQ__thumb/img/4KuHNTWSMPf8vTNDKSRMMI3oOv8=/fit-in/200x150/filters:strip_icc()/pic6293412.jpg" // ktlint-disable max-line-length
        ),
        Game(
            id = "8",
            title = "Ticket to Ride: Europe - 15th Anniversary",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/bilOnrR0vksMgjDK1zWPmQ__thumb/img/8lPTz5okk0uiW19b6seXaUXh7UE=/fit-in/200x150/filters:strip_icc()/pic5941381.png" // ktlint-disable max-line-length
        ),
        Game(
            id = "9",
            title = "Maya",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/nLtG6IiiN8ffXjoe8mGrPw__thumb/img/sxhx_oW6LIxDvl6ZinTqR5NishA=/fit-in/200x150/filters:strip_icc()/pic5030000.png" // ktlint-disable max-line-length
        )

    )

    val getAll: () -> MutableList<Game> = {
        val list = mutableListOf<Game>()
        for (game in boardGames) {
            list.add(game)
        }
        list
    }
}
