package com.example.eventmanagementapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.eventmanagementapp.database.vendor.Vendor
import com.example.eventmanagementapp.database.vendor.VendorDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddVendors : AppCompatActivity() {
    lateinit var selectedCategory: String
    lateinit var database: VendorDatabase
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vendors)

        toolbar = findViewById(R.id.actionBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {   // to navigate back to the previous activity
            finish()
        }
        toolbar.title = "Add Vendor"

        val spinner1 = findViewById<Spinner>(R.id.spinnerVendorType)
        val category = arrayOf("Attire & Accessories", "Health & Beauty", "Music & Show", "Flowers & Decor", "Photo & Video", "Transportation")
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, category)
        spinner1.adapter = adapter

        spinner1?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedCategory = category[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        database = Room.databaseBuilder(applicationContext,
            VendorDatabase::class.java, "vendorDB").build()

        //set up views
        val btnAdd = findViewById<Button>(R.id.btnAddVendor)
        btnAdd.setOnClickListener {

            val name = findViewById<EditText>(R.id.etVendorName).text.toString()
            val phone = findViewById<EditText>(R.id.etPhone).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val address = findViewById<EditText>(R.id.etAddress).text.toString()
            val budget = findViewById<EditText>(R.id.etAmount).text.toString()
            val type = selectedCategory


            // Perform the insert in a coroutine (background thread)
            GlobalScope.launch {
                database.VendorDAO().insertVendor(Vendor(name, phone, email, address, budget, type))
                //Toast.makeText(this@GuestsActivity, "Guest Added Successfully!", Toast.LENGTH_SHORT).show()
            }
        }



    }
}