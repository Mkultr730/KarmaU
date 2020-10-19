package com.memolinares.karma_androidpf.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.repository.FavorRepository
import com.memolinares.karma_androidpf.repository.HomeRepository

class HomeViewModel: ViewModel()  {

    var homeRepository = HomeRepository()

    fun getNombre(email: String?){
        val myRef = homeRepository.getDatabaseR()
        var userLiveData = MutableLiveData<List<Favor>>()
        val userlist = mutableListOf<Favor>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (childDataSnapshot in dataSnapshot.children) {
                    val value: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    userlist.add(value)
                }
                userLiveData.value = userlist
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
        //homeRepository.getNombre(email)
    }


}