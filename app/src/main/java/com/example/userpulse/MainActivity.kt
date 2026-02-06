package com.example.userpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import datalayer.User
import viewmodel.UserViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val error = viewModel.errorMessage.value
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }

            UserListScreen(viewModel) { user ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("name", user.name)
                    putExtra("username", user.username)
                    putExtra("email", user.email)
                    putExtra("phone", user.phone)
                    putExtra("website", user.website)
                }

                startActivity(intent)
            }
        }
    }
}

@Composable
fun UserListScreen(viewModel: UserViewModel, onClick: (User) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UserPulse", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF008080))
            )
        }
    ) { padding ->
        if (viewModel.isLoading.value) {
            Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
        } else {
            LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
                items(viewModel.users.value) { user ->
                    UserCard(user, onClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCard(user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick(user) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(50.dp).background(Color(0xFFF4511E), CircleShape), contentAlignment = Alignment.Center) {
                Text(user.name.take(2).uppercase(), color = Color.White, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(user.name, fontWeight = FontWeight.Bold)
                Text(user.email, color = Color.Gray, fontSize = 12.sp)
            }
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
        }
    }
}