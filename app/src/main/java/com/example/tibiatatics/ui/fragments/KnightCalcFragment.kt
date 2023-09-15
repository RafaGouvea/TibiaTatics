package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.tibiatatics.R
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class KnightCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineLegs: RadioGroup
    private var attackMode = 1.0
    private var dmgType = 1
    private var weaponModel = 0
    private var sanguineLegs = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_knight_calc, container, false)

        dropMenuElementalAttack(view)
        dropMenuAttackMode(view)
        dropMenuWeaponMode(view)
        sanguineLegs(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            dmgKnight(view)
        }

        return view
    }

    private fun sanguineLegs(view: View) {
        radioGroupSanguineLegs = view.findViewById(R.id.sanguine_legs)

        radioGroupSanguineLegs.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yesRadioButton -> sanguineLegs = 1
                    R.id.noRadioButton -> sanguineLegs = 0
                }
            }
            when (sanguineLegs){
                1 -> Toast.makeText(requireContext(), "+8% damage on Exori Gran", Toast.LENGTH_SHORT).show()
                0 -> Toast.makeText(requireContext(), "No bonus on Exori Gran", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dmgKnight(view: View) {

        val level = view.findViewById<TextInputEditText>(R.id.input_level)
        val levelInt = level.text.toString().toIntOrNull() ?: 0

        val skills = view.findViewById<TextInputEditText>(R.id.input_skills)
        val skillsInt = skills.text.toString().toIntOrNull() ?: 0

        val elementalAttack = view.findViewById<TextInputEditText>(R.id.input_elemental_attack)
        val elementalAttackInt = elementalAttack.text.toString().toIntOrNull() ?: 0

        val physicalAttack = view.findViewById<TextInputEditText>(R.id.input_physical_attack)
        val physicalAttackInt = physicalAttack.text.toString().toIntOrNull() ?: 0

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

        val formato = DecimalFormat("#")




        basicHit(
            physicalAttackInt,
            skillsInt,
            levelInt,
            elementalAttackInt,
            calcArmor,
            physicalResistanceInt,
            resistanceElemental,
            view,
            formato
        )

        dmgExoriMas(
            skillsInt,
            physicalAttackInt,
            elementalAttackInt,
            levelInt,
            percentagePhysicalAttack,
            calcArmor,
            physicalResistanceInt,
            percentageElementalAttack,
            resistanceElemental,
            view,
            formato

        )

        dmgExoriGran(
            skillsInt,
            levelInt,
            physicalAttackInt,
            elementalAttackInt,
            percentagePhysicalAttack,
            calcArmor,
            physicalResistanceInt,
            percentageElementalAttack,
            resistanceElemental,
            view,
            formato
        )

        dmgExori(
            skillsInt,
            levelInt,
            physicalAttackInt,
            elementalAttackInt,
            percentagePhysicalAttack,
            calcArmor,
            physicalResistanceInt,
            percentageElementalAttack,
            resistanceElemental,
            view,
            formato
        )

    }

    private fun basicHit(
        physicalAttackInt: Int,
        skillsInt: Int,
        levelInt: Int,
        elementalAttackInt: Int,
        calcArmor: Double,
        physicalResistanceInt: Int,
        resistanceElemental: Int,
        view: View,
        stringFormat: DecimalFormat
    ) {
        val calcTotalDmgPhysical =
            (0.085 * attackMode * physicalAttackInt * skillsInt) + (levelInt / 5).toDouble()
        val calcTotalDmgElemental = 0.085 * attackMode * elementalAttackInt * skillsInt

        val d = calcTotalDmgPhysical - calcArmor
        val dmgWithArmor = if (d <= 0) 0 else d.toInt()
        val dmgPhysicalWithResistence = dmgWithArmor * physicalResistanceInt / 100
        val dmgElementalWithResistence = (calcTotalDmgElemental * resistanceElemental) / 100

        val dmgTotal = dmgElementalWithResistence + dmgPhysicalWithResistence
        val maxBasic = view.findViewById<TextView>(R.id.maxBasic)
        maxBasic.text = stringFormat.format(dmgTotal)


        val averageDmgTotal = dmgTotal / 2
        val averageBasic = view.findViewById<TextView>(R.id.averageBasic)
        averageBasic.text = stringFormat.format(averageDmgTotal)

        val averageCritBasic = view.findViewById<TextView>(R.id.averageCritBasic)
        val maxCritBasic = view.findViewById<TextView>(R.id.maxCritBasic)

        val dmgCritTotal = dmgTotal + (dmgTotal * 0.5)
        val averageDmgCrit = dmgCritTotal / 2

        val dmgCritSoulWar = dmgTotal + (dmgTotal * 0.6)
        val averageDmgCritSoulWar = dmgCritSoulWar / 2

        val dmgCritSanguine = dmgTotal + (dmgTotal * 0.62)
        val averageDmgCritSanguine = dmgCritSanguine / 2

        when (weaponModel) {
            0 -> {
                maxCritBasic.text = stringFormat.format(dmgCritTotal)
                averageCritBasic.text = stringFormat.format(averageDmgCrit)
            }
            1 -> {
                maxCritBasic.text = stringFormat.format(dmgCritSoulWar)
                averageCritBasic.text = stringFormat.format(averageDmgCritSoulWar)
            }

            else -> {
                maxCritBasic.text = stringFormat.format(dmgCritSanguine)
                averageCritBasic.text = stringFormat.format(averageDmgCritSanguine)
            }
        }
    }

    private fun dmgExori(
        skillsInt: Int,
        levelInt: Int,
        physicalAttackInt: Int,
        elementalAttackInt: Int,
        percentagePhysicalAttack: Double,
        calcArmor: Double,
        physicalResistanceInt: Int,
        percentageElementalAttack: Double,
        resistanceElemental: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val dmgMinBaseExori = skillsInt * 0.5 + (levelInt / 5)
        val minPhysicalExori = physicalAttackInt * 0.5
        val minElementalExori = elementalAttackInt * 0.5

        val minExoriPercentage = dmgMinBaseExori + minPhysicalExori + minElementalExori
        val minPhysicalDmgExori = (minExoriPercentage / 100) * percentagePhysicalAttack
        val dmgMinExoriArmor = minPhysicalDmgExori - calcArmor
        val dmgMinPhysicalExoriTotal =
            if (dmgMinExoriArmor <= 0) 0 else
                dmgMinExoriArmor.toInt() * physicalResistanceInt / 100
        val mixElementalDmgExori = (minExoriPercentage / 100) * percentageElementalAttack
        val minElementalExoriTotal = mixElementalDmgExori * resistanceElemental / 100

        val minExoriTotalDmg = minElementalExoriTotal + dmgMinPhysicalExoriTotal
        val minCritExoriTotalDmg = minExoriTotalDmg + (minExoriTotalDmg * 0.5)
        val minCritSoulWar = minExoriTotalDmg + (minExoriTotalDmg * 0.6)
        val minCritSanguine = minExoriTotalDmg + (minExoriTotalDmg * 0.62)



        val dmgMaxBaseExori = skillsInt * 1.5 + (levelInt / 5)
        val maxPhysicalExori = physicalAttackInt * 1.5
        val maxElementalExori = elementalAttackInt * 1.5
        val maxExoriPercentage = dmgMaxBaseExori + maxPhysicalExori + maxElementalExori

        val maxPhysicalDmgExori = (maxExoriPercentage / 100) * percentagePhysicalAttack
        val dmgMaxExoriArmor = maxPhysicalDmgExori - calcArmor
        val dmgMaxPhysicalExoriTotal =
            if (dmgMaxExoriArmor <= 0) 0 else
                dmgMaxExoriArmor.toInt() * physicalResistanceInt / 100

        val maxElementalDmgExori = (maxExoriPercentage / 100) * percentageElementalAttack
        val maxElementalExoriTotal = maxElementalDmgExori * resistanceElemental / 100

        val maxNormalExori = maxElementalExoriTotal + dmgMaxPhysicalExoriTotal
        val maxCritNormalExori = maxNormalExori + (maxNormalExori * 0.5)
        val maxCritSoulWar = maxNormalExori + (maxNormalExori * 0.6)
        val maxCritSanguine = maxNormalExori + (maxNormalExori * 0.62)

        val averageNormalExori = (maxNormalExori + minExoriTotalDmg) / 2
        val averageNormalCritExori = averageNormalExori + (averageNormalExori * 0.5)
        val averageCritSoulWar = averageNormalExori + (averageNormalExori * 0.6)
        val averageCritSanguine = averageNormalExori + (averageNormalExori * 0.62)


        val minExori = view.findViewById<TextView>(R.id.minExori)
        val minCritExori = view.findViewById<TextView>(R.id.minCritExori)
        val averageExori = view.findViewById<TextView>(R.id.averageExori)
        val averageCritExori = view.findViewById<TextView>(R.id.averageCritExori)
        val maxExori = view.findViewById<TextView>(R.id.maxExori)
        val maxCritExori = view.findViewById<TextView>(R.id.maxCritExori)


        minExori.text = formato.format(minExoriTotalDmg)
        averageExori.text = formato.format(averageNormalExori)
        maxExori.text = formato.format(maxNormalExori)

        when (weaponModel) {
            0 -> {
                minCritExori.text = formato.format(minCritExoriTotalDmg)
                averageCritExori.text = formato.format(averageNormalCritExori)
                maxCritExori.text = formato.format(maxCritNormalExori)
            }

            1 -> {
                minCritExori.text = formato.format(minCritSoulWar)
                averageCritExori.text = formato.format(averageCritSoulWar)
                maxCritExori.text = formato.format(maxCritSoulWar)
            }

            else -> {
                minCritExori.text = formato.format(minCritSanguine)
                averageCritExori.text = formato.format(averageCritSanguine)
                maxCritExori.text = formato.format(maxCritSanguine)
            }
        }
    }

    private fun dmgExoriGran(
        skillsInt: Int,
        levelInt: Int,
        physicalAttackInt: Int,
        elementalAttackInt: Int,
        percentagePhysicalAttack: Double,
        calcArmor: Double,
        physicalResistanceInt: Int,
        percentageElementalAttack: Double,
        resistanceElemental: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val dmgMinBaseExoriGran = (skillsInt * 2) * 1.1 + (levelInt / 5).toDouble()
        val minPhysicalExoriGran = physicalAttackInt * 2 * 1.1
        val minElementalExoriGran = elementalAttackInt * 2 * 1.1
        val minDmgValue = dmgMinBaseExoriGran + minPhysicalExoriGran + minElementalExoriGran
        val minExoriGranDmg = when (weaponModel){
            2 -> minDmgValue + (minDmgValue * 0.08)
            3 -> minDmgValue + (minDmgValue * 0.15)
            else -> {
                minDmgValue
            }
        }
        val minPhysicalDmgExoriGran = (minExoriGranDmg / 100) * percentagePhysicalAttack
        val dmgMinExoriGranWithArmor = minPhysicalDmgExoriGran - calcArmor
        val dmgMinPhysicalExoriGranTotal =
            if (dmgMinExoriGranWithArmor <= 0) 0 else
                dmgMinExoriGranWithArmor.toInt() * physicalResistanceInt / 100

        val minElementalDmgExoriGran = (minExoriGranDmg / 100) * percentageElementalAttack
        val dmgElementalExoriGranMinTotal = minElementalDmgExoriGran * resistanceElemental / 100

        val minExoriGranTotalDmg = dmgElementalExoriGranMinTotal + dmgMinPhysicalExoriGranTotal
        var minCritExoriGranTotalDmg = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.5)
        var minCritSoulWar = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.6)
        var minCritSanguine = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.62)

        val dmgMaxBaseExoriGran = (skillsInt * 2) * 3 + (levelInt / 5).toDouble()
        val maxPhysicalExoriGran = physicalAttackInt * 2 * 3
        val maxElementalExoriGran = elementalAttackInt * 2 * 3
        val maxDmgValue = dmgMaxBaseExoriGran + maxPhysicalExoriGran + maxElementalExoriGran

        val maxExoriGranDmg = when (weaponModel) {
            2 -> maxDmgValue + (maxDmgValue * 0.08)
            3 -> maxDmgValue + (maxDmgValue * 0.15)
            else -> maxDmgValue
        }

        val maxPhysicalDmgExoriGran = (maxExoriGranDmg / 100) * percentagePhysicalAttack
        val dmgMaxExoriGranWithArmor = maxPhysicalDmgExoriGran - calcArmor
        val dmgMaxPhysicalExoriGranTotal =
            if (dmgMaxExoriGranWithArmor <= 0) 0 else
                dmgMaxExoriGranWithArmor.toInt() * physicalResistanceInt / 100

        val maxElementalDmgExoriGran = (maxExoriGranDmg / 100) * percentageElementalAttack
        val maxElementalExoriGranTotal = maxElementalDmgExoriGran * resistanceElemental / 100
        val maxExoriGranTotalDmg = maxElementalExoriGranTotal + dmgMaxPhysicalExoriGranTotal


        var maxCritExoriGranTotalDmg = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.5)
        var maxCritSoulWar = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.6)
        var maxCritSanguine = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.62)

        val averageNormalExoriGran = (minExoriGranTotalDmg + maxExoriGranTotalDmg) / 2
        var averageNormalCritExoriGran = averageNormalExoriGran + (averageNormalExoriGran * 0.5)
        var averageCritSoulWar= averageNormalExoriGran + (averageNormalExoriGran * 0.6)
        var averageCritSanguine = averageNormalExoriGran + (averageNormalExoriGran * 0.62)

        when (sanguineLegs){
            1 -> {
                minCritExoriGranTotalDmg = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.58)
                minCritSoulWar = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.68)
                minCritSanguine = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.70)

                averageNormalCritExoriGran = averageNormalExoriGran + (averageNormalExoriGran * 0.58)
                averageCritSoulWar= averageNormalExoriGran + (averageNormalExoriGran * 0.68)
                averageCritSanguine = averageNormalExoriGran + (averageNormalExoriGran * 0.70)

                maxCritExoriGranTotalDmg = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.58)
                maxCritSoulWar = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.68)
                maxCritSanguine = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.70)
            }
        }

        val minExoriGran = view.findViewById<TextView>(R.id.minExoriGran)
        val minCritExoriGran = view.findViewById<TextView>(R.id.minCritExoriGran)
        val averageExoriGran = view.findViewById<TextView>(R.id.averageExoriGran)
        val averageCritExoriGran = view.findViewById<TextView>(R.id.averageCritExoriGran)
        val maxExoriGran = view.findViewById<TextView>(R.id.maxExoriGran)
        val maxCritExoriGran = view.findViewById<TextView>(R.id.maxCritExoriGran)


        minExoriGran.text = formato.format(minExoriGranTotalDmg)
        averageExoriGran.text = formato.format(averageNormalExoriGran)
        maxExoriGran.text = formato.format(maxExoriGranTotalDmg)

        when (weaponModel) {
            0 -> {
                minCritExoriGran.text = formato.format(minCritExoriGranTotalDmg)
                averageCritExoriGran.text = formato.format(averageNormalCritExoriGran)
                maxCritExoriGran.text = formato.format(maxCritExoriGranTotalDmg)
            }

            1 -> {
                minCritExoriGran.text = formato.format(minCritSoulWar)
                averageCritExoriGran.text = formato.format(averageCritSoulWar)
                maxCritExoriGran.text = formato.format(maxCritSoulWar)
            }

            else -> {
                minCritExoriGran.text = formato.format(minCritSanguine)
                averageCritExoriGran.text = formato.format(averageCritSanguine)
                maxCritExoriGran.text = formato.format(maxCritSanguine)
            }
        }


    }

    private fun dmgExoriMas(
        skillsInt: Int,
        physicalAttackInt: Int,
        elementalAttackInt: Int,
        levelInt: Int,
        percentagePhysicalAttack: Double,
        calcArmor: Double,
        physicalResistanceInt: Int,
        percentageElementalAttack: Double,
        resistanceElemental: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val dmgMinExoriMas =
            0.5 * (skillsInt + physicalAttackInt + elementalAttackInt) + (levelInt / 5).toDouble()
        val minPhysicalDmgExoriMas = (dmgMinExoriMas / 100) * percentagePhysicalAttack
        val dmgMinExoriMasWithArmor = minPhysicalDmgExoriMas - calcArmor
        val dmgMinPhysicalExoriMasTotal =
            if (dmgMinExoriMasWithArmor <= 0) 0 else
                dmgMinExoriMasWithArmor.toInt() * physicalResistanceInt / 100

        val minElementalDmgExoriMas = (dmgMinExoriMas / 100) * percentageElementalAttack
        val dmgElementalExoriMasMinTotal = minElementalDmgExoriMas * resistanceElemental / 100

        val minExoriMasTotalDmg = dmgElementalExoriMasMinTotal + dmgMinPhysicalExoriMasTotal
        val minCritExoriMasTotalDmg = minExoriMasTotalDmg + (minExoriMasTotalDmg * 0.5)
        val minCritExoriMasSoulWar = minExoriMasTotalDmg + (minExoriMasTotalDmg * 0.6)
        val minCritExoriSanguine = minExoriMasTotalDmg + (minExoriMasTotalDmg * 0.62)


        val dmgMaxExoriMas =
            1.1 * (skillsInt + physicalAttackInt + elementalAttackInt) + (levelInt / 5).toDouble()
        val maxPhysicalDmgExoriMas = (dmgMaxExoriMas / 100) * percentagePhysicalAttack
        val dmgMaxExoriMasWithArmor = maxPhysicalDmgExoriMas - calcArmor
        val dmgMaxPhysicalExoriMasTotal =
            if (dmgMaxExoriMasWithArmor <= 0) 0 else
                dmgMaxExoriMasWithArmor.toInt() * physicalResistanceInt / 100

        val maxElementalDmgExoriMas = (dmgMaxExoriMas / 100) * percentageElementalAttack
        val dmgElementalExoriMasMaxTotal = maxElementalDmgExoriMas * resistanceElemental / 100

        val maxExoriMasTotalDmg = dmgElementalExoriMasMaxTotal + dmgMaxPhysicalExoriMasTotal
        val maxCritExoriMasTotalDmg = maxExoriMasTotalDmg + (maxExoriMasTotalDmg * 0.5)
        val maxCritExoriMasSoulWar = maxExoriMasTotalDmg + (maxExoriMasTotalDmg * 0.6)
        val maxCritExoriMasSanguine = maxExoriMasTotalDmg + (maxExoriMasTotalDmg * 0.62)

        val averageNormalExoriMas = (minExoriMasTotalDmg + maxExoriMasTotalDmg) / 2
        val averageNormalCritExoriMas = averageNormalExoriMas + (averageNormalExoriMas * 0.5)
        val averageCritSoulWar = averageNormalExoriMas + (averageNormalExoriMas * 0.6)
        val averageCritSanguine = averageNormalExoriMas + (averageNormalExoriMas * 0.62)

        val minExoriMas = view.findViewById<TextView>(R.id.minExoriMas)
        val minCritExoriMas = view.findViewById<TextView>(R.id.minCritExoriMas)
        val averageExoriMas = view.findViewById<TextView>(R.id.averageExoriMas)
        val averageCritExoriMas = view.findViewById<TextView>(R.id.averageCritExoriMas)
        val maxExoriMas = view.findViewById<TextView>(R.id.maxExoriMas)
        val maxCritExoriMas = view.findViewById<TextView>(R.id.maxCritExoriMas)


        minExoriMas.text = formato.format(minExoriMasTotalDmg)
        averageExoriMas.text = formato.format(averageNormalExoriMas)
        maxExoriMas.text = formato.format(maxExoriMasTotalDmg)

        when (weaponModel) {
            0 -> {
                minCritExoriMas.text = formato.format(minCritExoriMasTotalDmg)
                averageCritExoriMas.text = formato.format(averageNormalCritExoriMas)
                maxCritExoriMas.text = formato.format(maxCritExoriMasTotalDmg)
            }

            1 -> {
                minCritExoriMas.text = formato.format(minCritExoriMasSoulWar)
                averageCritExoriMas.text = formato.format(averageCritSoulWar)
                maxCritExoriMas.text = formato.format(maxCritExoriMasSoulWar)
            }

            else -> {
                minCritExoriMas.text = formato.format(minCritExoriSanguine)
                averageCritExoriMas.text = formato.format(averageCritSanguine)
                maxCritExoriMas.text = formato.format(maxCritExoriMasSanguine)
            }
        }
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

    private fun dropMenuWeaponMode(view: View) {

        val actWeaponModel: AutoCompleteTextView =
            view.findViewById(R.id.weaponComplete)
        val listElementalType =
            arrayOf(
                "Normal Weapon",
                "Soulwar Weapon",
                "Sanguine Weapon",
                "Grand Sanguine Weapon",
            )

        val weaponModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actWeaponModel.setAdapter(weaponModelAdapter)

        actWeaponModel.setText("Normal Weapon", false)
        actWeaponModel.setOnClickListener {
            actWeaponModel.showDropDown()
        }

        actWeaponModel.setOnItemClickListener { _, _, position, _ ->
            val weaponModelValue = when (listElementalType[position]) {
                "Normal Weapon" -> 0
                "Soulwar Weapon" -> 1
                "Sanguine Weapon" -> 2
                "Grand Sanguine Weapon" -> 3
                else -> 0
            }
            weaponModel = weaponModelValue
        }
    }
}