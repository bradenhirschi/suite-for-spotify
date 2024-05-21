package com.bradenhirschi.suiteforspotify.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.adamratzman.spotify.models.Track
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role.Companion.Button
import com.adamratzman.spotify.SpotifyClientApi
import com.bradenhirschi.suiteforspotify.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TrackSearchPage(activity: BaseActivity? = null, api: SpotifyClientApi) {
    MaterialTheme {
        val typography = MaterialTheme.typography
        var tracks by remember { mutableStateOf<List<Track>>(emptyList()) }
        var searchQuery by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Search tracks",
                style = typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(end = 10.dp),
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search for tracks") },
                )

                Button(onClick = {
                    GlobalScope.launch {
                        if (searchQuery.isNotEmpty()) {
                            val topTracks = withContext(Dispatchers.IO) {
                                api.search.searchTrack(searchQuery).items
                            }
                            tracks = topTracks
                        }
                    }
                }) {
                    Text("Go")
                }
            }



            TrackList(tracks)
        }
    }

}

@Composable
private fun TrackList(tracks: List<Track>) {
    val context = LocalContext.current
    LazyColumn {
        items(
            items = tracks, itemContent = { track ->
                TrackRow(track = track, onTrackClick = {
                    val browserIntent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(track.externalUrls.first { it.name == "spotify" }.url)
                        )
                    startActivity(context, browserIntent, null)
                })
                Divider()
            })
    }
}

@Composable
private fun TrackRow(track: Track, onTrackClick: (Track) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onTrackClick(track) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val imageModifier = Modifier
            .height(46.dp)
            .width(46.dp)
            .clip(shape = CircleShape)

        GlideImage(
            imageModel = track.album.images.firstOrNull()?.url ?: "https://picsum.photos/300/300",
            contentDescription = null,
            modifier = imageModifier
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = track.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "By ${track.artists.joinToString(", ") { it.name }}",
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}
