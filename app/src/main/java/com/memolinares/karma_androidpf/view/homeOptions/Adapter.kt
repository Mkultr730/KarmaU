package com.memolinares.karma_androidpf.view.homeOptions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memolinares.karma_androidpf.R
import com.memolinares.karma_androidpf.model.Favor
import kotlinx.android.synthetic.main.fragment_list.view.*

class Adapter(val favors: ArrayList<Favor>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favors[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favor: Favor) {
            itemView.title.text = favor.type
            itemView.body.text = "\n Estado ${favor.stage} \n Lugar: ${favor.deliver_place}"
        }
    }
}