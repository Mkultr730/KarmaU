package com.memolinares.karma_androidpf.view.homeOptions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.memolinares.karma_androidpf.R
import kotlinx.android.synthetic.main.fragment_favors.view.*

class favors (user: FirebaseUser?) : Fragment() {
    private var adapter = Adapter(ArrayList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // requireView gets the root view for the fragment's layout
        // (the one returned by onCreateView).
        requireView().favors_recycler.adapter = adapter
        requireView().favors_recycler.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {
        fun newInstance(auth: FirebaseUser?): favors = favors(auth)
    }

}