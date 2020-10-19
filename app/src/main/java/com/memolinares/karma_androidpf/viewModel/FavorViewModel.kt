package com.memolinares.karma_androidpf.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.model.User
import com.memolinares.karma_androidpf.repository.FavorRepository
import java.nio.file.Files.list

class FavorViewModel: ViewModel() {
    var favorLiveData = MutableLiveData<List<Favor>>()
    val favlist = mutableListOf<Favor>()
    var auth = FirebaseAuth.getInstance()

    var favorRepository = FavorRepository()

    fun askFavor(favor: Favor) = favorRepository.askFavor(favor)

    fun setFavor(favorId: String, userId: String) = favorRepository.setFavor(favorId, userId)

    init {
        getFavor()
    }

    fun getFavor(){
        //val database = Firebase.database
        val myRef = favorRepository.getRefenceFavor()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (childDataSnapshot in dataSnapshot.children) {

                    val value: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    value.key = childDataSnapshot.key.toString()
                    if (value.stage.equals("Inicial") && value.user_client != auth.uid){
                        favlist.add(value)
                    }
                }
                favorLiveData.value = favlist
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
    }
}