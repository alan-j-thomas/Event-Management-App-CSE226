package com.example.eventmanagementapp.database.event

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.eventmanagementapp.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListAdapter(var ctx: Context, var resources: Int, var items: List<Event>, val database: EventDatabase)
    : ArrayAdapter<Event>(ctx, resources, items){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resources, null)
        val name: TextView = view.findViewById(R.id.tvName)
        val date: TextView = view.findViewById(R.id.tvDate)
        val time: TextView = view.findViewById(R.id.tvTime)
        val budget: TextView = view.findViewById(R.id.tvBudget)
        val deleteBtn: ImageView = view.findViewById(R.id.deleteBtn)
        val mitem: Event = items[position]

        name.text = "Event Name: ${mitem.name}"
        date.text = "Date: ${mitem.date}"
        time.text = "Time: ${mitem.time}"
        budget.text = "Budget: ${mitem.budget.toString()}"

        //Deleting the record
        deleteBtn.setOnClickListener {

            val newName = name.text.toString().substringAfter("Event Name: ").trim()
            val newDate = date.text.toString().substringAfter("Date: ").trim()
            val newTime = time.text.toString().substringAfter("Time: ").trim()
            val newBudget = budget.text.toString().substringAfter("Budget: ").trim().toLong()

            GlobalScope.launch {
                database.EventDAO().deleteEvent(Event(newName, newDate, newTime, newBudget))
            }
        }
        return view
    }


}