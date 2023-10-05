package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tatics.tibiatatics.R


class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val navigateToKnightCalc = view.findViewById<View>(R.id.ek_calc)
        navigateToKnightCalc.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_library
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_library_to_knightCalcFragment)
            } else {
                navController.navigate(R.id.menu_library)
            }
        }

        val navigateToPaladinCalc = view.findViewById<View>(R.id.rp_calc)
        navigateToPaladinCalc.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_library
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_library_to_paladinCalcFragment)
            } else {
                navController.navigate(R.id.menu_library)
            }
        }

        val navigateToDruidCalc = view.findViewById<View>(R.id.ed_calc)
        navigateToDruidCalc.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_library
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_library_to_druidCalcFragment)
            } else {
                navController.navigate(R.id.menu_library)
            }
        }

        val navigateToSorcererCalc = view.findViewById<View>(R.id.ms_calc)
        navigateToSorcererCalc.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_library
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_library_to_sorcererCalcFragment)
            } else {
                navController.navigate(R.id.menu_library)
            }
        }
        return view
    }
}
