package com.example.eventmanagementapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.eventmanagementapp.database.guests.Guest
import com.example.eventmanagementapp.database.guests.GuestDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GuestsActivity : AppCompatActivity(){

    private lateinit var toolbar: Toolbar
    lateinit var selectedVenue: String
    lateinit var selectedEvent: String

    lateinit var database: GuestDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guests)

        toolbar = findViewById(R.id.actionBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {   // to navigate back to the previous activity
            finish()
        }

        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val venue = arrayOf("Outdoor", "Indoor")
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, venue)
        spinner1.adapter = adapter


        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        val event = arrayOf("Birthday", "Party", "Marriage", "Conference", "Seminar", "Charity Event")
        val adapter2 = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, event)
        spinner2.adapter = adapter2

        spinner1?.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedVenue = venue[p2]
                //Toast.makeText(this@GuestsActivity, "Item is $selectedVenue", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        spinner2?.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedEvent = event[p2]
                //Toast.makeText(this@GuestsActivity, "Item is $selectedEvent", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        //initialize database
        database = Room.databaseBuilder(applicationContext,
            GuestDatabase::class.java, "GuestDB").fallbackToDestructiveMigration().build()

        //set up views
        val btnAdd = findViewById<Button>(R.id.btnAddGuest)
        btnAdd.setOnClickListener {

            val id = findViewById<EditText>(R.id.etID).text.toString().toInt()
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val menu = findViewById<EditText>(R.id.etMenu).text.toString()
            val venue = selectedVenue
            val event = selectedEvent

            // Perform the insert in a coroutine (background thread)
            GlobalScope.launch {
                database.GuestDAO().insert(Guest(id, name, email, menu, venue, event))
                //Toast.makeText(this@GuestsActivity, "Guest Added Successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        val display = findViewById<Button>(R.id.btnDisplayGuests)
        display.setOnClickListener {
            Intent(this, DisplayGuests::class.java).also {
                startActivity(it)
            }
        }

    }


}