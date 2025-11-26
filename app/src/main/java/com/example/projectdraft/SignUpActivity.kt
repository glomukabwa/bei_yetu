package com.example.projectdraft

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment // **New Import for Alignment**
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectdraft.ui.theme.ProjectdraftTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjectdraftTheme {
                SignUpScreen(
                    onSignUp = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    },
                    onGoToLogin = {
                        finish() // goes back to LoginActivity
                    }
                )
            }
        }
    }
}

@Composable
fun SignUpScreen(onSignUp: () -> Unit, onGoToLogin: () -> Unit) {
    var username by remember { mutableStateOf("") }
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
            "🛒 BeiYetu", // Placeholder for your icon/logo
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 64.dp)
        )

        // Correction 3: Centered text is achieved by the parent Column's horizontalAlignment
        Text(
            "Create Account",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

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
            onClick = onSignUp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }

        // Correction 4: Centered TextButton is achieved by the parent Column's horizontalAlignment
        TextButton(onClick = onGoToLogin) {
            Text("Back to Login")
        }
    }
}

@Preview(showBackground = true, name = "Sign Up Screen Preview")
@Composable
fun SignUpScreenPreview() {
    ProjectdraftTheme {
        SignUpScreen(
            onSignUp = { /* Preview: Sign Up Clicked */ },
            onGoToLogin = { /* Preview: Go to Login Clicked */ }
        )
    }
}