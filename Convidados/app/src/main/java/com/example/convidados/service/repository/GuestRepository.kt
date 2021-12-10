package com.example.convidados.service.repository

import android.content.Context
import com.example.convidados.service.model.GuestModel

class GuestRepository (context: Context) {


    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()

    fun getGuest(id: Int): GuestModel? {
        return mDatabase.getGUEST(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun getALL(): List<GuestModel> {
        return mDatabase.getALL()

    }

    fun getPresent(): List<GuestModel> {
        return mDatabase.getPRESENTS()
    }

    fun getAbsent(): List<GuestModel> {
        return mDatabase.getABSENT()
    }


    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 0
    }

    fun remove(guest: GuestModel) {
        mDatabase.remove(guest)
    }

}