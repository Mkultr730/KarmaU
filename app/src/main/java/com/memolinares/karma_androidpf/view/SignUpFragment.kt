package com.memolinares.karma_androidpf.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.memolinares.karma_androidpf.R

class SignUpFragment : Fragment() {

    lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        view.findViewById<Button>(R.id.signup).setOnClickListener {

            var pass = requireView().findViewById<EditText>(R.id.pass).text.toString()
            var email = requireView().findViewById<EditText>(R.id.email).text.toString()

            if (pass.trim().isNotEmpty() || email.trim().isNotEmpty()) {
                createUser(email, pass)
                Toast.makeText(context, email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Input Required", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun createUser(email:String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "isSuccessful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error: "+ task.exception, Toast.LENGTH_LONG).show()
                }
            }
    }

}