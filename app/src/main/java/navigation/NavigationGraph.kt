package navigation

object NavigationGraph {
    var id_counter = 1

    val id = id_counter++

    object Destination {
        const val works = 2
        const val newsFeed = 5
        const val artist = 4
    }

    object Action {
        const val to_menu_newsFeed = 5
        const val to_menu_works = 6
        const val to_menu_artist = 7
    }

    object Args {
        const val menu_newsFeed_id = "menuNewsFeedId"
        const val menu_works_id = "menuWorksId"
        const val menu_artist_id = "menuArtistId"
    }
}