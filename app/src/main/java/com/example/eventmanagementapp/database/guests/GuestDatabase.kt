package com.example.eventmanagementapp.database.guests

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Guest::class], version = 1)
abstract class GuestDatabase : RoomDatabase(){

    abstract fun GuestDAO(): GuestDAO

    companion object {
        @Volatile
        private var INSTANCE: GuestDatabase? = null

        fun getInstance(context: Context): GuestDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuestDatabase::class.java,
                    "GuestDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
