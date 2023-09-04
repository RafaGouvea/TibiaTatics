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

        btnCalcular.setOnClickListener { Log.i("###", "onCreateView: ${calcHitBasic(view)}") }


        return view
    }

    private fun calcHitBasic(view: View): String {

        val level = view.findViewById<TextInputEditText>(R.id.input_level)
        val levelString = level.text.toString()
        val levelInt = levelString.toIntOrNull() ?: 0

        val skills = view.findViewById<TextInputEditText>(R.id.input_skills)
        val skillsString = skills.text.toString()
        val skillsInt = skillsString.toIntOrNull() ?: 0

        val elementalAttack = view.findViewById<TextInputEditText>(R.id.input_elemental_attack)
        val elementalAttackString = elementalAttack.text.toString()
        val elementalAttackInt = elementalAttackString.toIntOrNull() ?: 0

        val physicalAttack = view.findViewById<TextInputEditText>(R.id.input_physical_attack)
        val physicalAttackString = physicalAttack.text.toString()
        val physicalAttackInt = physicalAttackString.toIntOrNull() ?: 0

        val physicalResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_physical_resistence)
        val physicalResistanceString = physicalResistance.text.toString()
        val physicalResistanceInt = physicalResistanceString.toIntOrNull() ?: 100

        val iceResistance = view.findViewById<TextInputEditText>(R.id.input_creature_ice_resistence)
        val iceResistanceString = iceResistance.text.toString()
        val iceResistanceInt = iceResistanceString.toIntOrNull() ?: 100

        val earthResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_earth_resistence)
        val earthResistanceString = earthResistance.text.toString()
        val earthResistanceInt = earthResistanceString.toIntOrNull() ?: 100

        val energyResistance = view.findViewById<TextInputEditText>(R.id.input_creature_energy_resistence)
        val energyResistanceString = energyResistance.text.toString()
        val energyResistanceInt = energyResistanceString.toIntOrNull() ?: 100

        val fireResistance = view.findViewById<TextInputEditText>(R.id.input_creature_fire_resistence)
        val fireResistanceString = fireResistance.text.toString()
        val fireResistanceInt = fireResistanceString.toIntOrNull() ?: 100

        val deathResistance = view.findViewById<TextInputEditText>(R.id.input_creature_death_resistence)
        val deathResistanceString = deathResistance.text.toString()
        val deathResistanceInt = deathResistanceString.toIntOrNull() ?: 100

        val armor = view.findViewById<TextInputEditText>(R.id.input_creature_armor)
        val armorString = armor.text.toString()
        val armorInt = armorString.toIntOrNull() ?: 1


        // CALCULO DE DANO CORPO A CORPO
        val calcElementalAttack = when (dmgType) {
            1 -> elementalAttackInt * earthResistanceInt / 100
            2 -> elementalAttackInt * fireResistanceInt / 100
            3 -> elementalAttackInt * deathResistanceInt / 100
            4 -> elementalAttackInt * energyResistanceInt / 100
            5 -> elementalAttackInt * iceResistanceInt / 100
            else -> 0
        }

        val calcDmgPhysical = physicalAttackInt * physicalResistanceInt / 100
        val calcArmor = (armorInt * 0.71) - 1

        val calcTotalDmgPhysical = (0.085 * attackMode * calcDmgPhysical * skillsInt) + (levelInt / 5)
        val calcTotalDmgElemental = (0.085 * attackMode * calcElementalAttack * skillsInt) + (levelInt / 5)

        val d = calcTotalDmgPhysical - calcArmor
        val dmgWithArmorHit = if (d <= 0 || physicalAttackInt == 0) 0 else d.toInt()

        val resultadoFinal = dmgWithArmorHit + calcTotalDmgElemental





        // CALCULO DE DANO EXORI MAS
        val weaponAttackMin = (0.5 * ((calcDmgPhysical + calcElementalAttack) + skillsInt)) + (levelInt / 5)
        val weaponAttackMax = (1.1 * ((calcDmgPhysical + calcElementalAttack) + skillsInt)) + (levelInt / 5)
        val weaponAttackMedia = weaponAttackMin + weaponAttackMax / 2







        val formato = DecimalFormat("#")

        return formato.format(resultadoFinal)
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

        actAttackMode.setText("Full Attack", false)
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
            arrayOf(
                "Earth Damage",
                "Fire Damage",
                "Death Damage",
                "Energy Damage",
                "Ice Damage",
                "None"
            )


        val elementalTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actElementalType.setAdapter(elementalTypeAdapter)

        actElementalType.setText("None", false)
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