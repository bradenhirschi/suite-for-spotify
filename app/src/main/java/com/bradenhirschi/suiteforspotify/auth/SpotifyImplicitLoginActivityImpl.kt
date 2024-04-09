package com.bradenhirschi.suiteforspotify.auth

import com.adamratzman.spotify.SpotifyImplicitGrantApi
import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.auth.implicit.AbstractSpotifyAppImplicitLoginActivity
import com.bradenhirschi.suiteforspotify.BuildConfig
import com.bradenhirschi.suiteforspotify.SuiteForSpotifyApplication
//import com.bradenhirschi.suiteforspotify.ui.ActionHomeActivity
//import com.adamratzman.spotifyandroidexample.toast

class SpotifyImplicitLoginActivityImpl : AbstractSpotifyAppImplicitLoginActivity() {
    override val state: Int = 1337
    override val clientId: String = BuildConfig.SPOTIFY_CLIENT_ID
    override val redirectUri: String = BuildConfig.SPOTIFY_REDIRECT_URI_AUTH
    override val useDefaultRedirectHandler: Boolean = false
    override fun getRequestingScopes(): List<SpotifyScope> = SpotifyScope.values().toList()

    override fun onSuccess(spotifyApi: SpotifyImplicitGrantApi) {
        val model = (application as SuiteForSpotifyApplication).model
        model.credentialStore.setSpotifyApi(spotifyApi)
//        toast("Authentication via spotify-auth has completed. Launching TrackViewActivity..")
//        startActivity(Intent(this, ActionHomeActivity::class.java))
    }

    override fun onFailure(errorMessage: String) {
//        toast("Auth failed: $errorMessage")
    }
}