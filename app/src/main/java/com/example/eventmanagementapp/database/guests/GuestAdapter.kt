package com.example.eventmanagementapp.database.guests


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagementapp.OnGuestLongClickListener
import com.example.eventmanagementapp.R

class GuestAdapter(private val guestList: List<Guest>, private val listener: OnGuestLongClickListener)
    : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_recycler_view, parent, false)


        return GuestViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val guest = guestList[position]
        holder.bind(guest)

        holder.itemView.setOnLongClickListener {
            listener.onGuestLongClick(guest)
            true
        }
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val guestId: TextView = itemView.findViewById(R.id.guestId)
        private val guestName: TextView = itemView.findViewById(R.id.guestName)
        private val guestEmail: TextView = itemView.findViewById(R.id.guestEmail)
        private val guestMenu: TextView = itemView.findViewById(R.id.guestMenu)
        private val guestVenue: TextView = itemView.findViewById(R.id.guestVenue)
        private val guestEvent: TextView = itemView.findViewById(R.id.guestEvent)

        @SuppressLint("SetTextI18n")
        fun bind(guest: Guest) {
            guestId.text = "ID: ${guest.id}"
            guestName.text = "Name: ${guest.name}"
            guestEmail.text = "Email: ${guest.email}"
            guestMenu.text = "Menu: ${guest.menu}"
            guestVenue.text = "Venue: ${guest.venue}"
            guestEvent.text = "Event: ${guest.type}"
        }


    }
}