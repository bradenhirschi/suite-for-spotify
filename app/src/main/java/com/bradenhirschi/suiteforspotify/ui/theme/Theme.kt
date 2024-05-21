package com.bradenhirschi.suiteforspotify.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = darkColorScheme(
        primary = Color(0xFF1BB954),
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFFE17634),
        onSecondary = Color(0xFFFFFFFF),
        tertiary = Color(0xFF8568A6),
        onTertiary = Color(0xFFFFFFFF),
        background = Color(0xFF040205),
        onBackground = Color(0xFFFFFFFF),
        surface = Color(0xFF040205),
        onSurface = Color(0xFFFFFFFF),
    )
) {
    content()
}