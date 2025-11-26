package com.example.projectdraft

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserSessionViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<String?>(null)
    val currentUser: StateFlow<String?> = _currentUser

    fun login(email: String) {
        _currentUser.value = email
    }

    fun logout() {
        _currentUser.value = null
    }
}
