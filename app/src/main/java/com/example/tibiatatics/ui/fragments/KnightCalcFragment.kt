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
    private var dmgType = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_knight_calc, container, false)

        dropMenuElementalAttack(view)
        dropMenuAttackMode(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            Log.i("###", "onCreateView: ${calcHitBasic(view)}")
        }

        return view
    }

    private fun calcHitBasic(view: View): String {

        val level = view.findViewById<TextInputEditText>(R.id.input_level)
        val levelInt = level.text.toString().toIntOrNull() ?: 0

        val skills = view.findViewById<TextInputEditText>(R.id.input_skills)
        val skillsInt = skills.text.toString().toIntOrNull() ?: 0

        val elementalAttack = view.findViewById<TextInputEditText>(R.id.input_elemental_attack)
        val elementalAttackInt = elementalAttack.text.toString().toIntOrNull() ?: 0

        val physicalAttack = view.findViewById<TextInputEditText>(R.id.input_physical_attack)
        var physicalAttackInt = physicalAttack.text.toString().toIntOrNull() ?: 1
        if (physicalAttackInt == 0) { physicalAttackInt = 1 }

        val physicalResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_physical_resistence)
        val physicalResistanceInt = physicalResistance.text.toString().toIntOrNull() ?: 100

        val iceResistance = view.findViewById<TextInputEditText>(R.id.input_creature_ice_resistence)
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100

        val earthResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_earth_resistence)
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100

        val energyResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_energy_resistence)
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100

        val fireResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_fire_resistence)
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100

        val deathResistance =
            view.findViewById<TextInputEditText>(R.id.input_creature_death_resistence)
        val deathResistanceInt = deathResistance.text.toString().toIntOrNull() ?: 100

        val armor = view.findViewById<TextInputEditText>(R.id.input_creature_armor)
        var armorInt = armor.text.toString().toIntOrNull() ?: 1
        if (armorInt > 130) { armorInt = 130 }

        val resistanceElemental = when (dmgType) {
            1 -> earthResistanceInt
            2 -> fireResistanceInt
            3 -> deathResistanceInt
            4 -> energyResistanceInt
            5 -> iceResistanceInt
            else -> 0
        }

        val calcArmor = armorInt * 0.75
        val totalAttack = physicalAttackInt + elementalAttackInt
        val percentagePhysicalAttack = (physicalAttackInt / totalAttack.toDouble()) * 100.0
        val percentageElementalAttack = (elementalAttackInt / totalAttack.toDouble()) * 100.0


        // CALCULO DE DANO CORPO A CORPO
        val calcTotalDmgPhysical =
            (0.085 * attackMode * physicalAttackInt * skillsInt) + (levelInt / 5).toDouble()
        val calcTotalDmgElemental = 0.085 * attackMode * elementalAttackInt * skillsInt

        val d = calcTotalDmgPhysical - calcArmor
        val dmgWithArmor = if (d <= 0) 0 else d.toInt()
        val dmgPhysicalWithResistence = dmgWithArmor * physicalResistanceInt / 100

        val dmgElementalWithResistence = (calcTotalDmgElemental * resistanceElemental) / 100
        val dmgTotal = dmgElementalWithResistence + dmgPhysicalWithResistence


        // CALCULO DE DANO MINIMO EXORI MAS
        val dmgMinExoriMas =
            0.5 * (skillsInt + physicalAttackInt + elementalAttackInt) + (levelInt / 5).toDouble()
        //total de dano FISICO minimo exori mas
        val minPhysicalDmgExoriMas = (dmgMinExoriMas / 100) * percentagePhysicalAttack
        val dmgMinExoriMasWithArmor = minPhysicalDmgExoriMas - calcArmor
        val dmgMinPhysicalExoriMasTotal =
            if (dmgMinExoriMasWithArmor <= 0) 0 else
                dmgMinExoriMasWithArmor.toInt() * physicalResistanceInt / 100
        //Total de dano ELEMENTAL minimo exori mas
        val minElementalDmgExoriMas = (dmgMinExoriMas / 100) * percentageElementalAttack
        val dmgElementalExoriMasMinTotal = minElementalDmgExoriMas * resistanceElemental / 100
        //Total de dano minimo exori mas
        val minExoriMasTotalDmg = dmgElementalExoriMasMinTotal + dmgMinPhysicalExoriMasTotal

        // CALCULO DE DANO MAXIMO EXORI MAS
        val dmgMaxExoriMas =
            1.1 * (skillsInt + physicalAttackInt + elementalAttackInt) + (levelInt / 5).toDouble()
        //total de dano FISICO maximo exori mas
        val maxPhysicalDmgExoriMas = (dmgMaxExoriMas / 100) * percentagePhysicalAttack
        val dmgMaxExoriMasWithArmor = maxPhysicalDmgExoriMas - calcArmor
        val dmgMaxPhysicalExoriMasTotal =
            if (dmgMaxExoriMasWithArmor <= 0) 0 else
                dmgMaxExoriMasWithArmor.toInt() * physicalResistanceInt / 100
        //Total de Dano ELEMENTAL maximo exori mas
        val maxElementalDmgExoriMas = (dmgMaxExoriMas / 100) * percentageElementalAttack
        val dmgElementalExoriMasMaxTotal = maxElementalDmgExoriMas * resistanceElemental / 100
        //Total de dano maximo Exori Mas
        val maxExoriMasTotalDmg = dmgElementalExoriMasMaxTotal + dmgMaxPhysicalExoriMasTotal

        val averageDmgExoriMas = (minExoriMasTotalDmg + maxExoriMasTotalDmg) / 2



        // CALCULO DE DANO MINIMO EXORI GRAN
        val dmgMinExoriGran =
            ((skillsInt + physicalAttackInt + elementalAttackInt) * 2) * 1.1 + (levelInt / 5).toDouble()
        //total de dano FISICO minimo exori gran
        val minPhysicalDmgExoriGran = (dmgMinExoriGran / 100) * percentagePhysicalAttack
        val dmgMinExoriGranWithArmor = minPhysicalDmgExoriGran - calcArmor
        val dmgMinPhysicalExoriGranTotal =
            if (dmgMinExoriGranWithArmor <= 0) 0 else
                dmgMinExoriGranWithArmor.toInt() * physicalResistanceInt / 100
        //Total de dano ELEMENTAL minimo exori gran
        val minElementalDmgExoriGran = (dmgMinExoriGran / 100) * percentageElementalAttack
        val dmgElementalExoriGranMinTotal = minElementalDmgExoriGran * resistanceElemental / 100
        //Total de dano minimo exori gran
        val minExoriGranTotalDmg = dmgElementalExoriGranMinTotal + dmgMinPhysicalExoriGranTotal


        // CALCULO DE DANO MAXIMO EXORI GRAN
        val dmgMaxExoriGran =
            ((skillsInt + physicalAttackInt + elementalAttackInt) * 2) * 3 + (levelInt / 5).toDouble()
        //total de dano FISICO minimo exori gran
        val maxPhysicalDmgExoriGran = (dmgMaxExoriGran / 100) * percentagePhysicalAttack
        val dmgMaxExoriGranWithArmor = maxPhysicalDmgExoriGran - (calcArmor)
        val dmgMaxPhysicalExoriGranTotal =
            if (dmgMaxExoriGranWithArmor <= 0) 0 else
                dmgMaxExoriGranWithArmor.toInt() * physicalResistanceInt / 100
        //Total de dano ELEMENTAL minimo exori gran
        val maxElementalDmgExoriGran = (dmgMaxExoriGran / 100) * percentageElementalAttack
        val dmgElementalExoriGranMaxTotal = maxElementalDmgExoriGran * resistanceElemental / 100
        //Total de dano minimo exori gran
        val maxExoriGranTotalDmg = dmgElementalExoriGranMaxTotal + dmgMaxPhysicalExoriGranTotal


        val averageExoriGran = (maxExoriGranTotalDmg + minExoriGranTotalDmg) / 2




        val formato = DecimalFormat("#")
        return formato.format(dmgMinExoriGran)
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
                "Ice Damage"
            )


        val elementalTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actElementalType.setAdapter(elementalTypeAdapter)

        actElementalType.setText("Earth Damage", false)
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