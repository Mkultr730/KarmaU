package com.memolinares.karma_androidpf.repository

import android.content.ContentValues
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.model.Favor

class HomeRepository {
    val database = Firebase.database.getReference("User")

    fun getDatabaseR() = database
}