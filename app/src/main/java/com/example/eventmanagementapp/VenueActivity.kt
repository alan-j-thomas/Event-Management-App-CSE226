package com.example.eventmanagementapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagementapp.database.venue.VenueAdapter
import com.example.eventmanagementapp.database.venue.VenueDAO
import com.example.eventmanagementapp.database.venue.VenueDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VenueActivity : AppCompatActivity() {

    private lateinit var venueDao: VenueDAO
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var rvVenues: RecyclerView
    private lateinit var addVenue: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue)

        // Get the DAO instance
        venueDao = VenueDatabase.getDatabase(this).venueDAO()

        // Initialize RecyclerView
        rvVenues = findViewById(R.id.rvVenues)
        rvVenues.layoutManager = LinearLayoutManager(this)

        // Example in VenueActivity
        addVenue = findViewById(R.id.addVenue)
        addVenue.setOnClickListener {
            val intent = Intent(this, AddVenue::class.java)
            startActivity(intent)
        }



        venueAdapter = VenueAdapter(emptyList()) { venue ->

            val intent = Intent(this, ShowVenue::class.java)
            intent.putExtra("location", venue.location)
            startActivity(intent)
        }
        rvVenues.adapter = venueAdapter

        // Load all venues from Room database
        loadAllVenues()
    }

    private fun loadAllVenues() {
        // Use coroutine to load data in the background
        GlobalScope.launch(Dispatchers.IO) {
            val venues = venueDao.getAllVenues()

            // Switch to main thread to update UI
            withContext(Dispatchers.Main) {
                venueAdapter = VenueAdapter(venues) { venue ->

                    val intent = Intent(this@VenueActivity, ShowVenue::class.java)
                    intent.putExtra("location", venue.location)
                    startActivity(intent)
                }
                rvVenues.adapter = venueAdapter
            }
        }
    }
}