package com.memolinares.karma_androidpf.viewModel

import androidx.lifecycle.ViewModel
import com.memolinares.karma_androidpf.model.User
import com.memolinares.karma_androidpf.repository.LoginRepository

class LoginViewModel:  ViewModel() {
    val loginRepository = LoginRepository()
    fun signIn (email: String, password: String) = loginRepository.signIn(User("",email, password, 0, ""))
    fun signUp (nombre: String, email: String, password: String, rol: String) = loginRepository.createUser(User(nombre, email, password, 2, rol))
    fun getCurrentUser() = loginRepository.getCurrentUser()
}