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


class PaladinCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineGreaves: RadioGroup
    private var weaponModel = 0
    private var attackMode = 1.0
    private var sanguineGreaves = 0
    private lateinit var actWeaponModel: AutoCompleteTextView
    private lateinit var actAttackMode: AutoCompleteTextView
    private lateinit var level: TextInputEditText
    private lateinit var magicLevel: TextInputEditText
    private lateinit var skills: TextInputEditText
    private lateinit var arrowAttack: TextInputEditText
    private lateinit var bowAttack: TextInputEditText
    private lateinit var physicalResistance: TextInputEditText
    private lateinit var iceResistance: TextInputEditText
    private lateinit var earthResistance: TextInputEditText
    private lateinit var energyResistance: TextInputEditText
    private lateinit var fireResistance: TextInputEditText
    private lateinit var holyResistance: TextInputEditText
    private lateinit var armor: TextInputEditText
    private lateinit var tvMinThunder: TextView
    private lateinit var tvMinCritThunder: TextView
    private lateinit var tvAverageThunder: TextView
    private lateinit var tvAverageCritThunder: TextView
    private lateinit var tvMaxThunder: TextView
    private lateinit var tvMaxCritThunder: TextView
    private lateinit var tvMinGfb: TextView
    private lateinit var tvMinCritGfb: TextView
    private lateinit var tvMaxCritGfb: TextView
    private lateinit var tvAverageGfb: TextView
    private lateinit var tvAverageCritGfb: TextView
    private lateinit var tvMaxGfb: TextView
    private lateinit var tvMinStShower: TextView
    private lateinit var tvMinCritStShower: TextView
    private lateinit var tvAverageStShower: TextView
    private lateinit var tvAverageCritStShower: TextView
    private lateinit var tvMaxStShower: TextView
    private lateinit var tvMaxCritStShower: TextView
    private lateinit var tvMinAva: TextView
    private lateinit var tvMinCritAva: TextView
    private lateinit var tvAverageAva: TextView
    private lateinit var tvAverageCritAva: TextView
    private lateinit var tvMaxAva: TextView
    private lateinit var tvMaxCritAva: TextView
    private lateinit var tvMaxMasSan: TextView
    private lateinit var tvMaxCritMasSan: TextView
    private lateinit var tvAverageMasSan: TextView
    private lateinit var tvMinMasSan: TextView
    private lateinit var tvAverageCritMasSan: TextView
    private lateinit var tvMinCritMasSan: TextView
    private lateinit var maxRpBasic: TextView
    private lateinit var critMaxDmg: TextView
    private lateinit var averageCritDmg: TextView
    private lateinit var averageRpBasic: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paladin_calc, container, false)

        viewById(view)
        dropMenuWeaponMode()
        dropMenuAttackMode()
        sanguineGreaves(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_rp_calcular)
        btnCalcular.setOnClickListener {
            dmgPaladin()
        }

        return view
    }

    private fun viewById(view: View) {
        maxRpBasic = view.findViewById(R.id.max_rp_basic)
        critMaxDmg = view.findViewById(R.id.max_rp_critc_basic)
        averageCritDmg = view.findViewById(R.id.average_crit_basic)
        averageRpBasic = view.findViewById(R.id.average_rp_basic)
        tvMaxMasSan = view.findViewById(R.id.max_mas_san)
        tvMaxCritMasSan = view.findViewById(R.id.max_crit_mas_san)
        tvAverageMasSan = view.findViewById(R.id.average_mas_san)
        tvAverageCritMasSan = view.findViewById(R.id.average_crit_mas_san)
        tvMinMasSan = view.findViewById(R.id.min_mas_san)
        tvMinCritMasSan = view.findViewById(R.id.min_crit_mas_san)
        tvMinAva = view.findViewById(R.id.min_avalanche_rune)
        tvMinCritAva = view.findViewById(R.id.min_crit_avalanche_rune)
        tvAverageAva = view.findViewById(R.id.average_avalanche_rune)
        tvAverageCritAva = view.findViewById(R.id.average_crit_avalanche_rune)
        tvMaxAva = view.findViewById(R.id.max_avalanche_rune)
        tvMaxCritAva = view.findViewById(R.id.max_crit_avalanche_rune)
        tvMinStShower = view.findViewById(R.id.min_stoneshower_rune)
        tvMinCritStShower = view.findViewById(R.id.min_crit_stoneshower_rune)
        tvAverageStShower = view.findViewById(R.id.average_stoneshower_rune)
        tvAverageCritStShower = view.findViewById(R.id.average_crit_stoneshower_rune)
        tvMaxStShower = view.findViewById(R.id.max_stoneshower_rune)
        tvMaxCritStShower = view.findViewById(R.id.max_crit_stoneshower_rune)
        tvMinGfb = view.findViewById(R.id.min_gfb_rune)
        tvMinCritGfb = view.findViewById(R.id.min_crit_gfb_rune)
        tvAverageGfb = view.findViewById(R.id.average_gfb_rune)
        tvAverageCritGfb = view.findViewById(R.id.average_crit_gfb_rune)
        tvMaxGfb = view.findViewById(R.id.max_gfb_rune)
        tvMaxCritGfb = view.findViewById(R.id.max_crit_gfb_rune)
        tvMinThunder = view.findViewById(R.id.min_thunderstorm_rune)
        tvMinCritThunder = view.findViewById(R.id.min_crit_thunderstorm_rune)
        tvAverageThunder = view.findViewById(R.id.average_thunderstorm_rune)
        tvAverageCritThunder = view.findViewById(R.id.average_crit_thunderstorm_rune)
        tvMaxThunder = view.findViewById(R.id.max_thunderstorm_rune)
        tvMaxCritThunder = view.findViewById(R.id.max_crit_thunderstorm_rune)
        level = view.findViewById(R.id.input_rp_level)
        magicLevel = view.findViewById(R.id.input_rp_magic_level)
        skills = view.findViewById(R.id.input_rp_skills)
        arrowAttack = view.findViewById(R.id.input_arrow_attack)
        bowAttack = view.findViewById(R.id.input_bow_attack)
        physicalResistance = view.findViewById(R.id.input_rp_creature_physical_resistence)
        iceResistance = view.findViewById(R.id.input_rp_creature_ice_resistence)
        earthResistance = view.findViewById(R.id.input_rp_creature_earth_resistence)
        energyResistance = view.findViewById(R.id.input_rp_creature_energy_resistence)
        fireResistance = view.findViewById(R.id.input_rp_creature_fire_resistence)
        holyResistance = view.findViewById(R.id.input_rp_creature_holy_resistence)
        armor = view.findViewById(R.id.input_rp_creature_armor)
        actWeaponModel = view.findViewById(R.id.weapon_rp_complete)
        actAttackMode = view.findViewById(R.id.attack_rp)
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

    private fun sanguineGreaves(view: View) {
        radioGroupSanguineGreaves = view.findViewById(R.id.sanguine_greaves)

        radioGroupSanguineGreaves.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yes_radio_button -> sanguineGreaves = 1
                    R.id.no_radio_button -> sanguineGreaves = 0
                }
            }
            when (sanguineGreaves) {
                1 -> Toast.makeText(
                    requireContext(),
                    "+8% damage on Exevo Mas San",
                    Toast.LENGTH_SHORT
                ).show()

                0 -> Toast.makeText(
                    requireContext(),
                    "No bonus on Exevo Mas San",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun dmgPaladin() {

        val levelInt = level.text.toString().toIntOrNull() ?: 0
        val magicLevelInt = magicLevel.text.toString().toIntOrNull() ?: 0
        val skillsInt = skills.text.toString().toIntOrNull() ?: 10
        val arrowAttackInt = arrowAttack.text.toString().toIntOrNull() ?: 0
        val bowAttackInt = bowAttack.text.toString().toIntOrNull() ?: 0
        val physicalResistanceInt = physicalResistance.text.toString().toIntOrNull() ?: 100
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100
        val holyResistanceInt = holyResistance.text.toString().toIntOrNull() ?: 100

        var armorInt = armor.text.toString().toIntOrNull() ?: 1
        if (armorInt > 130) {
            armorInt = 130
        }

        val calcArmor = armorInt * 0.75
        val formato = DecimalFormat("#")

        basicHit(
            levelInt,
            skillsInt,
            bowAttackInt,
            arrowAttackInt,
            calcArmor,
            physicalResistanceInt,
            formato
        )

        masSan(
            levelInt,
            magicLevelInt,
            holyResistanceInt,
            formato
        )

        avalancheDmg(
            levelInt,
            magicLevelInt,
            iceResistanceInt,
            formato
        )

        stoneShowerDmg(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            formato
        )

        thunderStormDmg(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            formato
        )

        greatFireBallDmg(
            levelInt,
            magicLevelInt,
            fireResistanceInt,
            formato
        )
    }

    private fun thunderStormDmg(
        levelInt: Int,
        magicLevelInt: Int,
        energyResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minBaseDmgThunder = (levelInt * 0.2) + (magicLevelInt * 1) + 6
        val minDmgThunder = (minBaseDmgThunder * energyResistanceInt) / 100.0
        val minCritThunder = when (weaponModel) {
            0 -> minDmgThunder + (minDmgThunder * 0.5)
            1 -> minDmgThunder + (minDmgThunder * 0.6)
            else -> {
                minDmgThunder + (minDmgThunder * 0.62)
            }
        }

        val maxBaseDmgThunder = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgThunder = (maxBaseDmgThunder * energyResistanceInt) / 100.0
        val maxCritThunder = when (weaponModel) {
            0 -> maxDmgThunder + (maxDmgThunder * 0.5)
            1 -> maxDmgThunder + (maxDmgThunder * 0.6)
            else -> {
                maxDmgThunder + (maxDmgThunder * 0.62)
            }
        }

        val averageThunder = (minDmgThunder + maxDmgThunder) / 2
        val averageCritThunder = (minCritThunder + maxCritThunder) / 2

        tvMinThunder.text = formato.format(minDmgThunder)
        tvMinCritThunder.text = formato.format(minCritThunder)
        tvAverageThunder.text = formato.format(averageThunder)
        tvAverageCritThunder.text = formato.format(averageCritThunder)
        tvMaxThunder.text = formato.format(maxDmgThunder)
        tvMaxCritThunder.text = formato.format(maxCritThunder)
    }

    private fun greatFireBallDmg(
        levelInt: Int,
        magicLevelInt: Int,
        fireResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minBaseDmgGfb = (levelInt * 0.2) + (magicLevelInt * 1.81) + 10
        val minDmgGfb = (minBaseDmgGfb * fireResistanceInt) / 100.0
        val minCritGfb = when (weaponModel) {
            0 -> minDmgGfb + (minDmgGfb * 0.5)
            1 -> minDmgGfb + (minDmgGfb * 0.6)
            else -> {
                minDmgGfb + (minDmgGfb * 0.62)
            }
        }

        val maxBaseDmgGfb = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgGfb = (maxBaseDmgGfb * fireResistanceInt) / 100.0
        val maxCritGfb = when (weaponModel) {
            0 -> maxDmgGfb + (maxDmgGfb * 0.5)
            1 -> maxDmgGfb + (maxDmgGfb * 0.6)
            else -> {
                maxDmgGfb + (maxDmgGfb * 0.62)
            }
        }

        val averageGfb = (minDmgGfb + maxDmgGfb) / 2
        val averageCritGfb = (minCritGfb + maxCritGfb) / 2

        tvMinGfb.text = formato.format(minDmgGfb)
        tvMinCritGfb.text = formato.format(minCritGfb)
        tvAverageGfb.text = formato.format(averageGfb)
        tvAverageCritGfb.text = formato.format(averageCritGfb)
        tvMaxGfb.text = formato.format(maxDmgGfb)
        tvMaxCritGfb.text = formato.format(maxCritGfb)
    }

    private fun stoneShowerDmg(
        levelInt: Int,
        magicLevelInt: Int,
        earthResistaceInt: Int,
        formato: DecimalFormat
    ) {
        val minBaseDmgStShower = (levelInt * 0.2) + (magicLevelInt * 1) + 6
        val minDmgStShower = (minBaseDmgStShower * earthResistaceInt) / 100.0
        val minCritStShower = when (weaponModel) {
            0 -> minDmgStShower + (minDmgStShower * 0.5)
            1 -> minDmgStShower + (minDmgStShower * 0.6)
            else -> {
                minDmgStShower + (minDmgStShower * 0.62)
            }
        }

        val maxBaseDmgStShower = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgStShower = (maxBaseDmgStShower * earthResistaceInt) / 100.0
        val maxCritStShower = when (weaponModel) {
            0 -> maxDmgStShower + (maxDmgStShower * 0.5)
            1 -> maxDmgStShower + (maxDmgStShower * 0.6)
            else -> {
                maxDmgStShower + (maxDmgStShower * 0.62)
            }
        }

        val averageStShower = (minDmgStShower + maxDmgStShower) / 2
        val averageCritStShower = (minCritStShower + maxCritStShower) / 2

        tvMinStShower.text = formato.format(minDmgStShower)
        tvMinCritStShower.text = formato.format(minCritStShower)
        tvAverageStShower.text = formato.format(averageStShower)
        tvAverageCritStShower.text = formato.format(averageCritStShower)
        tvMaxStShower.text = formato.format(maxDmgStShower)
        tvMaxCritStShower.text = formato.format(maxCritStShower)
    }

    private fun avalancheDmg(
        levelInt: Int,
        magicLevelInt: Int,
        iceResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minBaseDmgAva = (levelInt * 0.2) + (magicLevelInt * 1.81) + 10
        val minDmgAva = (minBaseDmgAva * iceResistanceInt) / 100.0
        val minCritAva = when (weaponModel) {
            0 -> minDmgAva + (minDmgAva * 0.5)
            1 -> minDmgAva + (minDmgAva * 0.6)
            else -> {
                minDmgAva + (minDmgAva * 0.62)
            }
        }

        val maxBaseDmgAva = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgAva = (maxBaseDmgAva * iceResistanceInt) / 100.0
        val maxCritAva = when (weaponModel) {
            0 -> maxDmgAva + (maxDmgAva * 0.5)
            1 -> maxDmgAva + (maxDmgAva * 0.6)
            else -> {
                maxDmgAva + (maxDmgAva * 0.62)
            }
        }

        val averageAva = (minDmgAva + maxDmgAva) / 2
        val averageCritAva = (minCritAva + maxCritAva) / 2

        tvMinAva.text = formato.format(minDmgAva)
        tvMinCritAva.text = formato.format(minCritAva)
        tvAverageAva.text = formato.format(averageAva)
        tvAverageCritAva.text = formato.format(averageCritAva)
        tvMaxAva.text = formato.format(maxDmgAva)
        tvMaxCritAva.text = formato.format(maxCritAva)
    }

    private fun masSan(
        levelInt: Int,
        magicLevelInt: Int,
        holyResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minDmgBase = (levelInt / 5) + (magicLevelInt * 4)
        val minMasSan = when (weaponModel) {
            2 -> minDmgBase + (minDmgBase * 0.08)
            3 -> minDmgBase + (minDmgBase * 0.15)
            else -> {
                minDmgBase
            }
        }
        val minMasSanDmg = (minMasSan.toDouble() * holyResistanceInt) / 100.0
        var minCritMasSanDmg = minMasSanDmg + (minMasSanDmg * 0.5)
        var minCritMasSanSoulWar = minMasSanDmg + (minMasSanDmg * 0.6)
        var minCritMasSanSanguine = minMasSanDmg + (minMasSanDmg * 0.62)

        val maxDmgBase = (levelInt / 5) + (magicLevelInt * 6)
        val maxMasSan = when (weaponModel) {
            2 -> maxDmgBase + (maxDmgBase * 0.08)
            3 -> maxDmgBase + (maxDmgBase * 0.15)
            else -> {
                maxDmgBase
            }
        }
        val maxMasSanDmg = (maxMasSan.toDouble() * holyResistanceInt) / 100.0
        var maxCritMasSanDmg = maxMasSanDmg + (maxMasSanDmg * 0.5)
        var maxCritMasSanSoulWar = maxMasSanDmg + (maxMasSanDmg * 0.6)
        var maxCritMasSanSanguine = maxMasSanDmg + (maxMasSanDmg * 0.62)

        val averageMasSan = (minMasSanDmg + maxMasSanDmg) / 2
        var averageCritMasSan = (minCritMasSanDmg + maxCritMasSanDmg) / 2
        var averageCritSoulWar = (minCritMasSanSoulWar + maxCritMasSanSoulWar) / 2
        var averageCritSanguine = (minCritMasSanSanguine + maxCritMasSanSanguine) / 2

        when (sanguineGreaves) {
            1 -> {
                minCritMasSanDmg = minMasSanDmg + (minMasSanDmg * 0.58)
                minCritMasSanSoulWar = minMasSanDmg + (minMasSanDmg * 0.68)
                minCritMasSanSanguine = minMasSanDmg + (minMasSanDmg * 0.70)

                averageCritMasSan = averageMasSan + (averageMasSan * 0.58)
                averageCritSoulWar = averageMasSan + (averageMasSan * 0.68)
                averageCritSanguine = averageMasSan + (averageMasSan * 0.70)

                maxCritMasSanDmg = maxMasSanDmg + (maxMasSanDmg * 0.58)
                maxCritMasSanSoulWar = maxMasSanDmg + (maxMasSanDmg * 0.68)
                maxCritMasSanSanguine = maxMasSanDmg + (maxMasSanDmg * 0.70)
            }
        }

        tvMaxMasSan.text = formato.format(maxMasSanDmg)
        tvAverageMasSan.text = formato.format(averageMasSan)
        tvMinMasSan.text = formato.format(minMasSanDmg)

        when (weaponModel) {
            0 -> {
                tvMaxCritMasSan.text = formato.format(maxCritMasSanDmg)
                tvAverageCritMasSan.text = formato.format(averageCritMasSan)
                tvMinCritMasSan.text = formato.format(minCritMasSanDmg)
            }

            1 -> {
                tvMaxCritMasSan.text = formato.format(maxCritMasSanSoulWar)
                tvAverageCritMasSan.text = formato.format(averageCritSoulWar)
                tvMinCritMasSan.text = formato.format(minCritMasSanSoulWar)
            }

            else -> {
                tvMaxCritMasSan.text = formato.format(maxCritMasSanSanguine)
                tvAverageCritMasSan.text = formato.format(averageCritSanguine)
                tvMinCritMasSan.text = formato.format(minCritMasSanSanguine)
            }
        }
    }

    private fun basicHit(
        levelInt: Int,
        skillsInt: Int,
        bowAttackInt: Int,
        arrowAttackInt: Int,
        calcArmor: Double,
        physicalResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minDmgBasic = levelInt / 5
        val maxDmgBasic =
            (0.09 * attackMode * skillsInt * (bowAttackInt + arrowAttackInt)) + minDmgBasic
        val maxDmgBasicAtkWithArmor = maxDmgBasic - calcArmor
        val maxTotalDmgBasic = (maxDmgBasicAtkWithArmor * physicalResistanceInt) / 100

        maxRpBasic.text = formato.format(maxTotalDmgBasic)

        val maxDmgCritBasicAtk = maxTotalDmgBasic + (maxTotalDmgBasic * 0.5)
        val maxDmgCritBasicAtkSoulWar = maxTotalDmgBasic + (maxTotalDmgBasic * 0.6)
        val maxDmgCritBasicAtkSanguine = maxTotalDmgBasic + (maxTotalDmgBasic * 0.62)

        when (weaponModel) {
            0 -> critMaxDmg.text = formato.format(maxDmgCritBasicAtk)
            1 -> critMaxDmg.text = formato.format(maxDmgCritBasicAtkSoulWar)
            else -> critMaxDmg.text = formato.format(maxDmgCritBasicAtkSanguine)
        }

        val averageBasicHit = maxTotalDmgBasic / 2
        averageRpBasic.text = formato.format(averageBasicHit)

        val averageDmgCritBasicAtk = averageBasicHit + (averageBasicHit * 0.5)
        val averageDmgCritBasicAtkSoulWar = averageBasicHit + (averageBasicHit * 0.6)
        val averageDmgCritBasicAtkSanguine = averageBasicHit + (averageBasicHit * 0.62)

        when (weaponModel) {
            0 -> averageCritDmg.text = formato.format(averageDmgCritBasicAtk)
            1 -> averageCritDmg.text = formato.format(averageDmgCritBasicAtkSoulWar)
            else -> averageCritDmg.text = formato.format(averageDmgCritBasicAtkSanguine)
        }
    }
}

