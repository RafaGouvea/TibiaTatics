package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tibiatatics.R


class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val navigateToKnightCalc = view.findViewById<View>(R.id.ek_calc)
        navigateToKnightCalc.setOnClickListener {
            findNavController().navigate(R.id.action_menu_library_to_knightCalcFragment)
        }
        val navigateToPaladinCalc = view.findViewById<View>(R.id.rp_calc)
        navigateToPaladinCalc.setOnClickListener {
            findNavController().navigate(R.id.action_menu_library_to_paladinCalcFragment)
        }
        val navigateToDruidCalc = view.findViewById<View>(R.id.ed_calc)
        navigateToDruidCalc.setOnClickListener {
            findNavController().navigate(R.id.action_menu_library_to_druidCalcFragment)
        }
        val navigateToSorcererCalc = view.findViewById<View>(R.id.ms_calc)
        navigateToSorcererCalc.setOnClickListener {
            findNavController().navigate(R.id.action_menu_library_to_sorcererCalcFragment)
        }

        return view
    }



}