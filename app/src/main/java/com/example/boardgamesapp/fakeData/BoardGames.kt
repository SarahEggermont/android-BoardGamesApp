package com.example.boardgamesapp.fakeData

data class BoardGame(
    val title: String,
    val minPlayTime: Int,
    val maxPlayTime: Int,
    val minPlayers: Int,
    val maxPlayers: Int,
    val shortDescription: String,
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
        image = "thorgal"
    ),
    BoardGame(
        title = "Imperial Settlers: Empires of the North",
        minPlayTime = 45,
        maxPlayTime = 90,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Lead one of 6 asymmetric factions to build an empire and conquer new islands.", // ktlint-disable max-line-length
        image = "imperial_settlers"
    ),
    BoardGame(
        title = "Catan",
        minPlayTime = 60,
        maxPlayTime = 120,
        minPlayers = 3,
        maxPlayers = 4,
        shortDescription = "Collect and trade resources to build up the island of Catan in this modern classic.", // ktlint-disable max-line-length
        image = "catan"
    ),
    BoardGame(
        title = "Carcassonne Big Box 6",
        minPlayTime = 35,
        maxPlayTime = 35,
        minPlayers = 2,
        maxPlayers = 6,
        shortDescription = "Shape the medieval landscape of France, claiming cities, monasteries and farms.", // ktlint-disable max-line-length
        image = "carcassonne"
    ),
    BoardGame(
        title = "Cascadia",
        minPlayTime = 30,
        maxPlayTime = 45,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Create the most harmonious ecosystem as you puzzle together habitats and wildlife.", // ktlint-disable max-line-length
        image = "cascadia"
    ),
    BoardGame(
        title = "Sagrada",
        minPlayTime = 30,
        maxPlayTime = 45,
        minPlayers = 1,
        maxPlayers = 4,
        shortDescription = "Craft the best stained-glass windows by carefully placing colorful transparent dice.", // ktlint-disable max-line-length
        image = "sagrada"
    )
)
