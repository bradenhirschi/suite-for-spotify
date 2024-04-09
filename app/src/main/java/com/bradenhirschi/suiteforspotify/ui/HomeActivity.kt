package com.bradenhirschi.suiteforspotify.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adamratzman.spotify.SpotifyException
import com.bradenhirschi.suiteforspotify.Destination
import com.bradenhirschi.suiteforspotify.R
import com.bradenhirschi.suiteforspotify.auth.guardValidSpotifyApi

//import com.example.codepizza.utils.toast

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guardValidSpotifyApi(HomeActivity::class.java) { api ->
            if (!api.isTokenValid(true).isValid) throw SpotifyException.ReAuthenticationNeededException()

            setContent {
                // ActionHomeViewPage(this)
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppScreen(api = api)
                    }
                }
            }
        }
    }
}

@Composable
fun ActionHomeViewPage(activity: BaseActivity? = null, navController: NavHostController) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "What do you want to do?",
                style = typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.spotify_green)),
                onClick = {
                    navController.navigate(Destination.TopTracks.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                Text("See my top tracks")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate(Destination.Playlists.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text("See my playlists")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.orange)),
                onClick = {
                    navController.navigate(Destination.TrackSearch.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                Text("See a search example")
            }


        }
    }
}