package com.bradenhirschi.suiteforspotify

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.bradenhirschi.suiteforspotify.data.Model

class SuiteForSpotifyApplication : Application() {
    lateinit var model: Model

    override fun onCreate() {
        super.onCreate()
        model = Model
        context = applicationContext
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    companion object {
        lateinit var context: Context
    }
}