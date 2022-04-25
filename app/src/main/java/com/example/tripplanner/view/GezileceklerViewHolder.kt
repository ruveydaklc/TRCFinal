package com.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripplanner.R
import com.example.tripplanner.model.GezilecekEntity

class GezileceklerViewHolder(itemView: View, glist:ArrayList<GezilecekEntity>, itemClick:(position:Int)->Unit):RecyclerView.ViewHolder(itemView) {

    var tvYerAdı:TextView
    var tvYerKisaTanim:TextView
    var tvYerKisaAciklama:TextView
    var ivYerFotograf:ImageView
    var ivOncelik:ImageView

    init {
        tvYerAdı=itemView.findViewById(R.id.tvYerAdiGC)
        tvYerKisaTanim=itemView.findViewById(R.id.tvYerKisaTanimGC)
        tvYerKisaAciklama=itemView.findViewById(R.id.tvYerKisaAciklemaGC)
        ivYerFotograf=itemView.findViewById(R.id.ivYerFotografGC)
        ivOncelik=itemView.findViewById(R.id.ivOncelikGC)

        itemView.setOnClickListener { itemClick(adapterPosition) }
    }




    fun bindData(context:Context,item:GezilecekEntity){

        tvYerAdı.text=item.Adi
        tvYerKisaTanim.text=item.Tanim
        tvYerKisaAciklama.text=item.Aciklama

        //TODO setting image to ivYerFotograf

        //!!
        if (item.Oncelik=="Oncelik1")//yeşil
        { ivOncelik.setBackgroundColor(R.drawable.oncelik1_sekil) }
        else if (item.Oncelik=="Oncelik2")//mavi
        { ivOncelik.setBackgroundColor(R.drawable.oncelik2_sekil) }
        else if (item.Oncelik=="Oncelik3")//gri
        { ivOncelik.setBackgroundColor(R.drawable.oncelik3_sekil) }





    }
}
