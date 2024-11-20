package com.example.eventmanagementapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.eventmanagementapp.database.event.Event
import com.example.eventmanagementapp.database.event.EventDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar

class AddEvent : AppCompatActivity() {
    lateinit var database: EventDatabase
    lateinit var dateButton: ImageView
    lateinit var timeButton: ImageView
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        database = Room.databaseBuilder(applicationContext,
            EventDatabase::class.java, "eventDB").build()

        etTime = findViewById(R.id.etTime)
        etDate = findViewById(R.id.etDate)

        dateButton = findViewById(R.id.dateButton)
        timeButton = findViewById(R.id.timeButton)

        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Show DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Set selected date in EditText
                    etDate.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Show TimePickerDialog
            val timePickerDialog = TimePickerDialog(
                this,
                { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                    // Set selected time in EditText
                    etTime.setText(String.format("%02d:%02d", selectedHour, selectedMinute))
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }




        val btnAddEvent = findViewById<Button>(R.id.btnAddEvent)
        btnAddEvent.setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val date = etDate.text.toString()
            val time = etTime.text.toString()
            val budget = findViewById<EditText>(R.id.etBudget).text.toString().toLong()

            // Perform the insert in a coroutine (background thread)
            GlobalScope.launch {
                database.EventDAO().insertEvent(Event(name, date, time, budget))
            }

            finish()
        }



    }
}