package com.example.convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.service.model.GuestModel

//room + kotlin precisa de plugin kotrlin-kapt no graddle


@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase(){

    abstract fun guestDAO():GuestDAO

    companion object{
        private lateinit var INSTANCE: GuestDatabase

        fun getDatabase(context: Context): GuestDatabase{
            if (!::INSTANCE.isInitialized){
                synchronized(GuestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestDB")
                        .allowMainThreadQueries()//coloca em outra thread
                        .build()
                }

            }
            return INSTANCE

        }
  }
}