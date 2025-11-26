package com.example.projectdraft

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectdraft.ui.theme.ProjectdraftTheme

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjectdraftTheme {
                LoginScreen(
                    onLogin = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    },
                    onGoToSignUp = {
                        startActivity(Intent(this, SignUpActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(onLogin: () -> Unit, onGoToSignUp: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        // *** CORRECTION 1: Center all children horizontally ***
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // *** CORRECTION 2: Icon/Logo at the top and center ***
        Text(
            "🛒 BeiYetu",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 64.dp)
        )

        // Correction 3: Centered text is achieved by the parent Column's horizontalAlignment
        Text(
            "Welcome Back",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(Modifier.height(8.dp))

        // Correction 4: Centered TextButton is achieved by the parent Column's horizontalAlignment
        TextButton(onClick = onGoToSignUp) {
            Text("Don't have an account? Sign Up")
        }
    }
}

@Preview(showBackground = true, name = "Login Screen Preview")
@Composable
fun LoginScreenPreview() {
    ProjectdraftTheme {
        LoginScreen(
            onLogin = { /* Preview: Login Clicked */ },
            onGoToSignUp = { /* Preview: Sign Up Clicked */ }
        )
    }
}