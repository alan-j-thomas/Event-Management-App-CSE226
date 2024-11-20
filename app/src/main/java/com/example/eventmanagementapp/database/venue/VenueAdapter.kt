package com.example.eventmanagementapp.database.venue

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagementapp.R
import com.example.eventmanagementapp.ShowVenue


class VenueAdapter(
    private var venues: List<Venue>,
    private val onClick: (Venue) -> Unit
) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val venue = venues[position]
        holder.bind(venue)
    }

    override fun getItemCount(): Int = venues.size

    fun updateVenues(newVenues: List<Venue>) {
        venues = newVenues
        notifyDataSetChanged()
    }

    inner class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tvVenue)
        private val locationTextView: TextView = itemView.findViewById(R.id.tvLocation)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        private val btnOpen: Button = itemView.findViewById(R.id.btn_open)

        fun bind(venue: Venue) {
            nameTextView.text = venue.venue
            locationTextView.text = venue.location
            val location = nameTextView.text.toString() +" "+ locationTextView.text.toString()
            descriptionTextView.text = venue.description

            btnOpen.setOnClickListener {
                val intent = Intent(itemView.context, ShowVenue::class.java)
                intent.putExtra("location", location)
                itemView.context.startActivity(intent)
            }

            itemView.setOnClickListener { onClick(venue) }
        }
    }
}
