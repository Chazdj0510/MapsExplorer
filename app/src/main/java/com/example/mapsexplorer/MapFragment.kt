package com.example.mapsexplorer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mapsexplorer.databinding.FragmentMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.*
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    private val AUTOCOMPLETE_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        // Initialize Places API (only once, you can do this in Application class)
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), getString(R.string.google_maps_key))
        }

        // Setup the map fragment and get the map asynchronously
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            googleMap = map

            // Enable zoom in/out buttons
            googleMap.uiSettings.isZoomControlsEnabled = true

            // Enable pinch to zoom and gestures
            googleMap.uiSettings.isZoomGesturesEnabled = true

            // Add existing markers
            LocationStore.locations.forEach {
                googleMap.addMarker(MarkerOptions().position(it.latLng).title(it.name))
            }

            // Move camera to default location
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(38.8951, -77.0364), 10f))
        }

        // Launch Places Autocomplete when FAB clicked
        binding.fabAddLocation.setOnClickListener {
            launchAutocomplete()
        }

        return binding.root
    }

    // Starts the Places Autocomplete intent
    private fun launchAutocomplete() {
        // Specify the fields to return
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)

        // Build autocomplete intent
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
            .build(requireContext())

        autocompleteLauncher.launch(intent)
    }

    // Handle the result of autocomplete
    private val autocompleteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val place = Autocomplete.getPlaceFromIntent(result.data!!)
            place.latLng?.let { latLng ->
                // Add marker on map
                googleMap.addMarker(MarkerOptions().position(latLng).title(place.name))
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

                // Add to your location list
                LocationStore.locations.add(LocationItem(place.name ?: "Unnamed", place.address ?: "", latLng))
                Toast.makeText(requireContext(), "Added: ${place.name}", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(requireContext(), "No location found", Toast.LENGTH_SHORT).show()
            }
        } else if (result.resultCode == Activity.RESULT_CANCELED) {
            // The user canceled the operation.
        } else {
            val status = Autocomplete.getStatusFromIntent(result.data!!)
            Toast.makeText(requireContext(), "Error: ${status.statusMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}
