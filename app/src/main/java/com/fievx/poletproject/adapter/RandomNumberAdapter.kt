package com.fievx.poletproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fievx.poletproject.R
import java.util.*

class RandomNumberAdapter: RecyclerView.Adapter<RandomNumberViewHolder>() {

    val list = List(50, init = {Random().nextInt(10)})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomNumberViewHolder {
        return RandomNumberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple_text, parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RandomNumberViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv).text = "Value = ${list[holder.adapterPosition]}"

    }
}

class RandomNumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)