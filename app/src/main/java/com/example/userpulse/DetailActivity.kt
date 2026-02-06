package com.example.userpulse.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name") ?: "Not Available"
        val username = intent.getStringExtra("username") ?: "Not Available"
        val email = intent.getStringExtra("email") ?: "Not Available"
        val phone = intent.getStringExtra("phone") ?: "Not Available"
        val website = intent.getStringExtra("website") ?: "Not Available"

        setContent {
            // Material 3 Theme use kar rahe hain
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "User Details",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFF008080)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Saari required fields display ho rahi hain
                    DetailItem("Full Name", name)
                    DetailItem("Username", username)
                    DetailItem("Email ID", email)
                    DetailItem("Phone Number", phone)
                    DetailItem("Website", website)
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, color = Color.Gray, style = MaterialTheme.typography.labelLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
    }
}