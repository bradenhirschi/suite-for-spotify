package com.bradenhirschi.suiteforspotify.data

import com.adamratzman.spotify.auth.SpotifyDefaultCredentialStore
import com.bradenhirschi.suiteforspotify.BuildConfig
import com.bradenhirschi.suiteforspotify.SuiteForSpotifyApplication

object Model {
    val credentialStore by lazy {
        SpotifyDefaultCredentialStore(
            clientId = BuildConfig.SPOTIFY_CLIENT_ID,
            redirectUri = BuildConfig.SPOTIFY_REDIRECT_URI_PKCE,
            applicationContext = SuiteForSpotifyApplication.context
        )
    }
}