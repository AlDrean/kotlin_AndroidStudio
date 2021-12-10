package com.example.convidados.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//entidadaes para criar as tabelas do room
@Entity(tableName = "Guest")
//class GuestModel(val id: Int = 0, val name:String, var presence:Boolean)
class GuestModel{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0

    @ColumnInfo(name="name")
    var name:String = ""

    @ColumnInfo(name="presence")
    var presence:Boolean = true



}
