# Maps Explorer Android App

## Overview

**Maps Explorer** is an Android application built to fulfill a Material Design-focused assignment. It integrates Google Maps with interactive UI elements, allowing users to explore, add, and manage locations visually on a map and in a list. The app uses key Material Design components to provide a modern and intuitive user experience.

---

## Features

- **Google Maps Fragment:** Displays a map centered on a default location with several landmark markers (e.g., Empire State Building, Statue of Liberty).
- **Add Location Floating Action Button:** Users can search for locations by name or address using Google Places Autocomplete and add them as markers on the map and items in the list.
- **Bottom Navigation View:** Allows switching between three main screens:
  - **Map View:** Shows the Google Map with location markers.
  - **List View:** Displays added locations in a scrollable CardView-based list.
  - **Settings:** Provides preferences such as toggling Dark Mode.
- **Toolbar:** Displays app title and menu, including a link to the Settings screen.
- **Theme Support:** Light (baby blue) and Dark (dark blue) mode themes with appropriate color adjustments for text and UI elements.

---

## Material Design Elements Used

- **Toolbar** at the top for app title and menu options.
- **Floating Action Button (FAB)** to add new locations interactively.
- **RecyclerView / CardView** for an elegant scrollable list of locations.
- **Bottom Navigation View** for smooth navigation across main sections.
- **Google Maps Fragment** to embed interactive maps with markers.

---

## Design Decisions & Challenges

- **Google Places Autocomplete** was used for intuitive location search and address-to-coordinate conversion, improving UX over manual input.
- Theme colors were customized to match the desired baby blue and dark blue palettes, requiring careful color resource management and night mode support.
- Managing state and dynamic UI updates when locations are added, ensuring markers appear both on the map and in the list without data inconsistency.
- Handling the Floating Action Button’s positioning to avoid obstructing map controls such as zoom buttons.
- Ensuring toolbar titles and menu options update contextually when navigating between fragments.
- Implementing persistent dark mode setting with SharedPreferences, applied at app launch and dynamically during runtime.

---

## How to Run

1. Clone this repository:
```bash
   git clone https://github.com/Chazdj0510/MapsExplorer.git
```
2. Open in Android Studio.
3. Obtain a Google Maps API key and add it to `local.properties` or `strings.xml` as `google_maps_key`.
4. Build and run the app on an Android device or emulator with Google Play Services.
5. Use the FAB to search for and add locations.
6. Toggle dark mode in the Settings tab.

---

## Future Enhancements

- Add ability to edit or delete locations.
- Save location data persistently (e.g., local database or cloud).
- Provide routing between markers.
- Add user authentication for personalized experience.

---

## Video Demo

*A video demonstrating the app's functionality is available in the repository's root folder or via this [link](#) (to be added).*

---

## Contact

Developed by Chastidy Joanem  
GitHub: [https://github.com/Chazdj0510](https://github.com/Chazdj0510)
