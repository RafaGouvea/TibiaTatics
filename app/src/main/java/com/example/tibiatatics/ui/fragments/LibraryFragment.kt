package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.tibiatatics.R


class LibraryFragment : Fragment() {

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val navigateButton = view.findViewById<View>(R.id.imageView5)
        navigateButton.setOnClickListener {
            findNavController().navigate(R.id.action_menu_library_to_knightCalcFragment2)
        }

        return view
    }

}