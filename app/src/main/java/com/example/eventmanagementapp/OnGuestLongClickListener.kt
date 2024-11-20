package com.example.eventmanagementapp

import com.example.eventmanagementapp.database.guests.Guest

interface OnGuestLongClickListener {
    fun onGuestLongClick(guest: Guest)
}
