package com.example.convidados.service.constants

class DataBaseConstants private constructor(){//o que é um private constructor?
    object GUEST{
        const val TABLE_NAME = "Guest"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}