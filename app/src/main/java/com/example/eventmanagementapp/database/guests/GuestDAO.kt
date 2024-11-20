package com.example.eventmanagementapp.database.guests

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GuestDAO {

    @Insert
    suspend fun insert(guest: Guest)

    @Update
    suspend fun update(guest: Guest)

    @Delete
    suspend fun delete(guest: Guest)

    @Query("SELECT * FROM Guest")
    fun getAllGuests(): LiveData<List<Guest>>
}

