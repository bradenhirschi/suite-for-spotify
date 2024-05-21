package com.bradenhirschi.suiteforspotify.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adamratzman.spotify.SpotifyException
import com.bradenhirschi.suiteforspotify.Destination
import com.bradenhirschi.suiteforspotify.R
import com.bradenhirschi.suiteforspotify.auth.guardValidSpotifyApi
import com.bradenhirschi.suiteforspotify.ui.theme.AppTheme

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        guardValidSpotifyApi(HomeActivity::class.java) { api ->
            if (!api.isTokenValid(true).isValid) throw SpotifyException.ReAuthenticationNeededException()

            setContent {
                // ActionHomeViewPage(this)
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
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
    AppTheme {
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

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        navController.navigate(Destination.TopTracks.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            ) {
                Text(
                    "See my top tracks",
                    style = typography.headlineSmall,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Red, RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        navController.navigate(Destination.TopTracks.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            ) {
                Text(
                    "See my top artists",
                    style = typography.headlineSmall,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        navController.navigate(Destination.Playlists.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            ) {
                Text(
                    "See my playlists",
                    style = typography.headlineSmall,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        navController.navigate(Destination.TrackSearch.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            ) {
                Text(
                    "Search for tracks",
                    style = typography.headlineSmall,
                )
            }


        }
    }
}