package com.tokopedia.filter.view.recyclerview

import android.app.ProgressDialog.show
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tokopedia.filter.R

class LocationAdapter : RecyclerView.Adapter<LocationViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.chip_viewholder, parent, false)

        return LocationViewHolder(view)
    }


    override fun getItemCount(): Int =
            differ.currentList.size


    val lokasiArray: ArrayList<String> = ArrayList()

    // TODO Fix the Location Filter button
    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = differ.currentList[position]
        holder.itemView.apply {
            holder.chipLocation.text = location
            holder.chipLocation.setOnCheckedChangeListener { compoundButton, b ->
                if (compoundButton.isChecked) {
                    lokasiArray.add(location)
                    Toast.makeText(context, "Clicked on $location", Toast.LENGTH_SHORT).show()
                    Log.d("lokasi array", lokasiArray.toString())
                } else {
                    lokasiArray.remove(location)
                    Toast.makeText(context, "Unclicked on $location", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}