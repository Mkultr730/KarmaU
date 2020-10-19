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
    fun getNombre(email: String?): String {
        var nombre = "Nombre no encontrado"
        database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (childDataSnapshot in dataSnapshot.children) {
                    val value: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    if (value.equals(email)){
                        nombre = dataSnapshot.child("username").getValue<String>()!!
                    }
                }
            }
        })
        return nombre
    }
}