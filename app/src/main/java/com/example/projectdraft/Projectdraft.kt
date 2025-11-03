package com.example.projectdraft

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// You can customize colors later
private val LightColors = lightColorScheme(
    primary = Color(0xFF00695C),
    secondary = Color(0xFF26A69A),
    tertiary = Color(0xFF80CBC4)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF80CBC4),
    secondary = Color(0xFF4DB6AC),
    tertiary = Color(0xFF26A69A)
)

@Composable
fun ProjectdraftTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
