package com.example.projectdraft

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen(
                onTimeout = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            )
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    // Automatically move to MainActivity after 2 seconds
    LaunchedEffect(key1 = true) {
        delay(2000)
        onTimeout()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Project Draft",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SplashPreview() {
    SplashScreen(onTimeout = {})
}
