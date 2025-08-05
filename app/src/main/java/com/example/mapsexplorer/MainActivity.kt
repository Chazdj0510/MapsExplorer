package com.example.mapsexplorer


import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.fragment.app.Fragment
import com.example.mapsexplorer.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        // Load saved dark mode preference and apply theme before view creation
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val darkMode = prefs.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)

        // Inflate view using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment to MapFragment
        switchFragment(MapFragment())

        // Setup Toolbar
        toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        // Set initial title
        supportActionBar?.title = "Map"

        // Handle bottom navigation item selection
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_map -> {
                    switchFragment(MapFragment())
                    supportActionBar?.title = "Map"
                }
                R.id.nav_list -> {
                    switchFragment(ListFragment())
                    supportActionBar?.title = "List"
                }
                R.id.nav_settings -> {
                    switchFragment(SettingsFragment())
                    supportActionBar?.title = "Settings"
                }
            }
            true
        }
    }

    // Replaces current fragment with the selected one
    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    // Handle toolbar menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                // Navigate to SettingsFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, SettingsFragment())
                    .addToBackStack(null)
                    .commit()

                // Update toolbar title accordingly
                supportActionBar?.title = "Settings"
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

