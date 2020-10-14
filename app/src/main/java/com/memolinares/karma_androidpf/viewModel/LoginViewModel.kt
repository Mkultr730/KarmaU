package com.memolinares.karma_androidpf.viewModel

import androidx.lifecycle.ViewModel
import com.memolinares.karma_androidpf.model.User
import com.memolinares.karma_androidpf.repository.LoginRepository

class LoginViewModel:  ViewModel() {
    val loginRepository = LoginRepository()
    fun signIn (email: String, password: String) = loginRepository.signIn(User(email, password, "luis"))
    fun signUp (email: String, password: String) = loginRepository.createUser(User(email, password, "luis"))
    fun getCurrentUser() = loginRepository.getCurrentUser()
}