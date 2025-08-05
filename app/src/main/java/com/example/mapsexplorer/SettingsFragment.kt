package com.example.mapsexplorer

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    private lateinit var switchDarkMode: Switch

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Reference to the dark mode toggle switch
        switchDarkMode = view.findViewById(R.id.switch_dark_mode)

        // Load saved dark mode preference
        val sharedPrefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val darkModeOn = sharedPrefs.getBoolean("dark_mode", false)

        // Set switch position based on saved value
        switchDarkMode.isChecked = darkModeOn

        // Listener for switch toggle
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Apply selected theme
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            // Save setting
            sharedPrefs.edit().putBoolean("dark_mode", isChecked).apply()
        }

        return view
    }
}

