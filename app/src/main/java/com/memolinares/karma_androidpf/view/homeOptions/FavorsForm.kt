package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.viewModel.FavorViewModel
import com.memolinares.karma_androidpf.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_favors_form.view.*


class FavorsForm (user: FirebaseUser?, favor: Favor): Fragment() {
    val user  = user
    val fav = favor
    val FavorVM: FavorViewModel by viewModels()

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
        if (fav.user_client != ""){
            view.findViewById<TextView>(R.id.tipoF).text = fav.type
            view.findViewById<TextView>(R.id.stage).text = fav.stage
            view.findViewById<TextView>(R.id.Detalles).text = fav.details
        }
        view.findViewById<Button>(R.id.newFav).setOnClickListener{
            val newFavor = newFavor.newInstance(user)
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container2,  newFavor)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        view.findViewById<Button>(R.id.completo).setOnClickListener{
            FavorVM.setCheckCl(fav.key)
        }
    }

    companion object {
        fun newInstance(user: FirebaseUser?, favor: Favor): FavorsForm = FavorsForm(user, favor)
    }

}