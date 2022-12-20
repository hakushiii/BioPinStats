package com.example.biopinstats.authentication

import androidx.lifecycle.*
import com.example.biopinstats.database.dao.UserDao
import com.example.biopinstats.database.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(private val userDao: UserDao): ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private fun getUser(username: String, password: String): User? = userDao.getUser(username, password)

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

    fun userExist(username: String, password: String): Boolean {
        val user: User? = getUser(username, password)
        if (user != null) {
            _user.value = user.username
            return true
        }
        return false
    }

    fun isEntryValid(username: String, password: String): Boolean {
        if (username.isBlank() || password.isBlank())
            return false
        return true
    }

    fun getAllUser(): Flow<List<User>> = userDao.getAll()
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