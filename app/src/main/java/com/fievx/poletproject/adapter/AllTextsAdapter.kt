package com.fievx.poletproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fievx.poletproject.R

class AllTextsAdapter : RecyclerView.Adapter<AllTextsAdapter.AllTextViewHolder>() {

    val list = List(1, init = {i -> i})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTextViewHolder {
        return AllTextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple_text, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllTextViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemViewType(position: Int): Int {
        return list[position]
    }

    inner class AllTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(){
            itemView.findViewById<TextView>(R.id.tv).text = "Item ${list[adapterPosition]}"
        }
    }
}

