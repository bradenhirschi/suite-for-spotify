package com.bradenhirschi.suiteforspotify.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adamratzman.spotify.SpotifyClientApi
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.bradenhirschi.suiteforspotify.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AppScreen(api: SpotifyClientApi) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController,
            appItems = Destination.toList)},
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavigation(navController = navController, api = api)
            }
        }
    )
}

@Composable
fun AppNavigation(navController: NavHostController, api: SpotifyClientApi) {
    NavHost(navController, startDestination = Destination.Home.route) {
        composable(Destination.Home.route) {
            ActionHomeViewPage(navController = navController)
        }

        composable(Destination.TopTracks.route) {
            var tracks by remember { mutableStateOf<List<Track>>(emptyList()) }

            LaunchedEffect(api) {
                val topTracks = withContext(Dispatchers.IO) {
                    api.personalization.getTopTracks().items
                }
                tracks = topTracks
            }

            TopTracksPage(tracks = tracks)
        }

        composable(Destination.Playlists.route) {
            var playlists by remember { mutableStateOf<List<SimplePlaylist>>(emptyList()) }

            LaunchedEffect(api) {
                playlists = withContext(Dispatchers.IO) {
                    api.playlists.getClientPlaylists().items
                }
            }

            PlaylistsPage(playlists = playlists)
        }

        composable(Destination.TrackSearch.route) {
            var tracks by remember { mutableStateOf<List<Track>>(emptyList()) }

            LaunchedEffect(api) {
                val topTracks = withContext(Dispatchers.IO) {
                    api.search.searchTrack("Billy Joel").items
                }
                tracks = topTracks
            }

            TrackSearchPage(tracks = tracks)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<Destination>) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        appItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title)},
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}