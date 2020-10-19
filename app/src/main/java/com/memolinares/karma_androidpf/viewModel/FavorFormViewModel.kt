package com.memolinares.karma_androidpf.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.repository.FavorRepository

class FavorFormViewModel: ViewModel() {

    var favorFormLiveData = MutableLiveData<List<Favor>>()
    val favFormlist = mutableListOf<Favor>()
    var favorRepository = FavorRepository()

    init {
        getUserFavors()
    }

    fun getUserFavors() {
        val myRef = favorRepository.getRefenceFavor()
        var auth = FirebaseAuth.getInstance().currentUser

        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (childDataSnapshot in dataSnapshot.children) {
                    val value: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    value.key = childDataSnapshot.key.toString()
                    if (auth != null) {
                        if (value.user_client == auth.uid){
                            favFormlist.add(value)
                        }
                    }
                }
                favorFormLiveData.value = favFormlist
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
    }

    fun setCheckCl(favorID: String) = favorRepository.setCheckCl(favorID)
}