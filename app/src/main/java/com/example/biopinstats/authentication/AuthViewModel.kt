package com.example.biopinstats.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.biopinstats.database.dao.UserDao
import com.example.biopinstats.database.models.User
import kotlinx.coroutines.launch

class AuthViewModel(private val userDao: UserDao): ViewModel() {
    private fun insertUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(
                user = user
            )
        }
    }

    private fun createUser(username: String, password: String): User {
        return User(
            username = username,
            password = password,
        )
    }

    fun newUser(username: String, password: String) {
        val user = createUser(username,password)
        insertUser(user)
    }
}

class AuthViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}