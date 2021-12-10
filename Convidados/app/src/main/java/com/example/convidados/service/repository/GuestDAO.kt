package com.example.convidados.service.repository

import androidx.room.*
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.service.model.GuestModel

@Dao
interface GuestDAO {
    @Insert
    fun save(guest: GuestModel): Long//id que foi inserido

    @Update
    fun update(guest: GuestModel): Int//numero de linhas afetadas

    @Delete
    fun remove(guest: GuestModel)

    @Query("SELECT * FROM " + DataBaseConstants.GUEST.TABLE_NAME + " WHERE " + DataBaseConstants.GUEST.COLUMNS.ID + " = :id")
    fun getGUEST(id: Int): GuestModel

    @Query("SELECT * FROM " + DataBaseConstants.GUEST.TABLE_NAME)
    fun getALL(): List<GuestModel>

    @Query("SELECT * FROM " + DataBaseConstants.GUEST.TABLE_NAME + " WHERE " + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = 1")
    fun getPRESENTS(): List<GuestModel>

    @Query("SELECT * FROM " + DataBaseConstants.GUEST.TABLE_NAME + " WHERE " + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = 0")
    fun getABSENT(): List<GuestModel>
}