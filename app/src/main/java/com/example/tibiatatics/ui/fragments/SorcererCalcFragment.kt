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


class SorcererCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineGaloshes: RadioGroup
    private var weaponModel = 0
    private var sanguineBoots = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sorcerer_calc, container, false)

        dropMenuWeaponMode(view)
        sanguineGaloshes(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_ms_calcular)
        btnCalcular.setOnClickListener {
            dmgDruid(view)
        }


        return view
    }

    private fun sanguineGaloshes(view: View) {
        radioGroupSanguineGaloshes = view.findViewById(R.id.sanguine_boots)

        radioGroupSanguineGaloshes.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yesRadioButton -> sanguineBoots = 1
                    R.id.noRadioButton -> sanguineBoots = 0
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

    private fun dropMenuWeaponMode(view: View) {

        val actWeaponModel: AutoCompleteTextView =
            view.findViewById(R.id.weapon_ms_complete)
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

    private fun dmgDruid(view: View) {

        val level = view.findViewById<TextInputEditText>(R.id.input_ms_level)
        val levelInt = level.text.toString().toIntOrNull() ?: 0

        val magicLevel = view.findViewById<TextInputEditText>(R.id.input_ms_magic_level)
        val magicLevelInt = magicLevel.text.toString().toIntOrNull() ?: 0

        val iceResistance =
            view.findViewById<TextInputEditText>(R.id.input_ms_creature_ice_resistence)
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100

        val earthResistance =
            view.findViewById<TextInputEditText>(R.id.input_ms_creature_earth_resistence)
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100

        val energyResistance =
            view.findViewById<TextInputEditText>(R.id.input_ms_creature_energy_resistence)
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100

        val deathResistance = view.findViewById<TextInputEditText>(R.id.input_ms_creature_death_resistence)
        val deathResistanceInt = deathResistance.text.toString().toIntOrNull() ?: 100

        val fireResistance =
            view.findViewById<TextInputEditText>(R.id.input_ms_creature_fire_resistence)
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100

        val formato = DecimalFormat("#")

        avalancheDmg(
            levelInt,
            magicLevelInt,
            iceResistanceInt,
            view,
            formato
        )

        stoneShowerDmg(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            view,
            formato
        )

        thunderStormDmg(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            view,
            formato
        )

        greatFireBallDmg(
            levelInt,
            magicLevelInt,
            fireResistanceInt,
            view,
            formato
        )

        visHur(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            view,
            formato)


        granMasVis(
            levelInt,
            magicLevelInt,
            energyResistanceInt,
            view,
            formato)

        granMasFlam(levelInt,
            magicLevelInt,
            fireResistanceInt,
            view,
            formato)

        sdRune(
            levelInt,
            magicLevelInt,
            deathResistanceInt,
            view,
            formato)

    }

    private fun sdRune(
        levelInt: Int,
        magicLevelInt: Int,
        deathResistanceInt: Int,
        view: View,
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

        val tvMinSd = view.findViewById<TextView>(R.id.min_ed_sd_rune)
        val tvMinCritSd = view.findViewById<TextView>(R.id.min_crit_ed_sd_rune)
        val tvAverageSd = view.findViewById<TextView>(R.id.average_ed_sd_rune)
        val tvAverageCritSd = view.findViewById<TextView>(R.id.average_crit_ed_sd_rune)
        val tvMaxSd = view.findViewById<TextView>(R.id.max_ed_sd_rune)
        val tvMaxCritSd = view.findViewById<TextView>(R.id.max_crit_ed_sd_rune)

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
        view: View,
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

        val tvMinMasFlam = view.findViewById<TextView>(R.id.min_mas_flam)
        val tvMinCritMasFlam = view.findViewById<TextView>(R.id.min_crit_mas_flam)
        val tvAverageMasFlam = view.findViewById<TextView>(R.id.average_mas_flam)
        val tvAverageCritMasFlam = view.findViewById<TextView>(R.id.average_crit_mas_flam)
        val tvMaxMasFlam = view.findViewById<TextView>(R.id.max_mas_flam)
        val tvMaxCritMasFlam = view.findViewById<TextView>(R.id.max_crit_mas_flam)

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
        view: View,
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

        val tvMinMasVis = view.findViewById<TextView>(R.id.min_mas_vis)
        val tvMinCritMasVis = view.findViewById<TextView>(R.id.min_crit_mas_vis)
        val tvAverageMasVis = view.findViewById<TextView>(R.id.average_mas_vis)
        val tvAverageCritMasVis = view.findViewById<TextView>(R.id.average_crit_mas_vis)
        val tvMaxMasVis = view.findViewById<TextView>(R.id.max_mas_vis)
        val tvMaxCritMasVis = view.findViewById<TextView>(R.id.max_crit_mas_vis)

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
        view: View,
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

        val tvMinVisHur = view.findViewById<TextView>(R.id.min_vis_hur)
        val tvMinCritVisHur = view.findViewById<TextView>(R.id.min_crit_vis_hur)
        val tvAverageVisHur = view.findViewById<TextView>(R.id.average_vis_hur)
        val tvAverageCritVisHur = view.findViewById<TextView>(R.id.average_crit_vis_hur)
        val tvMaxVisHur = view.findViewById<TextView>(R.id.max_vis_hur)
        val tvMaxCritVisHur = view.findViewById<TextView>(R.id.max_crit_vis_hur)

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
        view: View,
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

        val tvMinThunder = view.findViewById<TextView>(R.id.min_ed_thunderstorm_rune)
        val tvMinCritThunder = view.findViewById<TextView>(R.id.min_crit_ed_thunderstorm_rune)
        val tvAverageThunder = view.findViewById<TextView>(R.id.average_ed_thunderstorm_rune)
        val tvAverageCritThunder = view.findViewById<TextView>(R.id.average_crit_ed_thunderstorm_rune)
        val tvMaxThunder = view.findViewById<TextView>(R.id.max_ed_thunderstorm_rune)
        val tvMaxCritThunder = view.findViewById<TextView>(R.id.max_crit_ed_thunderstorm_rune)

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
        view: View,
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

        val tvMinGfb = view.findViewById<TextView>(R.id.min_ed_gfb_rune)
        val tvMinCritGfb = view.findViewById<TextView>(R.id.min_crit_ed_gfb_rune)
        val tvAverageGfb = view.findViewById<TextView>(R.id.average_ed_gfb_rune)
        val tvAverageCritGfb = view.findViewById<TextView>(R.id.average_ed_crit_gfb_rune)
        val tvMaxGfb = view.findViewById<TextView>(R.id.max_ed_gfb_rune)
        val tvMaxCritGfb = view.findViewById<TextView>(R.id.max_crit_ed_gfb_rune)

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
        view: View,
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

        val tvMinStShower = view.findViewById<TextView>(R.id.min_ed_stoneshower_rune)
        val tvMinCritStShower = view.findViewById<TextView>(R.id.min_crit_ed_stoneshower_rune)
        val tvAverageStShower = view.findViewById<TextView>(R.id.average_ed_stoneshower_rune)
        val tvAverageCritStShower = view.findViewById<TextView>(R.id.average_crit_ed_stoneshower_rune)
        val tvMaxStShower = view.findViewById<TextView>(R.id.max_ed_stoneshower_rune)
        val tvMaxCritStShower = view.findViewById<TextView>(R.id.max_crit_ed_stoneshower_rune)

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
        view: View,
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

        val tvMinAva = view.findViewById<TextView>(R.id.min_ed_avalanche_rune)
        val tvMinCritAva = view.findViewById<TextView>(R.id.min_crit_ed_avalanche_rune)
        val tvAverageAva = view.findViewById<TextView>(R.id.average_ed_avalanche_rune)
        val tvAverageCritAva = view.findViewById<TextView>(R.id.average_crit_ed_avalanche_rune)
        val tvMaxAva = view.findViewById<TextView>(R.id.max_ed_avalanche_rune)
        val tvMaxCritAva = view.findViewById<TextView>(R.id.max_crit_ed_avalanche_rune)

        tvMinAva.text = formato.format(minDmgAva)
        tvMinCritAva.text = formato.format(minCritAva)
        tvAverageAva.text = formato.format(averageAva)
        tvAverageCritAva.text = formato.format(averageCritAva)
        tvMaxAva.text = formato.format(maxDmgAva)
        tvMaxCritAva.text = formato.format(maxCritAva)
    }


}