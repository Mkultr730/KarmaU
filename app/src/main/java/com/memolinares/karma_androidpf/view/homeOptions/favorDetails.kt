package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.viewModel.LoginViewModel

class favorDetails(user: FirebaseUser?, favor: Favor) : Fragment() {
    val favors = favor
    val user = user
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favor_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.gtUser().addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    view.findViewById<TextView>(R.id.user).text  = snapshot.child("username").toString()
                }
            })
        //view.findViewById<TextView>(R.id.user).text = favors.user_client
        view.findViewById<TextView>(R.id.type).text = favors.type
        view.findViewById<TextView>(R.id.details).text = favors.details
    }

    companion object {
        fun newInstance(auth: FirebaseUser?, favor: Favor): favorDetails = favorDetails(auth, favor)
    }
}

