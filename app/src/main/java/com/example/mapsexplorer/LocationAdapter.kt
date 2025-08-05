package com.example.mapsexplorer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mapsexplorer.databinding.ItemLocationBinding

class LocationAdapter(private val items: List<LocationItem>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    // ViewHolder holds references to item views for each data entry
    inner class ViewHolder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root)

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Binds data to the views in each item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.textName.text = item.name
        holder.binding.textAddress.text = item.address
    }

    // Returns total number of items in the list
    override fun getItemCount(): Int = items.size
}
