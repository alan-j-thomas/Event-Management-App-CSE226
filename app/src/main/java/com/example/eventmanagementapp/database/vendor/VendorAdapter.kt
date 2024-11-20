package com.example.eventmanagementapp.database.vendor

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

class VendorAdapter(var ctx: Context, var resources: Int, var items: List<Vendor>, val database: VendorDatabase)
    : ArrayAdapter<Vendor>(ctx, resources, items){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resources, null)
        val name: TextView = view.findViewById(R.id.vendorName)
        val phone: TextView = view.findViewById(R.id.vendorPhone)
        val email: TextView = view.findViewById(R.id.vendorEmail)
        val address: TextView = view.findViewById(R.id.vendorAddress)
        val budget: TextView = view.findViewById(R.id.vendorBudget)
        val category: TextView = view.findViewById(R.id.vendorCategory)
        val deleteBtn: ImageView = view.findViewById(R.id.deleteBtn)
        val item: Vendor = items[position]

        name.text = item.name
        phone.text = item.phone
        email.text = item.email
        address.text = item.address
        budget.text = item.amount
        category.text = item.category

        //Deleting the record
        deleteBtn.setOnClickListener {

            val newName = name.text.toString()
            val newPhone = phone.text.toString()
            val newEmail = email.text.toString()
            val newBudget = budget.text.toString()
            val newAddress = address.text.toString()
            val newCategory = category.text.toString()


            GlobalScope.launch {
                database.VendorDAO().deleteVendor(Vendor(newName, newPhone, newEmail, newAddress, newBudget, newCategory))
            }
        }
        return view
    }


}