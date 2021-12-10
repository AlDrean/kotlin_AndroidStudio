package com.example.convidados.service.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class GuestDatabase : RoomDatabase(){
    companion object{

        private lateinit var INSTANCE: GuestDatabase

        fun guestDatabase(context: Context): GuestDatabase{
            if (!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java,"guestDB")
                    .allowMainThreadQueries()//coloca em outra thread
                    .build()

            }
            return INSTANCE

        }
    }
}