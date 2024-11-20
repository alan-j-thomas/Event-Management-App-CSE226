package com.example.eventmanagementapp.database.venue

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue")
data class Venue(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val venue: String,
    val location: String,
    val description: String
)
