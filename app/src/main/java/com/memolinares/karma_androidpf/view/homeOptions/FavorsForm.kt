package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseUser
import com.memolinares.karma_androidpf.R


class FavorsForm (user: FirebaseUser?): Fragment() {
    val user  = user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favors_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.newFav).setOnClickListener(){
            val newFavor = newFavor.newInstance(user)
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container2,  newFavor)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }

    companion object {
        fun newInstance(user: FirebaseUser?): FavorsForm = FavorsForm(user)
    }

}