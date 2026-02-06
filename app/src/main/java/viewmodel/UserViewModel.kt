package viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import datalayer.RetrofitInstance
import datalayer.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var users = mutableStateOf<List<User>>(emptyList())
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    init { fetchUsers() }

    fun fetchUsers() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                users.value = RetrofitInstance.api.getUsers()
            } catch (_: Exception) {
                errorMessage.value = "Failed to fetch users. Check internet."
            } finally {
                isLoading.value = false
        }
    }
}}