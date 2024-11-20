package com.example.eventmanagementapp.database.venue

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Venue::class], version = 1)
abstract class VenueDatabase : RoomDatabase() {
    abstract fun venueDAO(): VenueDAO

    companion object {
        @Volatile
        private var INSTANCE: VenueDatabase? = null

        fun getDatabase(context: Context): VenueDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VenueDatabase::class.java,
                    "venueDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
