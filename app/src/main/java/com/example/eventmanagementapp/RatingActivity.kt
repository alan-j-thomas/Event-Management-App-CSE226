package com.example.eventmanagementapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        val question1Spinner: Spinner = findViewById(R.id.question1Spinner)
        val question2Spinner: Spinner = findViewById(R.id.question2Spinner)
        val question3Spinner: Spinner = findViewById(R.id.question3Spinner)
        val question4Spinner: Spinner = findViewById(R.id.question4Spinner)


        setSpinner(question1Spinner)
        setSpinner(question2Spinner)
        setSpinner(question3Spinner)
        setSpinner(question4Spinner)
    }

    private fun setSpinner(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                val selectedOption = parent.getItemAtPosition(position).toString()
                when(selectedOption) {
                    "Excellent" -> {
                        Toast.makeText(this@RatingActivity, "We highly appreciate your feedback!", Toast.LENGTH_SHORT).show()
                    }
                    "Good" -> {
                        Toast.makeText(this@RatingActivity, "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
                    }
                    "Average" -> {
                        Toast.makeText(this@RatingActivity, "Thank you for your feedback! We will try to improve it", Toast.LENGTH_SHORT).show()
                    }
                    "Poor" -> {
                        Toast.makeText(this@RatingActivity, "Inconvenience is highly regretted! We'll look into the problems", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this@RatingActivity,"Please select an option",Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }
}