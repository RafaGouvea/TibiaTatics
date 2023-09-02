package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import com.example.tibiatatics.R
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class KnightCalcFragment : Fragment() {

    private var attackMode = 1.0
    private var dmgType = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_knight_calc, container, false)

        dropMenuElementalAttack(view)
        dropMenuAttackMode(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            val resultado = when (dmgType) {
                1 -> "Earth Damage"
                2 -> "Fire Damage"
                3 -> "Death Damage"
                4 -> "Energy Damage"
                5 -> "Ice Damage"
                else -> {
                    "Physical Damage"
                }
            }
            Log.i("###", "onCreateView: $resultado")
        }

        //btnCalcular.setOnClickListener { Log.i("###", "onCreateView: ${teste(view)}")}


        return view
    }

    private fun teste(view: View): String {

        val level = view.findViewById<TextInputEditText>(R.id.input_level)
        val levelString = level.text.toString()
        val levelInt = levelString.toInt()

        val skills = view.findViewById<TextInputEditText>(R.id.input_skills)
        val skillsString = skills.text.toString()
        val skillsInt = skillsString.toIntOrNull() ?: 0

        val elementalAttack = view.findViewById<TextInputEditText>(R.id.input_elemental_attack)
        val elementalAttackString = elementalAttack.text.toString()
        val elementalAttackInt = elementalAttackString.toIntOrNull() ?: 0

        val physicalAttack = view.findViewById<TextInputEditText>(R.id.input_physical_attack)
        val physicalAttackString = physicalAttack.text.toString()
        val physicalAttackInt = physicalAttackString.toIntOrNull() ?: 0

        val physicalResistence =
            view.findViewById<TextInputEditText>(R.id.input_creature_physical_resistence)
        val physicalResistenceString = physicalResistence.text.toString()
        val physicalResistenceInt = physicalResistenceString.toIntOrNull() ?: 100

        val iceResistence = view.findViewById<TextInputEditText>(R.id.input_creature_ice_resistence)
        val iceResistenceString = iceResistence.text.toString()
        val iceResistenceInt = iceResistenceString.toIntOrNull() ?: 100

        val teste1 = physicalAttackInt * physicalResistenceInt / 100
        val teste2 = elementalAttackInt * iceResistenceInt / 100


        val d = (0.085 * attackMode * (teste1 + teste2) * skillsInt) + (levelInt / 5)
        val formato = DecimalFormat("#.00")

        return formato.format(d)
    }

    private fun dropMenuAttackMode(view: View) {

        val actAttackMode: AutoCompleteTextView = view.findViewById(R.id.AttackAutoCompleter)
        val listAttackMode = arrayOf("Full Attack", "Balanced Attack", "Full Defensive")


        val listAttackAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listAttackMode
        )
        actAttackMode.setAdapter(listAttackAdapter)
        actAttackMode.setOnClickListener {
            actAttackMode.showDropDown()
        }

        actAttackMode.setOnItemClickListener { _, _, position, _ ->
            val attackModeValue = when (listAttackMode[position]) {
                "Full Attack" -> 1.0
                "Balanced Attack" -> 0.75
                "Full Defensive" -> 0.5
                else -> 1.0
            }
            attackMode = attackModeValue
        }
    }

    private fun dropMenuElementalAttack(view: View) {

        val actElementalType: AutoCompleteTextView =
            view.findViewById(R.id.elementalTypeAutoComplete)
        val listElementalType =
            arrayOf("Earth Damage", "Fire Damage", "Death Damage", "Energy Damage", "Ice Damage")


        val elementalTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actElementalType.setAdapter(elementalTypeAdapter)
        actElementalType.setOnClickListener {
            actElementalType.showDropDown()
        }



        actElementalType.setOnItemClickListener { _, _, position, _ ->
            val attackModeValue = when (listElementalType[position]) {
                "Earth Damage" -> 1
                "Fire Damage" -> 2
                "Death Damage" -> 3
                "Energy Damage" -> 4
                "Ice Damage" -> 5
                else -> 0
            }
            dmgType = attackModeValue
        }


    }

}