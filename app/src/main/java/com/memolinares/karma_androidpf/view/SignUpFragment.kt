package com.memolinares.karma_androidpf.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.viewModel.LoginViewModel

class SignUpFragment : Fragment() {

    lateinit var navController: NavController
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioRoll)

        view.findViewById<Button>(R.id.signup).setOnClickListener {

            var pass = requireView().findViewById<EditText>(R.id.pass).text.toString()
            var email = requireView().findViewById<EditText>(R.id.email).text.toString()
            var nombre = requireView().findViewById<EditText>(R.id.names).text.toString()
            var id: Int = radioGroup.checkedRadioButtonId

            if (id!=1 && pass.trim().isNotEmpty() && email.trim().isNotEmpty()) {
                val radio: RadioButton = view.findViewById(id)
                loginViewModel.signUp(nombre, email, pass, radio.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "isSuccessful", Toast.LENGTH_LONG).show()
                        navController.navigate(R.id.action_signUpFragment_to_initFragment)
                    } else {
                        Toast.makeText(context, "Error: "+ task.exception+email, Toast.LENGTH_LONG).show()
                    }
                }
                //Toast.makeText(context, email, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Input Required", Toast.LENGTH_LONG).show()
            }
        }

    }


}