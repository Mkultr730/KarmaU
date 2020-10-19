package com.memolinares.karma_androidpf.repository

import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.model.User

class FavorRepository {
    val database = Firebase.database.getReference("Favor")

    fun askFavor(favor: Favor) = database.push().setValue(favor)

    fun getRefenceFavor() = Firebase.database.getReference("Favor")

    fun setFavor(favorId: String, userID: String) {
        Firebase.database.getReference("Favor").child(favorId).child("user_employee")
            .setValue(userID)
        Firebase.database.getReference("Favor").child(favorId).child("stage")
            .setValue("Asignado")
    }

    fun setCheckCl(favorId: String) = getRefenceFavor().child(favorId).child("client_check").setValue(true)
    fun setCheckEmpl(favorId: String) = getRefenceFavor().child(favorId).child("employee_check").setValue(true)
    fun completestage(favorId: String) = getRefenceFavor().child(favorId).child("stage").setValue("Completado")
    fun kamarplus(userId: String) = Firebase.database.getReference("User").child(userId).child("karma").setValue((getkarma(userId)+1).toString())
    fun karmaless(userId: String) = Firebase.database.getReference("User").child("karma").setValue((getkarma(userId)-2).toString())
    fun getkarma(userId: String): Int {
        var karma: Int = 0
        Firebase.database.getReference("User").child(userId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                karma = snapshot.child("karma").getValue().toString().toInt()
            }
        })
        return karma
    }
}