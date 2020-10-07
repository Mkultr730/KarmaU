package com.memolinares.karma_androidpf.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.viewModel.LoginViewModel

class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var navController: NavController
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_home2)
            var pass = requireView().findViewById<EditText>(R.id.pass).text.toString()
            var email = requireView().findViewById<EditText>(R.id.email).text.toString()

            if (pass.trim().isNotEmpty() || email.trim().isNotEmpty()) {
                loginViewModel.signIn(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(context, "isSuccessful"+user, Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_signInFragment_to_home2)
                    } else {
                        Toast.makeText(context, "Error: "+task.exception, Toast.LENGTH_SHORT).show()
                    }
                }
                //Toast.makeText(context, email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Input Required", Toast.LENGTH_LONG).show()
            }
        }
    }


}