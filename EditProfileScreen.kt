package com.example.projectdraft;

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.ChevronLeft
import com.example.projectdraft.ui.theme.ProjectdraftTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


@Composable
fun EditProfileScreen(
    firstNameInit: String = "John",
    lastNameInit: String = "Doe",
    emailInit: String = "johndoe@gmail.com",
    mobileInit: String = "0123456789",
    onBackClicked: () -> Unit,
    onSaveClicked: (firstName: String, lastName: String, email: String, mobile: String) -> Unit
) {
    var firstName by remember { mutableStateOf(firstNameInit) }
    var lastName by remember { mutableStateOf(lastNameInit) }
    var email by remember { mutableStateOf(emailInit) }
    var mobile by remember { mutableStateOf(mobileInit) }

    val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && mobile.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Back"
                )
            }
            Spacer(Modifier.width(8.dp).padding(16.dp))
            Text("Edit Profile", style = MaterialTheme.typography.headlineSmall)
        }

        Spacer(Modifier.height(24.dp))

        // --- Input Fields ---
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(32.dp))

        // --- Save Button ---
        Button(
            onClick = { onSaveClicked(firstName, lastName, email, mobile) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text("SAVE")
        }
    }
}

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun EditProfileScreenPreview() {
    ProjectdraftTheme {
        EditProfileScreen(
            onBackClicked = {},
            onSaveClicked = { _, _, _, _ -> }
        )
    }
}
