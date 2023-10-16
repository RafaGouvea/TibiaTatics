package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tatics.tibiatatics.R


class LibraryFragment : Fragment() {

    private lateinit var navigateToKnightCalc: View
    private lateinit var navigateToPaladinCalc: View
    private lateinit var navigateToDruidCalc: View
    private lateinit var navigateToSorcererCalc: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        navigateToSorcererCalc = view.findViewById(R.id.ms_calc)
        navigateToKnightCalc = view.findViewById(R.id.ek_calc)
        navigateToPaladinCalc = view.findViewById(R.id.rp_calc)
        navigateToDruidCalc = view.findViewById(R.id.ed_calc)

        navigateKnightCalc()
        navigatePaladinCalc()
        navigateToDruidCalc()
        navigateSorcecerCalc()

        return view
    }

    private fun navigateSorcecerCalc() {
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
    }

    private fun navigateToDruidCalc() {
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
    }

    private fun navigatePaladinCalc() {
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
    }

    private fun navigateKnightCalc() {
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
    }
}
