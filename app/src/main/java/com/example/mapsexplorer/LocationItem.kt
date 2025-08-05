package com.example.mapsexplorer

import com.google.android.gms.maps.model.LatLng

// Data class to hold information about each location
// Includes name (title), address (as entered or returned from Places API), and coordinates
data class LocationItem(val name: String, val address: String, val latLng: LatLng)
