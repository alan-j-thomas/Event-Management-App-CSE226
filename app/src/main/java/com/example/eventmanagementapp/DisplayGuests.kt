package com.example.eventmanagementapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagementapp.database.guests.Guest
import com.example.eventmanagementapp.database.guests.GuestAdapter
import com.example.eventmanagementapp.database.guests.GuestDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
class DisplayGuests : AppCompatActivity(), OnGuestLongClickListener {
    private lateinit var database: GuestDatabase
    private val channelId = "guest_notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_guests)

        database = GuestDatabase.getInstance(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe and set up RecyclerView adapter
        database.GuestDAO().getAllGuests().observe(this) { guestList ->
            val adapter = GuestAdapter(guestList, this)
            recyclerView.adapter = adapter
        }

        // Create notification channel
        createNotificationChannel()
    }

    override fun onGuestLongClick(guest: Guest) {
        val dialogView = layoutInflater.inflate(R.layout.guest_edit_dialog, null)
        val editName = dialogView.findViewById<EditText>(R.id.editName)
        val editEmail = dialogView.findViewById<EditText>(R.id.editEmail)
        val editMenu = dialogView.findViewById<EditText>(R.id.editMenu)
        val editVenue = dialogView.findViewById<EditText>(R.id.editVenue)
        val editEvent = dialogView.findViewById<EditText>(R.id.editEvent)

        // Populate fields with current data
        editName.setText(guest.name)
        editEmail.setText(guest.email)
        editMenu.setText(guest.menu)
        editVenue.setText(guest.venue)
        editEvent.setText(guest.type)

        AlertDialog.Builder(this)
            .setTitle("Edit Guest")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                // Update guest details
                guest.name = editName.text.toString()
                guest.email = editEmail.text.toString()
                guest.menu = editMenu.text.toString()
                guest.venue = editVenue.text.toString()
                guest.type = editEvent.text.toString()

                // Update database
                updateGuestInDatabase(guest)

                // Display notification
                showNotification("Guest Updated", "${guest.name}'s details have been updated.")
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateGuestInDatabase(guest: Guest) {
        GlobalScope.launch {
            database.GuestDAO().update(guest)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Guest Notifications"
            val descriptionText = "Notifications for guest updates"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(title: String, message: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.notifications) // Replace with your valid icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }
}