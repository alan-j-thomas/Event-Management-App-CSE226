package com.example.eventmanagementapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagementapp.database.venue.Venue
import com.example.eventmanagementapp.database.venue.VenueDAO
import com.example.eventmanagementapp.database.venue.VenueDatabase
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVenue : AppCompatActivity() {

    private lateinit var venueDao: VenueDAO
    private lateinit var etPlaceName: EditText
    private lateinit var etLocation: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnAddEvent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_venue)

        venueDao = VenueDatabase.getDatabase(this).venueDAO()

        // Initialize the views
        etPlaceName = findViewById(R.id.etPlaceName)
        etLocation = findViewById(R.id.etLocation)
        etDescription = findViewById(R.id.etDescription)
        btnAddEvent = findViewById(R.id.btnAddEvent)

        // Add venue functionality
        btnAddEvent.setOnClickListener {
            val name = etPlaceName.text.toString()
            val location = etLocation.text.toString()
            val description = etDescription.text.toString()

            if (name.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()) {
                val venue = Venue(venue = name, location = location, description = description)

                insertVenue(venue)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertVenue(venue: Venue) {


        GlobalScope.launch(Dispatchers.IO) {
            venueDao.insertVenue(venue)

            // After inserting, go back to the VenueActivity
            withContext(Dispatchers.Main) {
                Toast.makeText(this@AddVenue, "Venue Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}