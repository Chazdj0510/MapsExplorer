package com.example.mapsexplorer

// Singleton object to store all added locations
// The locations list is shared across fragments (Map, List)
object LocationStore {
    val locations = mutableListOf<LocationItem>()
}
