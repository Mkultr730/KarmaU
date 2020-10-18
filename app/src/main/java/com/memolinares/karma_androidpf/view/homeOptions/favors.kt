package com.memolinares.karma_androidpf.view.homeOptions

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import kotlinx.android.synthetic.main.fragment_favors.view.*

class favors (user: FirebaseUser?) : Fragment() {
    private var adapter = Adapter(ArrayList())
    private var postListener: ValueEventListener? = null
    private lateinit var postReference: DatabaseReference
    val favlist = mutableListOf<Favor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // requireView gets the root view for the fragment's layout
        // (the one returned by onCreateView).
        requireView().favors_recycler.adapter = adapter
        requireView().favors_recycler.layoutManager = LinearLayoutManager(requireContext())

        val database = Firebase.database
        val myRef = database.getReference("Favor")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (childDataSnapshot in dataSnapshot.children) {
                    val value: Favor = childDataSnapshot.getValue(Favor::class.java)!!
                    favlist.add(value)
                }
                //Log.d(TAG, "Value is: $value")
                adapter.favors.clear()
                adapter.favors.addAll(favlist)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

    }
    companion object {
        fun newInstance(auth: FirebaseUser?): favors = favors(auth)
    }

}