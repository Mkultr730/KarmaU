package com.memolinares.karma_androidpf.repository


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.memolinares.karma_androidpf.model.User
import com.google.firebase.ktx.Firebase

class LoginRepository  {
    val database = Firebase.database.getReference("User")
    var auth = FirebaseAuth.getInstance()

    fun signIn(user: User) = auth.signInWithEmailAndPassword(user.email, user.password)

    fun createUser(user: User): Task<AuthResult> {
        //savedUser(user)
        return auth.createUserWithEmailAndPassword(user.email, user.password)
    }
    fun getCurrentUser() = auth.currentUser

    fun savedUser(user: User) = database.push().setValue(user)
}