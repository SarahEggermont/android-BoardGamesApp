package com.example.boardgamesapp.fakeData

import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.model.GameDetail

object BoardGamesSampler {
    val boardGames = listOf(
        Game(
            id = "1",
            title = "Thorgal: The Board Game",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__thumb/img/0hdUKkeZA4E25_VUPUXpSvvJgls=/fit-in/200x150/filters:strip_icc()/pic6724739.jpg"
        ),
        Game(
            id = "2",
            title = "Imperial Settlers: Empires of the North",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/w6HMeWkxsTJM6zC8oxwUfQ__thumb/img/g1qP3Gm2krXidqUYAJCS-xZVz0I=/fit-in/200x150/filters:strip_icc()/pic4543694.jpg"
        ),
        Game(
            id = "3",
            title = "Catan",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/W3Bsga_uLP9kO91gZ7H8yw__thumb/img/8a9HeqFydO7Uun_le9bXWPnidcA=/fit-in/200x150/filters:strip_icc()/pic2419375.jpg"
        ),
        Game(
            id = "4",
            title = "Carcassonne Big Box 6",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/RuVaR99haabPBv8xQP-K7g__thumb/img/nGTNaDvoURPZrg7Jv6ZPL3cXswE=/fit-in/200x150/filters:strip_icc()/pic6882456.png"
        ),
        Game(
            id = "5",
            title = "Cascadia",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/MjeJZfulbsM1DSV3DrGJYA__thumb/img/tVSFjSxYEcw7sKj3unIIQV8kxoc=/fit-in/200x150/filters:strip_icc()/pic5100691.jpg"
        ),
        Game(
            id = "6",
            title = "Sagrada",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/PZt3EAAGV3dFIVuwMR0AEw__thumb/img/1m4aryOW1MOpq-8jGkF6gDTJmCY=/fit-in/200x150/filters:strip_icc()/pic3525224.jpg"
        ),
        Game(
            id = "7",
            title = "Ark Nova",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/SoU8p28Sk1s8MSvoM4N8pQ__thumb/img/4KuHNTWSMPf8vTNDKSRMMI3oOv8=/fit-in/200x150/filters:strip_icc()/pic6293412.jpg"
        ),
        Game(
            id = "8",
            title = "Ticket to Ride: Europe - 15th Anniversary",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/bilOnrR0vksMgjDK1zWPmQ__thumb/img/8lPTz5okk0uiW19b6seXaUXh7UE=/fit-in/200x150/filters:strip_icc()/pic5941381.png"
        ),
        Game(
            id = "9",
            title = "Maya",
            year = 2012,
            thumbnail = "https://cf.geekdo-images.com/nLtG6IiiN8ffXjoe8mGrPw__thumb/img/sxhx_oW6LIxDvl6ZinTqR5NishA=/fit-in/200x150/filters:strip_icc()/pic5030000.png"
        )

    )

    val getAll: () -> MutableList<Game> = {
        val list = mutableListOf<Game>()
        for (game in boardGames) {
            list.add(game)
        }
        list
    }

    val getOne: () -> GameDetail = {
        GameDetail(
            title = "Thorgal: The Board Game",
            minPlayTime = "90",
            maxPlayTime = "120",
            minPlayers = "1",
            maxPlayers = "4",
            shortDescription = "The Gamefound edition contains gameplay elements which will not be included in the retail edition:&amp;#10;&amp;#10;- 3 additional adventures and their respective adventure sheets (Winter Demon, Labyrinth, Seeking Gamma)&amp;#10;- 30 additional pages in the Book of Tales&amp;#10;- 3 additional characters (Wolfcub, Tjall the Fiery, Arghun &amp;quot;Tree Foot&amp;quot;) each coming with a miniature, character board, and skill card&amp;#10;- wooden First Player token&amp;#10;- Frigg's Cat (miniature and card)&amp;#10;- 10 additional Event cards&amp;#10;- 16 additional Terrain cards&amp;#10;- 8 additional Enemy cards&amp;#10;- 7 additional Action cards&amp;#10;- 15 additional Item cards&amp;#10;- 10 additional Finding cards&amp;#10;- 8 Gladiator cards&amp;#10;&amp;#10;Thorgal: The Board Game is a cooperative storybook adventure game for 1-4 players aged 14+ designed by Joanna Kijanka, Jan Maurycy, and Rafa&amp;#197;&amp;#130; Szyma. Players take on the roles of Thorgal, his wife Aaricia, their son Jolan, and Kriss&amp;mdash;a deadly female warrior&amp;mdash;, and go on a series of adventures.&amp;#10;&amp;#10;The game consists of 7 (10 for the Gamefound edition) stand-alone scenarios, each taking roughly 90-120 minutes of play. Scenarios do not form a campaign nor have any legacy features, so they can be played by different playgroups and over an extended period of time.&amp;#10;&amp;#10;Thorgal: The Board Game comes with a book of maps. Each scenario is played on a different map that players explore while fulfilling scenario goals and promises completely different adventures, hidden opportunities, and vivid characters. Players are free to choose whether they travel in one group or split to cover more areas at the same time. They also perform side quests that might help them win a scenario.&amp;#10;&amp;#10;Another important feature in the game is the storybook containing narrative descriptions of the encounters that Characters make in each scenario. The adventures described in the book are specific to each scenario and Character, yet a lot of them are randomized within a scenario, offering higher replayability and allowing for repeated play. There are also Side Plots players can pursue to gain benefits and reveal additional pieces of the story.&amp;#10;&amp;#10;Thorgal: The Board Game, while having a strong narrative element, offers a brand new, unique action selection mechanism. In this new mechanism, actions have varying power depending on which other actions have already been taken. Players carefully plan their strategy and sequence of actions taken, as this directly impacts the strength of the effect of each action. This creates an interesting decision space and makes each turn important for everyone at the table.&amp;#10;&amp;#10;Another important mechanic involves Character development. Each character gains experience during the game and advances their abilities. This dynamic progress allows players to make meaningful decisions about how to develop their hero in the game. Characters gather resources and craft objects to help them survive in the dangerous world.&amp;#10;&amp;#10;&amp;mdash;description from the publisher&amp;#10;&amp;#10;",
            thumbnail = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__thumb/img/0hdUKkeZA4E25_VUPUXpSvvJgls=/fit-in/200x150/filters:strip_icc()/pic6724739.jpg",
            image = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__original/img/vs2i4NlxuNMUUZejmu5gEwWfLg0=/0x0/filters:format(jpeg)/pic6724739.jpg",
            year = "2024",
            minAge = "14"
        )
    }
}
