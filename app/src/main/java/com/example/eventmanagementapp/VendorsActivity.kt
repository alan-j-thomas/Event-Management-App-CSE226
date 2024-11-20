package com.example.eventmanagementapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.eventmanagementapp.database.vendor.VendorAdapter
import com.example.eventmanagementapp.database.vendor.VendorDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VendorsActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    lateinit var floatingButton: FloatingActionButton
    private lateinit var database: VendorDatabase
    lateinit var listView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendors)

        toolbar = findViewById(R.id.actionBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {   // to navigate back to the previous activity
            finish()
        }
        toolbar.title = "Vendors"

        floatingButton = findViewById(R.id.fab)

        floatingButton.setOnClickListener {
            Intent(this, AddVendors::class.java).also {
                startActivity(it)
            }
        }

        listView = findViewById(R.id.listView)



        database = VendorDatabase.getInstance(this)

        database.VendorDAO().getAllVendors().observe(this) { vendorList ->

            val adapter = VendorAdapter(this@VendorsActivity, R.layout.list_item_vendor, vendorList, database)
            listView.adapter = adapter
            adapter.notifyDataSetChanged()

        }

    }
}