package com.example.eventmanagementapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.eventmanagementapp.ChecklistActivity
import com.example.eventmanagementapp.EventsActivity
import com.example.eventmanagementapp.GuestsActivity
import com.example.eventmanagementapp.R
import com.example.eventmanagementapp.RatingActivity
import com.example.eventmanagementapp.VendorsActivity
import com.example.eventmanagementapp.VenueActivity

class FirstFragment : Fragment() {

    private lateinit var cdEvents: CardView
    private lateinit var cdGuests: CardView
    private lateinit var cdVendors: CardView
    private lateinit var cdVenue: CardView
    private lateinit var cdRating: CardView
    private lateinit var cdList: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        cdEvents = view.findViewById(R.id.cdEvents)
        cdGuests = view.findViewById(R.id.cdGuests)
        cdVendors = view.findViewById(R.id.cdVendors)
        cdVenue = view.findViewById(R.id.cdVenue)
        cdRating = view.findViewById(R.id.cdRating)
        cdList = view.findViewById(R.id.cdList)

        cdGuests.setOnClickListener {
            openActivity(GuestsActivity::class.java)
        }

        cdEvents.setOnClickListener{
            openActivity(EventsActivity::class.java)

        }

        cdVendors.setOnClickListener {
            openActivity(VendorsActivity::class.java)
        }

        cdVenue.setOnClickListener {
            openActivity(VenueActivity::class.java)
        }

        cdRating.setOnClickListener {
            openActivity(RatingActivity::class.java)
        }

        cdList.setOnClickListener {
            openActivity(ChecklistActivity::class.java)
        }
        return view
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(activity, activityClass)
        startActivity(intent)
    }


}