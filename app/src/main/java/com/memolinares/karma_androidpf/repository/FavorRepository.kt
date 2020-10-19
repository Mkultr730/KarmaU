package com.memolinares.karma_androidpf.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.model.User

class FavorRepository {
    val database = Firebase.database.getReference("Favor")


    fun askFavor(favor: Favor) = database.push().setValue(favor)

    fun getRefenceFavor() = Firebase.database.getReference("Favor")
}