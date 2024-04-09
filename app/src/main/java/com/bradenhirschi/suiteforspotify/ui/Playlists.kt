package com.bradenhirschi.suiteforspotify.ui

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
import com.adamratzman.spotify.models.SimplePlaylist
import com.skydoves.landscapist.glide.GlideImage
//import com.bradenhirschi.suiteforspotify.utils.toast

@Composable
fun PlaylistsPage(activity: BaseActivity? = null, playlists: List<SimplePlaylist>) {
    MaterialTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Your Playlists",
                style = typography.headlineSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            PlaylistList(playlists)

        }
    }

}

@Preview
@Composable
fun PlaylistsPagePreview() {
    PlaylistsPage(playlists = listOf())
}

@Composable
private fun PlaylistList(playlists: List<SimplePlaylist>) {
    val context = LocalContext.current
    LazyColumn {
        items(
            items = playlists, itemContent = { playlist ->
                PlaylistRow(playlist = playlist)
                Divider()
            })
    }
}

@Composable
private fun PlaylistRow(playlist: SimplePlaylist) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val imageModifier = Modifier
            .height(46.dp)
            .width(46.dp)
            .clip(shape = CircleShape)

        GlideImage(
            imageModel = playlist.images.firstOrNull()?.url ?: "https://picsum.photos/300/300",
            contentDescription = null,
            modifier = imageModifier
        )

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = playlist.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = playlist.description.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
