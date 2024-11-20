package com.example.eventmanagementapp.database.venue

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VenueDAO {

    @Insert
    suspend fun insertVenue(venue: Venue)

    @Query("SELECT * FROM venue")
    suspend fun getAllVenues(): List<Venue>

    @Delete
    suspend fun deleteVenue(venue: Venue)
}