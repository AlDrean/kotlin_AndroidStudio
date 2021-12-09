package com.example.convidados.service.repository

import android.content.Context
import com.example.convidados.service.model.GuestModel

class GuestRepository private constructor(context: Context){
    /**Padrão Singleton : 1 unica instância de conexão
      -> private constructor

    **/
    private var mGuestDataBaseHelper : GuestDataBaseHelper = GuestDataBaseHelper(context)



    companion object{
        private lateinit var repository:GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if (!::repository.isInitialized) {
                repository= GuestRepository(context)
            }
            return repository
        }
    }


    fun save(guest: GuestModel){
//        val db = GuestDataBaseHelper(context)
//        db.writableDatabase.execSQL("") -> não segue padrões de escalabilidade
    }



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

    fun update(){

    }

    fun remove(){

    }

}