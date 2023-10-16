package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
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
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.tatics.tibiatatics.R
import java.text.DecimalFormat

class KnightCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineLegs: RadioGroup
    private var attackMode = 1.0
    private var dmgType = 1
    private var weaponModel = 0
    private var sanguineLegs = 0
    private lateinit var level: TextInputEditText
    private lateinit var skills: TextInputEditText
    private lateinit var elementalAttack: TextInputEditText
    private lateinit var physicalAttack: TextInputEditText
    private lateinit var physicalResistance: TextInputEditText
    private lateinit var iceResistance: TextInputEditText
    private lateinit var earthResistance: TextInputEditText
    private lateinit var energyResistance: TextInputEditText
    private lateinit var fireResistance: TextInputEditText
    private lateinit var deathResistance: TextInputEditText
    private lateinit var armor: TextInputEditText
    private lateinit var maxBasic: TextView
    private lateinit var averageBasic: TextView
    private lateinit var averageCritBasic: TextView
    private lateinit var maxCritBasic: TextView
    private lateinit var minExori: TextView
    private lateinit var minCritExori: TextView
    private lateinit var averageExori: TextView
    private lateinit var averageCritExori: TextView
    private lateinit var maxExori: TextView
    private lateinit var maxCritExori: TextView
    private lateinit var minExoriGran: TextView
    private lateinit var minCritExoriGran: TextView
    private lateinit var averageExoriGran: TextView
    private lateinit var averageCritExoriGran: TextView
    private lateinit var maxExoriGran: TextView
    private lateinit var maxCritExoriGran: TextView
    private lateinit var minExoriMas: TextView
    private lateinit var minCritExoriMas: TextView
    private lateinit var averageExoriMas: TextView
    private lateinit var averageCritExoriMas: TextView
    private lateinit var maxExoriMas: TextView
    private lateinit var maxCritExoriMas: TextView
    private lateinit var actAttackMode: AutoCompleteTextView
    private lateinit var actElementalType: AutoCompleteTextView
    private lateinit var actWeaponModel: AutoCompleteTextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_knight_calc, container, false)

        viewById(view)
        dropMenuElementalAttack()
        dropMenuAttackMode()
        dropMenuWeaponMode()
        sanguineLegs(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_calcular)

        btnCalcular.setOnClickListener {
            dmgKnight()
        }

        return view
    }

    private fun viewById(view: View) {
        actWeaponModel = view.findViewById(R.id.weaponComplete)
        actElementalType = view.findViewById(R.id.elementalTypeAutoComplete)
        actAttackMode = view.findViewById(R.id.AttackAutoCompleter)
        minExoriMas = view.findViewById(R.id.min_exori_mas)
        minCritExoriMas = view.findViewById(R.id.min_crit_exori_mas)
        averageExoriMas = view.findViewById(R.id.average_exori_mas)
        averageCritExoriMas = view.findViewById(R.id.average_crit_exori_mas)
        maxExoriMas = view.findViewById(R.id.max_exori_mas)
        maxCritExoriMas = view.findViewById(R.id.max_crit_exori_mas)
        minExoriGran = view.findViewById(R.id.min_exori_gran)
        minCritExoriGran = view.findViewById(R.id.min_crit_exori_gran)
        averageExoriGran = view.findViewById(R.id.average_exori_gran)
        averageCritExoriGran = view.findViewById(R.id.average_crit_exori_gran)
        maxExoriGran = view.findViewById(R.id.max_exori_gran)
        maxCritExoriGran = view.findViewById(R.id.max_crit_exori_gran)
        minExori = view.findViewById(R.id.min_exori)
        minCritExori = view.findViewById(R.id.min_crit_exori)
        averageExori = view.findViewById(R.id.average_exori)
        averageCritExori = view.findViewById(R.id.average_crit_exori)
        maxExori = view.findViewById(R.id.max_exori)
        maxCritExori = view.findViewById(R.id.max_crit_exori)
        level = view.findViewById(R.id.input_level)
        skills = view.findViewById(R.id.input_skills)
        elementalAttack = view.findViewById(R.id.input_elemental_attack)
        physicalAttack = view.findViewById(R.id.input_physical_attack)
        physicalResistance = view.findViewById(R.id.input_creature_physical_resistence)
        iceResistance = view.findViewById(R.id.input_creature_ice_resistence)
        earthResistance = view.findViewById(R.id.input_creature_earth_resistence)
        energyResistance = view.findViewById(R.id.input_creature_energy_resistence)
        fireResistance = view.findViewById(R.id.input_creature_fire_resistence)
        deathResistance = view.findViewById(R.id.input_creature_death_resistence)
        armor = view.findViewById(R.id.input_creature_armor)
        maxBasic = view.findViewById(R.id.max_basic)
        averageBasic = view.findViewById(R.id.average_basic)
        averageCritBasic = view.findViewById(R.id.average_crit_basic)
        maxCritBasic = view.findViewById(R.id.max_crit_basic)
    }

    private fun sanguineLegs(view: View) {
        radioGroupSanguineLegs = view.findViewById(R.id.sanguine_legs)

        radioGroupSanguineLegs.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yes_radio_button -> sanguineLegs = 1
                    R.id.no_radio_button -> sanguineLegs = 0
                }
            }
            when (sanguineLegs) {
                1 -> Toast.makeText(
                    requireContext(),
                    "+8% damage on Exori Gran",
                    Toast.LENGTH_SHORT
                ).show()

                0 -> Toast.makeText(requireContext(), "No bonus on Exori Gran", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun dmgKnight() {
        val levelInt = level.text.toString().toIntOrNull() ?: 0
        val skillsInt = skills.text.toString().toIntOrNull() ?: 0
        val elementalAttackInt = elementalAttack.text.toString().toIntOrNull() ?: 0
        val physicalAttackInt = physicalAttack.text.toString().toIntOrNull() ?: 0
        val physicalResistanceInt = physicalResistance.text.toString().toIntOrNull() ?: 100
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100
        val deathResistanceInt = deathResistance.text.toString().toIntOrNull() ?: 100

        var armorInt = armor.text.toString().toIntOrNull() ?: 1
        if (armorInt > 130) {
            armorInt = 130
        }

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
        maxBasic.text = stringFormat.format(dmgTotal)

        val averageDmgTotal = dmgTotal / 2
        averageBasic.text = stringFormat.format(averageDmgTotal)

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
        formato: DecimalFormat
    ) {
        val dmgMinBaseExoriGran = (skillsInt * 2) * 1.1 + (levelInt / 5).toDouble()
        val minPhysicalExoriGran = physicalAttackInt * 2 * 1.1
        val minElementalExoriGran = elementalAttackInt * 2 * 1.1
        val minDmgValue = dmgMinBaseExoriGran + minPhysicalExoriGran + minElementalExoriGran
        val minExoriGranDmg = when (weaponModel) {
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
        var averageCritSoulWar = averageNormalExoriGran + (averageNormalExoriGran * 0.6)
        var averageCritSanguine = averageNormalExoriGran + (averageNormalExoriGran * 0.62)

        when (sanguineLegs) {
            1 -> {
                minCritExoriGranTotalDmg = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.58)
                minCritSoulWar = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.68)
                minCritSanguine = minExoriGranTotalDmg + (minExoriGranTotalDmg * 0.70)

                averageNormalCritExoriGran =
                    averageNormalExoriGran + (averageNormalExoriGran * 0.58)
                averageCritSoulWar = averageNormalExoriGran + (averageNormalExoriGran * 0.68)
                averageCritSanguine = averageNormalExoriGran + (averageNormalExoriGran * 0.70)

                maxCritExoriGranTotalDmg = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.58)
                maxCritSoulWar = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.68)
                maxCritSanguine = maxExoriGranTotalDmg + (maxExoriGranTotalDmg * 0.70)
            }
        }

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

    private fun dropMenuAttackMode() {
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

    private fun dropMenuElementalAttack() {
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

    private fun dropMenuWeaponMode() {
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