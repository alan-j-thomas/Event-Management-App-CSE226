package com.example.eventmanagementapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChecklistActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddTask: FloatingActionButton
    private val listAdapter = listAdapter(mutableListOf())
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        toolbar = findViewById(R.id.actionBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {   // to navigate back to the previous activity
            finish()
        }

        recyclerView = findViewById(R.id.checklistRecyclerView)
        btnAddTask = findViewById(R.id.btnAddTask)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter

        btnAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT
            hint = "Enter task name"
        }

        AlertDialog.Builder(this)
            .setTitle("Add New Task")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val taskName = input.text.toString()
                if (taskName.isNotBlank()) {
                    listAdapter.addTask(taskName)
                } else {
                    Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}

