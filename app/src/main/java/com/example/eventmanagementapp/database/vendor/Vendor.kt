package com.example.eventmanagementapp.database.vendor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendor")
data class Vendor(
    val name: String,
    @PrimaryKey
    val phone: String,
    val email: String,
    val address: String,
    val amount: String,
    val category: String
)
