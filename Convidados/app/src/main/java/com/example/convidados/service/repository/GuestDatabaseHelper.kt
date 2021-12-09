package com.example.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.service.constants.DataBaseConstants

class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    /**
     * Método executado somente uma vez quando o acesso ao banco de dados é feito pela primeira vez
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    /**
     * Alteraçaõ em função da comparação da função VERSION
    * */
    }

    companion object {
        private const val VERSION = 1
        private const val NAME = "Convidados.db"


        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

}