package com.memolinares.karma_androidpf.repository

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.memolinares.karma_androidpf.model.User

class LoginRepository  {
    var auth = FirebaseAuth.getInstance()

    fun signIn(user: User) = auth.signInWithEmailAndPassword(user.email, user.password)

    fun createUser(user: User) = auth.createUserWithEmailAndPassword(user.email, user.password)

    fun getCurrentUser() = auth.currentUser
}