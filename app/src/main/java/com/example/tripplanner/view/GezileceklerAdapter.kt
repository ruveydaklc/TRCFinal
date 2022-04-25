package com.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tripplanner.R
import com.example.tripplanner.model.GezilecekEntity

class GezileceklerAdapter(val context: Context, var gezilecekListe:ArrayList<GezilecekEntity>, val itemClick:(position:Int)->Unit):RecyclerView.Adapter<GezileceklerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GezileceklerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.gezilecekler_card,parent,false)
        return GezileceklerViewHolder(view,gezilecekListe,itemClick)
    }

    override fun onBindViewHolder(holder: GezileceklerViewHolder, position: Int) {
        holder.bindData(context,gezilecekListe.get(position))
    }

    override fun getItemCount(): Int {
        return gezilecekListe.size
    }


}