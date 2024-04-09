package com.bradenhirschi.suiteforspotify.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bradenhirschi.suiteforspotify.SuiteForSpotifyApplication
import com.bradenhirschi.suiteforspotify.data.Model

abstract class BaseActivity : AppCompatActivity() {
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (application as SuiteForSpotifyApplication).model
    }
}
