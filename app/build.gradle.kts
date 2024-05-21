plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.bradenhirschi.suiteforspotify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bradenhirschi.suiteforspotify"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            buildConfigField("String", "SPOTIFY_CLIENT_ID", "\"0d7afea9e025418892dd31674b6170ee\"")
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_AUTH",
                "\"spotifyandroidplayground://spotify-auth\""
            )
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_PKCE",
                "\"spotifyandroidplayground://spotify-pkce\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "SPOTIFY_CLIENT_ID", "\"0d7afea9e025418892dd31674b6170ee\"")
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_AUTH",
                "\"spotifyandroidplayground://spotify-auth\""
            )
            buildConfigField(
                "String",
                "SPOTIFY_REDIRECT_URI_PKCE",
                "\"spotifyandroidplayground://spotify-pkce\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // From CodePizza example
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // From Spotify demo
    implementation("androidx.appcompat:appcompat:1.4.1")

    // Spotify api Kotlin wrapper
    implementation("com.adamratzman:spotify-api-kotlin-android:3.8.6")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Images
    implementation("com.github.bumptech.glide:glide:4.13.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.1")
    implementation("com.github.skydoves:landscapist-glide:1.5.0" )
}