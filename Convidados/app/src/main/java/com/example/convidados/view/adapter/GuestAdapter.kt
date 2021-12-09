package com.example.convidados.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.view.viewHolder.GuestViewHolder

class GuestAdapter: RecyclerView.Adapter<GuestViewHolder>() {
    private var mGuestList: List<GuestModel> = arrayListOf()

    companion object{
        var createCount:Int = 0
        var bindCount:Int = 0
    }
    private lateinit  var mlistener :GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        bindCount++
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest,parent,false)
        return GuestViewHolder(item, mlistener)

    }
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        bindCount++
        holder.bind(mGuestList[position])

    }

    override fun getItemCount(): Int {

        return mGuestList.count()
    }

    fun updateGuests(list:List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mlistener = listener
    }

}
