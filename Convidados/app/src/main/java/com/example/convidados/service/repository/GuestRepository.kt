package com.example.convidados.service.repository

import com.example.convidados.service.model.GuestModel

class GuestRepository {
    fun getALL():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }
    fun getPresent():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }
    fun getAbsent():List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel){

    }
    fun update(){

    }

    fun remove(){

    }

}