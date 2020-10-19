package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.viewModel.LoginViewModel

class Home : Fragment(), OnFavorClickListener {
    lateinit var navController: NavController
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val user = loginViewModel.getCurrentUser()
        view.findViewById<BottomNavigationView>(R.id.bottombar).setOnNavigationItemSelectedListener{ it ->
            when (it.itemId) {
                R.id.navigation_profile -> {
                    val profileF = Perfil.newInstance(user)
                    open(profileF)
                    true
                }
                R.id.navigation_favors -> {
                    val favors = favors.newInstance(user)
                    open(favors)
                    true
                 }
                R.id.navigation_favorsform -> {
                    val favorsForm = FavorsForm.newInstance(user, Favor("", "", "", "", "", ""))
                    open(favorsForm)
                    true
                }
                else -> false
            }
        }
    }

    fun open(F: Fragment){
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container2,  F)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onItemCLick(favor: Favor, position: Int) {
        Toast.makeText(this.context, "Deliver Place " + favor.deliver_place, Toast.LENGTH_LONG).show()
    }

}