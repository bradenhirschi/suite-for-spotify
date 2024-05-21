package com.bradenhirschi.suiteforspotify

sealed class Destination (val route: String, val icon: Int, val title: String){
    object Home : Destination(
        route = "home",
        icon = R.drawable.baseline_home_24,
        title = "Home"
    )

    object TopTracks : Destination(
        route = "topTracks",
        icon = R.drawable.baseline_assessment_24,
        title = "Tracks"
    )

    object TopArtists : Destination(
        route = "topArtists",
        icon = R.drawable.baseline_blur_on_24,
        title = "Artists"
    )

    object Playlists : Destination(
        route = "playlists",
        icon = R.drawable.baseline_align_horizontal_left_24,
        title = "Playlists"
    )

    object TrackSearch : Destination(
        route = "trackSearch",
        icon = R.drawable.baseline_article_24,
        title = "Search"
    )

    companion object {
        val toList = listOf(Home, TopTracks, TopArtists, Playlists, TrackSearch)
    }
}