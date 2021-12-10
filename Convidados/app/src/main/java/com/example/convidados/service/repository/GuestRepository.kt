package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {
    /**Padrão Singleton : 1 unica instância de conexão
    -> private constructor

     **/
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)


    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true

        } catch (e: Exception) {
            false

        }

//        val db = GuestDataBaseHelper(context)
//        db.writableDatabase.execSQL("") -> não segue padrões de escalabilidade
    }


    fun getALL(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )


            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    var id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    var name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    var presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

//
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//            )


            val cursor = db.rawQuery("select * from "+ DataBaseConstants.GUEST.TABLE_NAME+" where "+ DataBaseConstants.GUEST.COLUMNS.PRESENCE+" = 1",null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    var id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    var name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    var presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

//
//            val cursor = db.query(
//                DataBaseConstants.GUEST.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//            )


            val cursor = db.rawQuery("select * from "+ DataBaseConstants.GUEST.TABLE_NAME+" where "+ DataBaseConstants.GUEST.COLUMNS.PRESENCE+" = 0",null)
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    var id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    var name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    var presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getGuest(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection =
                arrayOf(
                    DataBaseConstants.GUEST.COLUMNS.NAME,
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE
                )

            val selections = DataBaseConstants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selections,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                var name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                var presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }

            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val selections = DataBaseConstants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selections, args)
            true

        } catch (e: Exception) {
            false

        }

    }

    fun remove(guest: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val selections = DataBaseConstants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selections, args)

            true

        } catch (e: Exception) {
            false

        }
    }

}