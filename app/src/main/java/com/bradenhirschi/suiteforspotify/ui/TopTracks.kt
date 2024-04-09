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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.adamratzman.spotify.models.Track
import com.skydoves.landscapist.glide.GlideImage
//import com.bradenhirschi.suiteforspotify.utils.toast

@Composable
fun TopTracksPage(activity: BaseActivity? = null, tracks: List<Track>) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Your Top Tracks",
                style = typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            TrackList(tracks)

        }
    }

}

@Preview
@Composable
fun TopTracksPagePreview() {
    TopTracksPage(tracks = listOf())
}

@Composable
private fun TrackList(tracks: List<Track>) {
    val context = LocalContext.current
    LazyColumn {
        items(
            items = tracks, itemContent = { track ->
                TrackRow(track = track, onTrackClick = {
//                    toast(context, "You clicked ${track.name} - opening in spotify")
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
