package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import com.memolinares.karma_androidpf.viewModel.FavorViewModel
import org.w3c.dom.Text

class newFavor (user: FirebaseUser?): Fragment() {
    val user  = user
    val favorViewModel: FavorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_favor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.deliver_place)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)


        view.findViewById<Button>(R.id.aceptar).setOnClickListener(){
            val deliver_place: String = spinner.getSelectedItem().toString()
            val user_client: String = this.user?.uid.toString()
            val details: String = view.findViewById<EditText>(R.id.details).text.toString()
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1 && deliver_place.trim() != "" && user_client.trim() != "" && details.trim() != ""){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = view.findViewById(id)
                Toast.makeText(context,"On button click : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                val type: String = radio.text.toString()


                favorViewModel.askFavor(Favor(deliver_place, user_client, "", "Inicial", type, details))

                val favorsForm = FavorsForm.newInstance(user)
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container2,  favorsForm)
                transaction?.addToBackStack(null)
                transaction?.commit()

            }else{
                // If no radio button checked in this radio group
                Toast.makeText(context,"On button click : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
        }



        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.deliver_places,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

    }

    companion object {
        fun newInstance(user: FirebaseUser?): newFavor = newFavor(user)
    }

}