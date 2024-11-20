package com.example.eventmanagementapp.database.vendor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Vendor::class], version = 1)
abstract class VendorDatabase: RoomDatabase() {

    abstract fun VendorDAO(): VendorDAO
    companion object {
        @Volatile
        private var INSTANCE: VendorDatabase? = null

        fun getInstance(context: Context): VendorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VendorDatabase::class.java,
                    "vendorDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}