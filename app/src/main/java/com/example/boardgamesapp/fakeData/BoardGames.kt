package com.example.boardgamesapp.fakeData

data class BoardGame(
    val title: String,
    val minPlayTime: Int,
    val maxPlayTime: Int,
    val minPlayers: Int,
    val maxPlayers: Int,
    val shortDescription: String,
    val thumbnail: String,
    val image: String
)

val boardGames = listOf<BoardGame>(
    BoardGame(
        title = "Thorgal: The Board Game",
        minPlayTime = 90,
        maxPlayTime = 120,
        minPlayers = 2,
        maxPlayers = 4,
        shortDescription = "Go on adventures in the mystic world of Norse mythology.",
        thumbnail = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__thumb/img/0hdUKkeZA4E25_VUPUXpSvvJgls=/fit-in/200x150/filters:strip_icc()/pic6724739.jpg",
        image = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__original/img/vs2i4NlxuNMUUZejmu5gEwWfLg0=/0x0/filters:format(jpeg)/pic6724739.jpg"
    ),
    BoardGame(
        title = "Imperial Settlers: Empires of the North",
        minPlayTime = 45,
        maxPlayTime = 90,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Lead one of 6 asymmetric factions to build an empire and conquer new islands.", // ktlint-disable max-line-length
        thumbnail = "https://cf.geekdo-images.com/w6HMeWkxsTJM6zC8oxwUfQ__thumb/img/g1qP3Gm2krXidqUYAJCS-xZVz0I=/fit-in/200x150/filters:strip_icc()/pic4543694.jpg", // ktlint-disable max-line-length
        image = "https://cf.geekdo-images.com/w6HMeWkxsTJM6zC8oxwUfQ__original/img/HTrXseY1Z66BC8QkdFgL2XUnA4A=/0x0/filters:format(jpeg)/pic4543694.jpg" // ktlint-disable max-line-length
    ),
    BoardGame(
        title = "Catan",
        minPlayTime = 60,
        maxPlayTime = 120,
        minPlayers = 3,
        maxPlayers = 4,
        shortDescription = "Collect and trade resources to build up the island of Catan in this modern classic.", // ktlint-disable max-line-length
        thumbnail = "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__thumb/img/8a9HeqFydO7Uun_le9bXWPnidcA=/fit-in/200x150/filters:strip_icc()/pic2419375.jpg", // ktlint-disable max-line-length
        image = "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__original/img/xV7oisd3RQ8R-k18cdWAYthHXsA=/0x0/filters:format(jpeg)/pic2419375.jpg" // ktlint-disable max-line-length
    ),
    BoardGame(
        title = "Carcassonne Big Box 6",
        minPlayTime = 35,
        maxPlayTime = 35,
        minPlayers = 2,
        maxPlayers = 6,
        shortDescription = "Shape the medieval landscape of France, claiming cities, monasteries and farms.", // ktlint-disable max-line-length
        thumbnail = "https://cf.geekdo-images.com/RuVaR99haabPBv8xQP-K7g__thumb/img/nGTNaDvoURPZrg7Jv6ZPL3cXswE=/fit-in/200x150/filters:strip_icc()/pic6882456.png", // ktlint-disable max-line-length
        image = "https://cf.geekdo-images.com/RuVaR99haabPBv8xQP-K7g__original/img/EJcx2LOvZUbYiBULcC0vK6iqS6A=/0x0/filters:format(png)/pic6882456.png" // ktlint-disable max-line-length
    ),
    BoardGame(
        title = "Cascadia",
        minPlayTime = 30,
        maxPlayTime = 45,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Create the most harmonious ecosystem as you puzzle together habitats and wildlife.", // ktlint-disable max-line-length
        thumbnail = "https://cf.geekdo-images.com/MjeJZfulbsM1DSV3DrGJYA__thumb/img/tVSFjSxYEcw7sKj3unIIQV8kxoc=/fit-in/200x150/filters:strip_icc()/pic5100691.jpg", // ktlint-disable max-line-length
        image = "https://cf.geekdo-images.com/MjeJZfulbsM1DSV3DrGJYA__original/img/B374C04Eip7fmQBGJzgiOTp-jyQ=/0x0/filters:format(jpeg)/pic5100691.jpg" // ktlint-disable max-line-length
    ),
    BoardGame(
        title = "Sagrada",
        minPlayTime = 30,
        maxPlayTime = 45,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Craft the best stained-glass windows by carefully placing colorful transparent dice.", // ktlint-disable max-line-length
        thumbnail = "https://cf.geekdo-images.com/PZt3EAAGV3dFIVuwMR0AEw__thumb/img/1m4aryOW1MOpq-8jGkF6gDTJmCY=/fit-in/200x150/filters:strip_icc()/pic3525224.jpg", // ktlint-disable max-line-length
        image = "https://cf.geekdo-images.com/PZt3EAAGV3dFIVuwMR0AEw__original/img/5ug9EeKlH_ucJEUXaFpTZbBJ1GY=/0x0/filters:format(jpeg)/pic3525224.jpg" // ktlint-disable max-line-length
    )
)
