package com.bradenhirschi.suiteforspotify

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamratzman.spotify.auth.pkce.startSpotifyClientPkceLoginActivity
import com.bradenhirschi.suiteforspotify.auth.SpotifyPkceLoginActivityImpl
import com.bradenhirschi.suiteforspotify.ui.theme.AppTheme

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AuthPage(this)
        }
    }
}

@Composable
fun AuthPage(activity: Activity? = null) {
    AppTheme {
        val typography = MaterialTheme.typography

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Suite for Spotify",
                textAlign = TextAlign.Center,
                maxLines = 2,
                style = typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(128.dp))

            Text(
                "Log in with Spotify",
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
//                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.spotify_green)),
                onClick = {
                activity?.startSpotifyClientPkceLoginActivity(SpotifyPkceLoginActivityImpl::class.java)
            }) {
                Text("Connect to Spotify")
            }
        }
    }
}

@Preview
@Composable
fun AuthPagePreview() {
    AuthPage()
}
