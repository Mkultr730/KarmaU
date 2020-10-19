package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.viewModel.FavorViewModel
import com.memolinares.karma_androidpf.viewModel.HomeViewModel
import com.memolinares.karma_androidpf.viewModel.LoginViewModel


class Perfil (user: FirebaseUser?) : Fragment() {
    val useractual = user
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.getUser().addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                view.findViewById<TextView>(R.id.name).text  = snapshot.child("username").getValue().toString()
                requireView().findViewById<TextView>(R.id.puntos).text = snapshot.child("karma").getValue().toString()
            }
        })

        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "Virat Kohli", "Rohit Sharma", "Steve Smith",
            "Kane Williamson", "Ross Taylor"
        )

        // access the listView from xml file
        var mListView = requireView().findViewById<ListView>(R.id.movimientos)
        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
    }

    companion object {
        fun newInstance(user: FirebaseUser?): Perfil = Perfil(user)
    }
}