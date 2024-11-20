package com.example.eventmanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class listAdapter(private val tasks: MutableList<String>) :
    RecyclerView.Adapter<listAdapter.ChecklistViewHolder>() {

    inner class ChecklistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.check_list, parent, false)
        return ChecklistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        val taskName = tasks[position]
        holder.checkBox.text = taskName
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(holder.itemView.context, "$taskName completed!", Toast.LENGTH_SHORT).show()
            }
        }
        holder.btnRemove.setOnClickListener {
            removeTask(position)
        }
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(taskName: String) {
        tasks.add(taskName)
        notifyItemInserted(tasks.size - 1)
    }

    private fun removeTask(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
}
