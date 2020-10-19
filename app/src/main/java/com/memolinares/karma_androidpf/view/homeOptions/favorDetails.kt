package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.viewModel.FavorViewModel
import com.memolinares.karma_androidpf.viewModel.LoginViewModel

class favorDetails(user: FirebaseUser?, favor: Favor) : Fragment() {
    val favors = favor
    val user = user
    val loginViewModel: LoginViewModel by viewModels()
    val favorViewModel: FavorViewModel by viewModels()

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
        loginViewModel.getUser(favors.user_client).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    view.findViewById<TextView>(R.id.user).text  = snapshot.child("username").getValue().toString()
                }
            })
        view.findViewById<TextView>(R.id.type).text = favors.type
        view.findViewById<TextView>(R.id.details).text = favors.details

        view.findViewById<Button>(R.id.submit_favor).setOnClickListener{
            if (user != null) {
                if (favors.stage != "Inicial"){
                    view.findViewById<Button>(R.id.submit_favor).visibility = View.INVISIBLE
                }else{
                    Toast.makeText(context, "Ya se ha tomado", Toast.LENGTH_LONG).show()
                }
                if (user.uid.equals(favors.user_employee)){
                    view.findViewById<Button>(R.id.completo).visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        fun newInstance(auth: FirebaseUser?, favor: Favor): favorDetails = favorDetails(auth, favor)
    }
}

