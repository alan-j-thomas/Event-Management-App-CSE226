package com.example.eventmanagementapp.database.guests

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
data class Guest(
    @PrimaryKey
    val id: Int,
    var name: String,
    var email: String,
    var menu: String,
    var venue: String,
    var type: String
)
