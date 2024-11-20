package com.example.eventmanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.eventmanagementapp.database.event.EventDatabase
import com.example.eventmanagementapp.database.event.ListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventsActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    lateinit var floatingButton: FloatingActionButton
    private lateinit var database: EventDatabase
    lateinit var listView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        toolbar = findViewById(R.id.actionBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {   // to navigate back to the previous activity
            finish()
        }
        toolbar.title = "Events"

        floatingButton = findViewById(R.id.fab)

        floatingButton.setOnClickListener {
            Intent(this, AddEvent::class.java).also {
                startActivity(it)
            }
        }

        listView = findViewById(R.id.listView)



        database = EventDatabase.getInstance(this)

        database.EventDAO().getAllEvents().observe(this) { eventList ->

            val adapter = ListAdapter(this@EventsActivity, R.layout.list_item, eventList, database)
            listView.adapter = adapter
            adapter.notifyDataSetChanged()

        }



    }
}