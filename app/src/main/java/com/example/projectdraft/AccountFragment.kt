package com.example.projectdraft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectdraft.ui.theme.ProjectdraftTheme // Adjust package path as needed
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material.icons.filled.PhotoCamera // <-- Added for UserProfileScreen profile picture edit icon

// Define the custom color (Hex #3700B3)
val DeepPurple = Color(0xFF3700B3)

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // State to hold the dynamic data for the Fragment
                var pushState by remember { mutableStateOf(true) }

                ProjectdraftTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        AccountScreen(
                            userName = "Mr. John Doe", // Data passed from the Fragment's scope
                            onLogoutClicked = { /* TODO: Implement Logout navigation */ },
                            onUserProfileClicked = { /* TODO: Implement navigation to User Profile screen */ },
                            isPushEnabled = pushState,
                            onPushToggle = { pushState = it }
                        )
                    }
                }
            }
        }
    }
}

// Helper Composable for each Settings Item
@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    showChevron: Boolean = true,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(title, modifier = Modifier.weight(1f))

        trailingContent?.invoke()

        if (showChevron) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
    HorizontalDivider()
}

// Refactored AccountScreen to accept parameters
@Composable
fun AccountScreen(
    userName: String,
    onLogoutClicked: () -> Unit,
    onUserProfileClicked: () -> Unit,
    isPushEnabled: Boolean,
    onPushToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {

        // --- Header Section ---
        Text("Settings", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Placeholder for User Image
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Welcome", style = MaterialTheme.typography.bodySmall)
                    Text(userName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold) // <-- Dynamic Name
                }
            }
            // Logout Icon
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Logout",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onLogoutClicked) // <-- Dynamic Logout Action
            )
        }

        HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))
        // --- End Header Section ---

        // --- Settings Items ---
        SettingsItem(
            icon = Icons.Default.Person,
            title = "User Profile",
            onClick = onUserProfileClicked // <-- Dynamic User Profile Action
        )

        SettingsItem(
            icon = Icons.Default.Lock,
            title = "Change Password",
            onClick = { /* TODO: Show Change Password Dialog/Screen */ }
        )

        SettingsItem(
            icon = Icons.Default.HelpOutline,
            title = "FAQs",
            onClick = { /* TODO: Navigate to FAQs */ }
        )

        // Push Notification item with a switch
        SettingsItem(
            icon = Icons.Default.Notifications,
            title = "Push Notification",
            onClick = { onPushToggle(!isPushEnabled) }, // Toggle state on click
            showChevron = false,
            trailingContent = {
                Switch(
                    checked = isPushEnabled, // <-- Dynamic State
                    onCheckedChange = onPushToggle, // <-- Dynamic Toggle Action
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = DeepPurple,
                        checkedThumbColor = Color.White
                    )
                )
            }
        )
        // --- End Settings Items ---

        Spacer(Modifier.height(32.dp))

        // --- WhatsApp Query Section ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "If you have any other query you can reach out to us.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = { /* TODO: Open WhatsApp link */ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = DeepPurple
                    )
                ) {
                    Text("WhatsApp Us", fontWeight = FontWeight.Bold)
                }
            }
        }
        // --- End WhatsApp Query Section ---
    }
}

// Preview Composable updated to pass mock data
@Preview(showBackground = true, name = "Settings Screen Preview")
@Composable
fun AccountScreenPreview() {
    ProjectdraftTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AccountScreen(
                userName = "Mr. John Doe",
                onLogoutClicked = { },
                onUserProfileClicked = { },
                isPushEnabled = true,
                onPushToggle = { }
            )
        }
    }
}

@Composable
fun UserProfileScreen(
    onBackClicked: () -> Unit,
    onSaveClicked: (firstName: String, lastName: String, mobile: String) -> Unit
) {
    // State variables for form fields
    var firstName by remember { mutableStateOf("John") }
    var lastName by remember { mutableStateOf("Doe") }
    // Note: Email is often read-only, but kept as a field for visual match
    val email = "johndoe@gmail.com"
    var mobile by remember { mutableStateOf("+91-123456789") }

    val isFormValid = firstName.isNotBlank() && lastName.isNotBlank() && mobile.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Top Bar (Back Arrow and Title) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft, // You might need to import this icon
                    contentDescription = "Back"
                )
            }
            Spacer(Modifier.width(8.dp))
            Text("User Profile", style = MaterialTheme.typography.headlineSmall)
        }

        Spacer(Modifier.height(24.dp))

        // --- Profile Picture Section ---
        Box(
            modifier = Modifier
                .size(96.dp) // Adjusted size for profile picture
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            // Placeholder for user image. In a real app, use Coil/Glide to load an image.
            // Image(...)

            // Camera/Edit Icon (small circle)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(DeepPurple)
                    .align(Alignment.BottomEnd)
                    .offset(x = 4.dp, y = 4.dp), // Adjust position
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera, // You might need to import this icon
                    contentDescription = "Change Picture",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // --- Input Fields ---
        OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = email, onValueChange = { /* read only */ }, label = { Text("E-Mail") }, readOnly = true, modifier = Modifier.fillMaxWidth())
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
            onClick = { onSaveClicked(firstName, lastName, mobile) },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("SAVE")
        }
    }
}

// Preview for UserProfileScreen
@Preview(showBackground = true, name = "User Profile Screen Preview")
@Composable
fun UserProfileScreenPreview() {
    ProjectdraftTheme {
        UserProfileScreen(
            onBackClicked = {},
            onSaveClicked = { _, _, _ -> }
        )
    }
}

@Composable
fun ChangePasswordDialog(
    onDismissRequest: () -> Unit,
    onSaveClicked: (newPassword: String, confirmPassword: String) -> Unit
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isFormValid = newPassword.isNotBlank() && confirmPassword.isNotBlank() && (newPassword == confirmPassword)

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            // Use a specific shape and color to match the image style
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Change Password",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { onSaveClicked(newPassword, confirmPassword) },
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("SAVE")
                }
            }
        }
    }
}

// Preview for ChangePasswordDialog
@Preview(showBackground = true, name = "Change Password Dialog Preview")
@Composable
fun ChangePasswordDialogPreview() {
    ProjectdraftTheme {
        // The dialog is wrapped in a Box to simulate being centered on a screen
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)), contentAlignment = Alignment.Center) {
            ChangePasswordDialog(
                onDismissRequest = {},
                onSaveClicked = { _, _ -> }
            )
        }
    }
}