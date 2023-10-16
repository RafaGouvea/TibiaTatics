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


class SorcererCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineGaloshes: RadioGroup
    private var weaponModel = 0
    private var sanguineBoots = 0
    private lateinit var actWeaponModel: AutoCompleteTextView
    private lateinit var level: TextInputEditText
    private lateinit var magicLevel: TextInputEditText
    private lateinit var iceResistance: TextInputEditText
    private lateinit var earthResistance: TextInputEditText
    private lateinit var energyResistance: TextInputEditText
    private lateinit var deathResistance: TextInputEditText
    private lateinit var fireResistance: TextInputEditText
    private lateinit var tvMinSd: TextView
    private lateinit var tvMinCritSd: TextView
    private lateinit var tvAverageSd: TextView
    private lateinit var tvAverageCritSd: TextView
    private lateinit var tvMaxSd: TextView
    private lateinit var tvMaxCritSd: TextView
    private lateinit var tvMinMasFlam: TextView
    private lateinit var tvMinCritMasFlam: TextView
    private lateinit var tvAverageMasFlam: TextView
    private lateinit var tvMaxMasFlam: TextView
    private lateinit var tvAverageCritMasFlam: TextView
    private lateinit var tvMaxCritMasFlam: TextView
    private lateinit var tvMinMasVis: TextView
    private lateinit var tvAverageCritMasVis: TextView
    private lateinit var tvMinCritMasVis: TextView
    private lateinit var tvAverageMasVis: TextView
    private lateinit var tvMaxMasVis: TextView
    private lateinit var tvMaxCritMasVis: TextView
    private lateinit var tvMinVisHur: TextView
    private lateinit var tvMinCritVisHur: TextView
    private lateinit var tvAverageVisHur: TextView
    private lateinit var tvAverageCritVisHur: TextView
    private lateinit var tvMaxVisHur: TextView
    private lateinit var tvMaxCritVisHur: TextView
    private lateinit var tvMinThunder: TextView
    private lateinit var tvMinCritThunder: TextView
    private lateinit var tvAverageThunder: TextView
    private lateinit var tvAverageCritThunder: TextView
    private lateinit var tvMaxCritThunder: TextView
    private lateinit var tvMaxThunder: TextView
    private lateinit var tvMinGfb: TextView
    private lateinit var tvMinCritGfb: TextView
    private lateinit var tvAverageGfb: TextView
    private lateinit var tvAverageCritGfb: TextView
    private lateinit var tvMaxGfb: TextView
    private lateinit var tvMaxCritGfb: TextView
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sorcerer_calc, container, false)

        viewById(view)
        dropMenuWeaponMode()
        sanguineGaloshes(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_ms_calcular)
        btnCalcular.setOnClickListener {
            dmgDruid()
        }

        return view
    }

    private fun viewById(view: View) {
        tvMinAva = view.findViewById(R.id.min_ed_avalanche_rune)
        tvMinCritAva = view.findViewById(R.id.min_crit_ed_avalanche_rune)
        tvAverageAva = view.findViewById(R.id.average_ed_avalanche_rune)
        tvAverageCritAva = view.findViewById(R.id.average_crit_ed_avalanche_rune)
        tvMaxAva = view.findViewById(R.id.max_ed_avalanche_rune)
        tvMaxCritAva = view.findViewById(R.id.max_crit_ed_avalanche_rune)
        tvMinStShower = view.findViewById(R.id.min_ed_stoneshower_rune)
        tvMinCritStShower = view.findViewById(R.id.min_crit_ed_stoneshower_rune)
        tvAverageStShower = view.findViewById(R.id.average_ed_stoneshower_rune)
        tvAverageCritStShower = view.findViewById(R.id.average_crit_ed_stoneshower_rune)
        tvMaxStShower = view.findViewById(R.id.max_ed_stoneshower_rune)
        tvMaxCritStShower = view.findViewById(R.id.max_crit_ed_stoneshower_rune)
        tvMinGfb = view.findViewById(R.id.min_ed_gfb_rune)
        tvMinCritGfb = view.findViewById(R.id.min_crit_ed_gfb_rune)
        tvAverageGfb = view.findViewById(R.id.average_ed_gfb_rune)
        tvAverageCritGfb = view.findViewById(R.id.average_ed_crit_gfb_rune)
        tvMaxGfb = view.findViewById(R.id.max_ed_gfb_rune)
        tvMaxCritGfb = view.findViewById(R.id.max_crit_ed_gfb_rune)
        tvMinThunder = view.findViewById(R.id.min_ed_thunderstorm_rune)
        tvMinCritThunder = view.findViewById(R.id.min_crit_ed_thunderstorm_rune)
        tvAverageThunder = view.findViewById(R.id.average_ed_thunderstorm_rune)
        tvAverageCritThunder = view.findViewById(R.id.average_crit_ed_thunderstorm_rune)
        tvMaxThunder = view.findViewById(R.id.max_ed_thunderstorm_rune)
        tvMaxCritThunder = view.findViewById(R.id.max_crit_ed_thunderstorm_rune)
        tvMinVisHur = view.findViewById(R.id.min_vis_hur)
        tvMinCritVisHur = view.findViewById(R.id.min_crit_vis_hur)
        tvAverageVisHur = view.findViewById(R.id.average_vis_hur)
        tvAverageCritVisHur = view.findViewById(R.id.average_crit_vis_hur)
        tvMaxVisHur = view.findViewById(R.id.max_vis_hur)
        tvMaxCritVisHur = view.findViewById(R.id.max_crit_vis_hur)
        tvMinMasVis = view.findViewById(R.id.min_mas_vis)
        tvMinCritMasVis = view.findViewById(R.id.min_crit_mas_vis)
        tvAverageMasVis = view.findViewById(R.id.average_mas_vis)
        tvAverageCritMasVis = view.findViewById(R.id.average_crit_mas_vis)
        tvMaxMasVis = view.findViewById(R.id.max_mas_vis)
        tvMaxCritMasVis = view.findViewById(R.id.max_crit_mas_vis)
        tvMinMasFlam = view.findViewById(R.id.min_mas_flam)
        tvMinCritMasFlam = view.findViewById(R.id.min_crit_mas_flam)
        tvAverageMasFlam = view.findViewById(R.id.average_mas_flam)
        tvAverageCritMasFlam = view.findViewById(R.id.average_crit_mas_flam)
        tvMaxMasFlam = view.findViewById(R.id.max_mas_flam)
        tvMaxCritMasFlam = view.findViewById(R.id.max_crit_mas_flam)
        tvMinSd = view.findViewById(R.id.min_ed_sd_rune)
        tvMinCritSd = view.findViewById(R.id.min_crit_ed_sd_rune)
        tvAverageSd = view.findViewById(R.id.average_ed_sd_rune)
        tvAverageCritSd = view.findViewById(R.id.average_crit_ed_sd_rune)
        tvMaxSd = view.findViewById(R.id.max_ed_sd_rune)
        tvMaxCritSd = view.findViewById(R.id.max_crit_ed_sd_rune)
        level = view.findViewById(R.id.input_ms_level)
        magicLevel = view.findViewById(R.id.input_ms_magic_level)
        iceResistance = view.findViewById(R.id.input_ms_creature_ice_resistence)
        earthResistance = view.findViewById(R.id.input_ms_creature_earth_resistence)
        energyResistance = view.findViewById(R.id.input_ms_creature_energy_resistence)
        deathResistance = view.findViewById(R.id.input_ms_creature_death_resistence)
        fireResistance = view.findViewById(R.id.input_ms_creature_fire_resistence)
        actWeaponModel = view.findViewById(R.id.weapon_ms_complete)
    }

    private fun sanguineGaloshes(view: View) {
        radioGroupSanguineGaloshes = view.findViewById(R.id.sanguine_boots)

        radioGroupSanguineGaloshes.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yes_radio_button -> sanguineBoots = 1
                    R.id.no_radio_button -> sanguineBoots = 0
                }
            }
            when (sanguineBoots) {
                1 -> Toast.makeText(
                    requireContext(),
                    "+8% damage on Exevo Vis Hur & Exevo Gran Mas Flam",
                    Toast.LENGTH_SHORT
                ).show()

                0 -> Toast.makeText(
                    requireContext(),
                    "No bonus on Exevo Vis Hur & Exevo Gran Mas Flam",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun dropMenuWeaponMode() {
        val listElementalType =
            arrayOf(
                "Normal Rod",
                "Cobra Wand",
                "Soultainter",
                "Sanguine Coil",
                "Grand Sanguine Coil",
            )

        val weaponModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actWeaponModel.setAdapter(weaponModelAdapter)

        actWeaponModel.setText("Normal Wand", false)
        actWeaponModel.setOnClickListener {
            actWeaponModel.showDropDown()
        }

        actWeaponModel.setOnItemClickListener { _, _, position, _ ->
            val weaponModelValue = when (listElementalType[position]) {
                "Normal Rod" -> 0
                "Cobra Wand" -> 1
                "Soultainter" -> 2
                "Sanguine Coil" -> 3
                "Grand Sanguine Coil" -> 4
                else -> 0
            }
            weaponModel = weaponModelValue
        }
    }

    private fun dmgDruid() {

        val levelInt = level.text.toString().toIntOrNull() ?: 0
        val magicLevelInt = magicLevel.text.toString().toIntOrNull() ?: 0
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100
        val deathResistanceInt = deathResistance.text.toString().toIntOrNull() ?: 100
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100
        val formato = DecimalFormat("#")

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

        visHur(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            formato
        )


        granMasVis(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            formato
        )

        granMasFlam(
            levelInt,
            magicLevelInt,
            fireResistanceInt,
            formato
        )

        sdRune(
            levelInt,
            magicLevelInt,
            deathResistanceInt,
            formato
        )
    }

    private fun sdRune(
        levelInt: Int,
        magicLevelInt: Int,
        deathResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minBaseDmgSd = (levelInt * 0.2) + (magicLevelInt * 4.605) + 28
        val minDmgSd = (minBaseDmgSd * deathResistanceInt) / 100.0
        val minCritSd = when (weaponModel) {
            0 -> minDmgSd + (minDmgSd * 0.5)
            1 -> minDmgSd + (minDmgSd * 0.35)
            2 -> minDmgSd + (minDmgSd * 0.60)
            else -> {
                minDmgSd + (minDmgSd * 0.62)
            }
        }

        val maxBaseDmgSd = (levelInt * 0.2) + (magicLevelInt * 7.395) + 46
        val maxDmgSd = (maxBaseDmgSd * deathResistanceInt) / 100.0
        val maxCritSd = when (weaponModel) {
            0 -> maxDmgSd + (maxDmgSd * 0.5)
            1 -> maxDmgSd + (maxDmgSd * 0.35)
            2 -> minDmgSd + (minDmgSd * 0.60)
            else -> {
                maxDmgSd + (maxDmgSd * 0.62)
            }
        }
        val averageSd = (minDmgSd + maxDmgSd) / 2
        val averageCritSd = (minCritSd + maxCritSd) / 2

        tvMinSd.text = formato.format(minDmgSd)
        tvMinCritSd.text = formato.format(minCritSd)
        tvAverageSd.text = formato.format(averageSd)
        tvAverageCritSd.text = formato.format(averageCritSd)
        tvMaxSd.text = formato.format(maxDmgSd)
        tvMaxCritSd.text = formato.format(maxCritSd)
    }


    private fun granMasFlam(
        levelInt: Int,
        magicLevelInt: Int,
        fireResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minMasFlamDmgBase = (levelInt / 5) + (magicLevelInt * 7.0)
        val minMasFlamSanguine = when (weaponModel) {
            3 -> minMasFlamDmgBase + (minMasFlamDmgBase * 0.08)
            4 -> minMasFlamDmgBase + (minMasFlamDmgBase * 0.15)
            else -> {
                minMasFlamDmgBase
            }
        }
        val minMasFlam = (minMasFlamSanguine * fireResistanceInt) / 100.0
        var minCritMasFlam = minMasFlam + (minMasFlam * 0.5)
        var minCritCobraWand = minMasFlam + (minMasFlam * 0.35)
        var minCritSoultainter = minMasFlam + (minMasFlam * 0.60)
        var minCritSanguine = minMasFlam + (minMasFlam * 0.62)


        val maxMasFlamDmgBase = (levelInt / 5) + (magicLevelInt * 14.0)
        val maxMasFlamSanguine = when (weaponModel) {
            3 -> maxMasFlamDmgBase + (maxMasFlamDmgBase * 0.08)
            4 -> maxMasFlamDmgBase + (maxMasFlamDmgBase * 0.15)
            else -> {
                maxMasFlamDmgBase
            }
        }
        val maxMasFlam = (maxMasFlamSanguine * fireResistanceInt) / 100.0
        var maxCritMasFlam = maxMasFlam + (maxMasFlam * 0.5)
        var maxCritCobraWand = maxMasFlam + (maxMasFlam * 0.35)
        var maxCritSoultainter = maxMasFlam + (maxMasFlam * 0.60)
        var maxCritSanguine = maxMasFlam + (maxMasFlam * 0.62)

        val averageMasFlam = (minMasFlam + maxMasFlam) / 2
        var averageCritMasFrigo = (minCritMasFlam + maxCritMasFlam) / 2
        var averageCritCobraWand = (minCritCobraWand + maxCritCobraWand) / 2
        var averageCritSoultainter = (minCritSoultainter + maxCritSoultainter) / 2
        var averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        when (sanguineBoots) {
            1 -> {
                minCritMasFlam = minMasFlam + (minMasFlam * 0.58)
                minCritCobraWand = minMasFlam + (minMasFlam * 0.43)
                minCritSoultainter = minMasFlam + (minMasFlam * 0.68)
                minCritSanguine = minMasFlam + (minMasFlam * 0.70)

                averageCritMasFrigo = averageMasFlam + (averageMasFlam * 0.58)
                averageCritCobraWand = averageMasFlam + (averageMasFlam * 0.43)
                averageCritSoultainter = averageMasFlam + (averageMasFlam * 0.68)
                averageCritSanguine = averageMasFlam + (averageMasFlam * 0.70)

                maxCritMasFlam = maxMasFlam + (maxMasFlam * 0.58)
                maxCritCobraWand = maxMasFlam + (maxMasFlam * 0.43)
                maxCritSoultainter = maxMasFlam + (maxMasFlam * 0.68)
                maxCritSanguine = maxMasFlam + (maxMasFlam * 0.70)

            }
        }

        tvMinMasFlam.text = formato.format(minMasFlam)
        tvAverageMasFlam.text = formato.format(averageMasFlam)
        tvMaxMasFlam.text = formato.format(maxMasFlam)

        when (weaponModel) {
            0 -> {
                tvMinCritMasFlam.text = formato.format(minCritMasFlam)
                tvAverageCritMasFlam.text = formato.format(averageCritMasFrigo)
                tvMaxCritMasFlam.text = formato.format(maxCritMasFlam)
            }

            1 -> {
                tvMinCritMasFlam.text = formato.format(minCritCobraWand)
                tvAverageCritMasFlam.text = formato.format(averageCritCobraWand)
                tvMaxCritMasFlam.text = formato.format(maxCritCobraWand)
            }

            2 -> {
                tvMinCritMasFlam.text = formato.format(minCritSoultainter)
                tvAverageCritMasFlam.text = formato.format(averageCritSoultainter)
                tvMaxCritMasFlam.text = formato.format(maxCritSoultainter)
            }

            else -> {
                tvMinCritMasFlam.text = formato.format(minCritSanguine)
                tvAverageCritMasFlam.text = formato.format(averageCritSanguine)
                tvMaxCritMasFlam.text = formato.format(maxCritSanguine)
            }
        }
    }

    private fun granMasVis(
        levelInt: Int,
        magicLevelInt: Int,
        energyResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minMasVisDmgBase = (levelInt / 5) + (magicLevelInt * 5.0)

        val minMasVis = (minMasVisDmgBase * energyResistanceInt) / 100.0
        val minCritMasVis = minMasVis + (minMasVis * 0.5)
        val minCritCobraWand = minMasVis + (minMasVis * 0.35)
        val minCritSoultainter = minMasVis + (minMasVis * 0.60)
        val minCritSanguine = minMasVis + (minMasVis * 0.62)


        val maxMasVisDmgBase = (levelInt / 5) + (magicLevelInt * 12.0)

        val maxMasVis = (maxMasVisDmgBase * energyResistanceInt) / 100.0
        val maxCritMasVis = maxMasVis + (maxMasVis * 0.5)
        val maxCritCobraWand = maxMasVis + (maxMasVis * 0.35)
        val maxCritSoultainter = maxMasVis + (maxMasVis * 0.60)
        val maxCritSanguine = maxMasVis + (maxMasVis * 0.62)

        val averageMasVis = (minMasVis + maxMasVis) / 2
        val averageCritMasVis = (minCritMasVis + maxCritMasVis) / 2
        val averageCritCobraWand = (minCritCobraWand + maxCritCobraWand) / 2
        val averageCritSoultainter = (minCritSoultainter + maxCritSoultainter) / 2
        val averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        tvMinMasVis.text = formato.format(minMasVis)
        tvAverageMasVis.text = formato.format(averageMasVis)
        tvMaxMasVis.text = formato.format(maxMasVis)

        when (weaponModel) {
            0 -> {
                tvMinCritMasVis.text = formato.format(minCritMasVis)
                tvAverageCritMasVis.text = formato.format(averageCritMasVis)
                tvMaxCritMasVis.text = formato.format(maxCritMasVis)
            }

            1 -> {
                tvMinCritMasVis.text = formato.format(minCritCobraWand)
                tvAverageCritMasVis.text = formato.format(averageCritCobraWand)
                tvMaxCritMasVis.text = formato.format(maxCritCobraWand)
            }

            2 -> {
                tvMinCritMasVis.text = formato.format(minCritSoultainter)
                tvAverageCritMasVis.text = formato.format(averageCritSoultainter)
                tvMaxCritMasVis.text = formato.format(maxCritSoultainter)
            }

            else -> {
                tvMinCritMasVis.text = formato.format(minCritSanguine)
                tvAverageCritMasVis.text = formato.format(averageCritSanguine)
                tvMaxCritMasVis.text = formato.format(maxCritSanguine)
            }
        }
    }

    private fun visHur(
        levelInt: Int,
        magicLevelInt: Int,
        energyResistanceInt: Int,
        formato: DecimalFormat
    ) {
        val minVisHurDmgBase = (levelInt / 5) + (magicLevelInt * 4.5)
        val minVisHurSanguine = when (weaponModel) {
            2 -> minVisHurDmgBase + (minVisHurDmgBase * 0.08)
            3 -> minVisHurDmgBase + (minVisHurDmgBase * 0.15)
            else -> {
                minVisHurDmgBase
            }
        }
        val minVisHur = (minVisHurSanguine * energyResistanceInt) / 100.0
        var minCritVisHur = minVisHur + (minVisHur * 0.5)
        var minCritCobraWand = minVisHur + (minVisHur * 0.35)
        var minCritSoultainter = minVisHur + (minVisHur * 0.60)
        var minCritSanguine = minVisHur + (minVisHur * 0.62)


        val maxVisHurDmgBase = (levelInt / 5) + (magicLevelInt * 9).toDouble()
        val maxVisHurSanguine = when (weaponModel) {
            2 -> maxVisHurDmgBase + (maxVisHurDmgBase * 0.08)
            3 -> maxVisHurDmgBase + (maxVisHurDmgBase * 0.15)
            else -> {
                maxVisHurDmgBase
            }
        }
        val maxVisHur = (maxVisHurSanguine * energyResistanceInt) / 100.0
        var maxCritVisHur = maxVisHur + (maxVisHur * 0.5)
        var maxCritCobraWand = maxVisHur + (maxVisHur * 0.35)
        var maxCritSoultainter = maxVisHur + (maxVisHur * 0.60)
        var maxCritSanguine = maxVisHur + (maxVisHur * 0.62)

        val averageVisHur = (minVisHur + maxVisHur) / 2
        var averageCritVisHur = (minCritVisHur + maxCritVisHur) / 2
        var averageCritCobraWand = (minCritCobraWand + maxCritCobraWand) / 2
        var averageCritSoultainter = (minCritSoultainter + maxCritSoultainter) / 2
        var averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        when (sanguineBoots) {
            1 -> {
                minCritVisHur = minVisHur + (minVisHur * 0.58)
                minCritCobraWand = minVisHur + (minVisHur * 0.43)
                minCritSoultainter = minVisHur + (minVisHur * 0.68)
                minCritSanguine = minVisHur + (minVisHur * 0.70)

                averageCritVisHur = averageVisHur + (averageVisHur * 0.58)
                averageCritCobraWand = averageVisHur + (averageVisHur * 0.43)
                averageCritSoultainter = averageVisHur + (averageVisHur * 0.68)
                averageCritSanguine = averageVisHur + (averageVisHur * 0.70)

                maxCritVisHur = maxVisHur + (maxVisHur * 0.58)
                maxCritCobraWand = maxVisHur + (maxVisHur * 0.43)
                maxCritSoultainter = maxVisHur + (maxVisHur * 0.68)
                maxCritSanguine = maxVisHur + (maxVisHur * 0.70)

            }
        }

        tvMinVisHur.text = formato.format(minVisHur)
        tvAverageVisHur.text = formato.format(averageVisHur)
        tvMaxVisHur.text = formato.format(maxVisHur)

        when (weaponModel) {
            0 -> {
                tvMinCritVisHur.text = formato.format(minCritVisHur)
                tvAverageCritVisHur.text = formato.format(averageCritVisHur)
                tvMaxCritVisHur.text = formato.format(maxCritVisHur)
            }

            1 -> {
                tvMinCritVisHur.text = formato.format(minCritCobraWand)
                tvAverageCritVisHur.text = formato.format(averageCritCobraWand)
                tvMaxCritVisHur.text = formato.format(maxCritCobraWand)
            }

            2 -> {
                tvMinCritVisHur.text = formato.format(minCritSoultainter)
                tvAverageCritVisHur.text = formato.format(averageCritSoultainter)
                tvMaxCritVisHur.text = formato.format(maxCritSoultainter)
            }

            else -> {
                tvMinCritVisHur.text = formato.format(minCritSanguine)
                tvAverageCritVisHur.text = formato.format(averageCritSanguine)
                tvMaxCritVisHur.text = formato.format(maxCritSanguine)
            }
        }
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
            1 -> minDmgThunder + (minDmgThunder * 0.35)
            2 -> minDmgThunder + (minDmgThunder * 0.60)
            else -> {
                minDmgThunder + (minDmgThunder * 0.62)
            }
        }

        val maxBaseDmgThunder = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgThunder = (maxBaseDmgThunder * energyResistanceInt) / 100.0
        val maxCritThunder = when (weaponModel) {
            0 -> maxDmgThunder + (maxDmgThunder * 0.5)
            1 -> maxDmgThunder + (maxDmgThunder * 0.35)
            2 -> maxDmgThunder + (maxDmgThunder * 0.60)
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
            1 -> minDmgGfb + (minDmgGfb * 0.35)
            2 -> minDmgGfb + (minDmgGfb * 0.60)
            else -> {
                minDmgGfb + (minDmgGfb * 0.62)
            }
        }

        val maxBaseDmgGfb = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgGfb = (maxBaseDmgGfb * fireResistanceInt) / 100.0
        val maxCritGfb = when (weaponModel) {
            0 -> maxDmgGfb + (maxDmgGfb * 0.5)
            1 -> maxDmgGfb + (maxDmgGfb * 0.35)
            2 -> maxDmgGfb + (maxDmgGfb * 0.60)
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
            1 -> minDmgStShower + (minDmgStShower * 0.35)
            2 -> minDmgStShower + (minDmgStShower * 0.60)
            else -> {
                minDmgStShower + (minDmgStShower * 0.62)
            }
        }

        val maxBaseDmgStShower = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgStShower = (maxBaseDmgStShower * earthResistaceInt) / 100.0
        val maxCritStShower = when (weaponModel) {
            0 -> maxDmgStShower + (maxDmgStShower * 0.5)
            1 -> maxDmgStShower + (maxDmgStShower * 0.35)
            2 -> maxDmgStShower + (maxDmgStShower * 0.60)
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
            1 -> minDmgAva + (minDmgAva * 0.35)
            2 -> minDmgAva + (minDmgAva * 0.60)
            else -> {
                minDmgAva + (minDmgAva * 0.62)
            }
        }

        val maxBaseDmgAva = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgAva = (maxBaseDmgAva * iceResistanceInt) / 100.0
        val maxCritAva = when (weaponModel) {
            0 -> maxDmgAva + (maxDmgAva * 0.5)
            1 -> maxDmgAva + (maxDmgAva * 0.35)
            2 -> maxDmgAva + (maxDmgAva * 0.60)
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
}
