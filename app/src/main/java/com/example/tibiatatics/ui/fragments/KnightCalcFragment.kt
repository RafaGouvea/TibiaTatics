package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.tibiatatics.R

class KnightCalcFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_knight_calc, container, false)

        dropMenu(view)

        return view
    }

    private fun dropMenu(view: View) {
        val actElementalType: AutoCompleteTextView = view.findViewById(R.id.elementalTypeAutoComplete)

        val listElementalType = ArrayList<String>()
        listElementalType.add("Earth Damage")
        listElementalType.add("Fire Damage")
        listElementalType.add("Death Damage")
        listElementalType.add("Energy Damage")
        listElementalType.add("Ice Damage")

        val elementalTypeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listElementalType)
        actElementalType.setAdapter(elementalTypeAdapter)
        actElementalType.setOnClickListener {
            actElementalType.showDropDown()
        }

        actElementalType.setOnItemClickListener { parent, _, position, _ ->
            Toast.makeText(requireContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
    }

}