package com.example.eventmanagementapp.database.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    val name: String,
    val date: String,
    val time: String,
    val budget: Long
)
