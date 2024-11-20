package com.example.eventmanagementapp.database.vendor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VendorDAO {

    @Insert
    suspend fun insertVendor(vendor: Vendor)

    @Update
    suspend fun updateVendor(vendor: Vendor)

    @Delete
    suspend fun deleteVendor(vendor: Vendor)

    @Query("SELECT * FROM vendor")
    fun getAllVendors(): LiveData<List<Vendor>>

}