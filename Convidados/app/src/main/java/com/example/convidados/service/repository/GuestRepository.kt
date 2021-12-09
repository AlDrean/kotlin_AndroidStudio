package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.DataBaseConstants
import com.example.convidados.service.model.GuestModel
import java.lang.Exception

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


    fun save(guest: GuestModel):Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME,null,contentValues)
            true

        }catch (e:Exception){
            false

        }

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

    fun update(guest: GuestModel):Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selections = DataBaseConstants.GUEST.COLUMNS.ID+"= ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME,contentValues,selections,args)
            true

        }catch (e:Exception){
            false

        }

    }

    fun remove(guest: Integer):Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val selections = DataBaseConstants.GUEST.COLUMNS.ID+"= ?"
            val args = arrayOf(guest)

            db.delete(DataBaseConstants.GUEST.TABLE_NAME,selections,args)

            true

        }catch (e:Exception){
            false

        }
    }

}