package com.example.mapsexplorer

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapsexplorer.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout using ViewBinding
        binding = FragmentListBinding.inflate(inflater, container, false)

        // Set up RecyclerView with a linear layout and the custom adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = LocationAdapter(LocationStore.locations)
        return binding.root
    }
}
