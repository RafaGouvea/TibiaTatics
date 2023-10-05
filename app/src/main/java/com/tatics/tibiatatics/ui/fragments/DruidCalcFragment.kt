package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
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


class DruidCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineGaloshes: RadioGroup
    private var weaponModel = 0
    private var sanguineGaloshes = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_druid_calc, container, false)

        dropMenuWeaponMode(view)
        sanguineGaloshes(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_ed_calcular)
        btnCalcular.setOnClickListener {
            dmgDruid(view)
        }

        return view
    }

    private fun sanguineGaloshes(view: View) {
        radioGroupSanguineGaloshes = view.findViewById(R.id.sanguine_galoshes)

        radioGroupSanguineGaloshes.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yesRadioButton -> sanguineGaloshes = 1
                    R.id.noRadioButton -> sanguineGaloshes = 0
                }
            }
            when (sanguineGaloshes) {
                1 -> Toast.makeText(
                    requireContext(),
                    "+8% damage on Exevo Tera Hur & Exevo Gran Mas Frigo",
                    Toast.LENGTH_SHORT
                ).show()

                0 -> Toast.makeText(
                    requireContext(),
                    "No bonus on Exevo Tera Hur & Exevo Gran Mas Frigo",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun dropMenuWeaponMode(view: View) {

        val actWeaponModel: AutoCompleteTextView =
            view.findViewById(R.id.weapon_ed_complete)
        val listElementalType =
            arrayOf(
                "Normal Rod",
                "Lion Rod",
                "Sanguine Rod",
                "Grand Sanguine Rod",
            )

        val weaponModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listElementalType
        )
        actWeaponModel.setAdapter(weaponModelAdapter)

        actWeaponModel.setText("Normal Rod", false)
        actWeaponModel.setOnClickListener {
            actWeaponModel.showDropDown()
        }

        actWeaponModel.setOnItemClickListener { _, _, position, _ ->
            val weaponModelValue = when (listElementalType[position]) {
                "Normal Rod" -> 0
                "Lion Rod" -> 1
                "Sanguine Rod" -> 2
                "Grand Sanguine Rod" -> 3
                else -> 0
            }
            weaponModel = weaponModelValue
        }
    }

    private fun dmgDruid(view: View) {

        val level = view.findViewById<TextInputEditText>(R.id.input_ed_level)
        val levelInt = level.text.toString().toIntOrNull() ?: 0

        val magicLevel = view.findViewById<TextInputEditText>(R.id.input_ed_magic_level)
        val magicLevelInt = magicLevel.text.toString().toIntOrNull() ?: 0

        val iceResistance =
            view.findViewById<TextInputEditText>(R.id.input_ed_creature_ice_resistence)
        val iceResistanceInt = iceResistance.text.toString().toIntOrNull() ?: 100

        val earthResistance =
            view.findViewById<TextInputEditText>(R.id.input_ed_creature_earth_resistence)
        val earthResistanceInt = earthResistance.text.toString().toIntOrNull() ?: 100

        val energyResistance =
            view.findViewById<TextInputEditText>(R.id.input_ed_creature_energy_resistence)
        val energyResistanceInt = energyResistance.text.toString().toIntOrNull() ?: 100

        val deathResistance = view.findViewById<TextInputEditText>(R.id.input_ed_creature_death_resistence)
        val deathResistanceInt = deathResistance.text.toString().toIntOrNull() ?: 100

        val fireResistance =
            view.findViewById<TextInputEditText>(R.id.input_ed_creature_fire_resistence)
        val fireResistanceInt = fireResistance.text.toString().toIntOrNull() ?: 100

        val formato = DecimalFormat("#")

        val x = formato.format(deathResistanceInt)
        Log.i("###", "dmgDruid: $x")

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

        teraHur(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            view,
            formato)


        granMasTera(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            view,
            formato)

        granMasFrigo(levelInt,
            magicLevelInt,
            iceResistanceInt,
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
            else -> {
                minDmgSd + (minDmgSd * 0.55)
            }
        }

        val maxBaseDmgSd = (levelInt * 0.2) + (magicLevelInt * 7.395) + 46
        val maxDmgSd = (maxBaseDmgSd * deathResistanceInt) / 100.0
        val maxCritSd = when (weaponModel) {
            0 -> maxDmgSd + (maxDmgSd * 0.5)
            1 -> maxDmgSd + (maxDmgSd * 0.35)
            else -> {
                maxDmgSd + (maxDmgSd * 0.55)
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


    private fun granMasFrigo(
        levelInt: Int,
        magicLevelInt: Int,
        iceResistanceInt: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val minMasFrigoDmgBase = (levelInt / 5) + (magicLevelInt * 6.0)
        val minMasFrigoSanguine = when (weaponModel) {
            2 -> minMasFrigoDmgBase + (minMasFrigoDmgBase * 0.08)
            3 -> minMasFrigoDmgBase + (minMasFrigoDmgBase * 0.15)
            else -> {
                minMasFrigoDmgBase
            }
        }
        val minMasFrigo = (minMasFrigoSanguine * iceResistanceInt) / 100.0
        var minCritMasFrigo = minMasFrigo + (minMasFrigo * 0.5)
        var minCritLionRod = minMasFrigo + (minMasFrigo * 0.35)
        var minCritSanguine = minMasFrigo + (minMasFrigo * 0.55)


        val maxMasFrigoDmgBase = (levelInt / 5) + (magicLevelInt * 12.0)
        val maxTeraHurSanguine = when (weaponModel) {
            2 -> maxMasFrigoDmgBase + (maxMasFrigoDmgBase * 0.08)
            3 -> maxMasFrigoDmgBase + (maxMasFrigoDmgBase * 0.15)
            else -> {
                maxMasFrigoDmgBase
            }
        }
        val maxMasFrigo = (maxTeraHurSanguine * iceResistanceInt) / 100.0
        var maxCritMasFrigo = maxMasFrigo + (maxMasFrigo * 0.5)
        var maxCritLionRod = maxMasFrigo + (maxMasFrigo * 0.35)
        var maxCritSanguine = maxMasFrigo + (maxMasFrigo * 0.55)

        val averageMasFrigo = (minMasFrigo + maxMasFrigo) / 2
        var averageCritMasFrigo = (minCritMasFrigo + maxCritMasFrigo) / 2
        var averageCritLionRod = (minCritLionRod + maxCritLionRod) / 2
        var averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        when (sanguineGaloshes) {
            1 -> {
                minCritMasFrigo = minMasFrigo + (minMasFrigo * 0.58)
                minCritLionRod = minMasFrigo + (minMasFrigo * 0.43)
                minCritSanguine = minMasFrigo + (minMasFrigo * 0.63)

                averageCritMasFrigo = averageMasFrigo + (averageMasFrigo * 0.58)
                averageCritLionRod = averageMasFrigo + (averageMasFrigo * 0.43)
                averageCritSanguine = averageMasFrigo + (averageMasFrigo * 0.63)

                maxCritMasFrigo = maxMasFrigo + (maxMasFrigo * 0.58)
                maxCritLionRod = maxMasFrigo + (maxMasFrigo * 0.43)
                maxCritSanguine = maxMasFrigo + (maxMasFrigo * 0.63)

            }
        }

        val tvMinMasFrigo = view.findViewById<TextView>(R.id.min_mas_frigo)
        val tvMinCritMasFrigo = view.findViewById<TextView>(R.id.min_crit_mas_frigo)
        val tvAverageMasFrigo = view.findViewById<TextView>(R.id.average_mas_frigo)
        val tvAverageCritMasFrigo = view.findViewById<TextView>(R.id.average_crit_mas_frigo)
        val tvMaxMasFrigo = view.findViewById<TextView>(R.id.max_mas_frigo)
        val tvMaxCritMasFrigo = view.findViewById<TextView>(R.id.max_crit_mas_frigo)

        tvMinMasFrigo.text = formato.format(minMasFrigo)
        tvAverageMasFrigo.text = formato.format(averageMasFrigo)
        tvMaxMasFrigo.text = formato.format(maxMasFrigo)

        when (weaponModel) {
            0 -> {
                tvMinCritMasFrigo.text = formato.format(minCritMasFrigo)
                tvAverageCritMasFrigo.text = formato.format(averageCritMasFrigo)
                tvMaxCritMasFrigo.text = formato.format(maxCritMasFrigo)
            }

            1 -> {
                tvMinCritMasFrigo.text = formato.format(minCritLionRod)
                tvAverageCritMasFrigo.text = formato.format(averageCritLionRod)
                tvMaxCritMasFrigo.text = formato.format(maxCritLionRod)
            }

            else -> {
                tvMinCritMasFrigo.text = formato.format(minCritSanguine)
                tvAverageCritMasFrigo.text = formato.format(averageCritSanguine)
                tvMaxCritMasFrigo.text = formato.format(maxCritSanguine)
            }
        }
    }

    private fun granMasTera(
        levelInt: Int,
        magicLevelInt: Int,
        earthResistanceInt: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val minMasTeraDmgBase = (levelInt / 5) + (magicLevelInt * 5.0)
        val minMasTeraSanguine = when (weaponModel) {
            2 -> minMasTeraDmgBase + (minMasTeraDmgBase * 0.08)
            3 -> minMasTeraDmgBase + (minMasTeraDmgBase * 0.15)
            else -> {
                minMasTeraDmgBase
            }
        }
        val minMasTera = (minMasTeraSanguine * earthResistanceInt) / 100.0
        val minCritMasTera = minMasTera + (minMasTera * 0.5)
        val minCritLionRod = minMasTera + (minMasTera * 0.35)
        val minCritSanguine = minMasTera + (minMasTera * 0.55)


        val maxMasTeraDmgBase = (levelInt / 5) + (magicLevelInt * 10.0)
        val maxTeraHurSanguine = when (weaponModel) {
            2 -> maxMasTeraDmgBase + (maxMasTeraDmgBase * 0.08)
            3 -> maxMasTeraDmgBase + (maxMasTeraDmgBase * 0.15)
            else -> {
                maxMasTeraDmgBase
            }
        }
        val maxMasTera = (maxTeraHurSanguine * earthResistanceInt) / 100.0
        val maxCritMasTera = maxMasTera + (maxMasTera * 0.5)
        val maxCritLionRod = maxMasTera + (maxMasTera * 0.35)
        val maxCritSanguine = maxMasTera + (maxMasTera * 0.55)

        val averageMasTera = (minMasTera + maxMasTera) / 2
        val averageCritMasTera = (minCritMasTera + maxCritMasTera) / 2
        val averageCritLionRod = (minCritLionRod + maxCritLionRod) / 2
        val averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        val tvMinMasTera = view.findViewById<TextView>(R.id.min_mas_tera)
        val tvMinCritMasTera = view.findViewById<TextView>(R.id.min_crit_mas_tera)
        val tvAverageMasTera = view.findViewById<TextView>(R.id.average_mas_tera)
        val tvAverageCritMasTera = view.findViewById<TextView>(R.id.average_crit_mas_tera)
        val tvMaxMasTera = view.findViewById<TextView>(R.id.max_mas_tera)
        val tvMaxCritMasTera = view.findViewById<TextView>(R.id.max_crit_mas_tera)

        tvMinMasTera.text = formato.format(minMasTera)
        tvAverageMasTera.text = formato.format(averageMasTera)
        tvMaxMasTera.text = formato.format(maxMasTera)

        when (weaponModel) {
            0 -> {
                tvMinCritMasTera.text = formato.format(minCritMasTera)
                tvAverageCritMasTera.text = formato.format(averageCritMasTera)
                tvMaxCritMasTera.text = formato.format(maxCritMasTera)
            }

            1 -> {
                tvMinCritMasTera.text = formato.format(minCritLionRod)
                tvAverageCritMasTera.text = formato.format(averageCritLionRod)
                tvMaxCritMasTera.text = formato.format(maxCritLionRod)
            }

            else -> {
                tvMinCritMasTera.text = formato.format(minCritSanguine)
                tvAverageCritMasTera.text = formato.format(averageCritSanguine)
                tvMaxCritMasTera.text = formato.format(maxCritSanguine)
            }
        }
    }

    private fun teraHur(
        levelInt: Int,
        magicLevelInt: Int,
        earthResistanceInt: Int,
        view: View,
        formato: DecimalFormat
    ) {
        val minTeraHurDmgBase = (levelInt / 5) + (magicLevelInt * 3.5)
        val minTeraHurSanguine = when (weaponModel) {
            2 -> minTeraHurDmgBase + (minTeraHurDmgBase * 0.08)
            3 -> minTeraHurDmgBase + (minTeraHurDmgBase * 0.15)
            else -> {
                minTeraHurDmgBase
            }
        }
        val minTeraHur = (minTeraHurSanguine * earthResistanceInt) / 100.0
        var minCritTeraHur = minTeraHur + (minTeraHur * 0.5)
        var minCritLionRod = minTeraHur + (minTeraHur * 0.35)
        var minCritSanguine = minTeraHur + (minTeraHur * 0.55)


        val maxTeraHurDmgBase = (levelInt / 5) + (magicLevelInt * 7).toDouble()
        val maxTeraHurSanguine = when (weaponModel) {
            2 -> maxTeraHurDmgBase + (maxTeraHurDmgBase * 0.08)
            3 -> maxTeraHurDmgBase + (maxTeraHurDmgBase * 0.15)
            else -> {
                maxTeraHurDmgBase
            }
        }
        val maxTeraHur = (maxTeraHurSanguine * earthResistanceInt) / 100.0
        var maxCritTeraHur = maxTeraHur + (maxTeraHur * 0.5)
        var maxCritLionRod = maxTeraHur + (maxTeraHur * 0.35)
        var maxCritSanguine = maxTeraHur + (maxTeraHur * 0.55)

        val averageTeraHur = (minTeraHur + maxTeraHur) / 2
        var averageCritTeraHur = (minCritTeraHur + maxCritTeraHur) / 2
        var averageCritLionRod = (minCritLionRod + maxCritLionRod) / 2
        var averageCritSanguine = (minCritSanguine + maxCritSanguine) / 2

        when (sanguineGaloshes) {
            1 -> {
                minCritTeraHur = minTeraHur + (minTeraHur * 0.58)
                minCritLionRod = minTeraHur + (minTeraHur * 0.43)
                minCritSanguine = minTeraHur + (minTeraHur * 0.63)

                averageCritTeraHur = averageTeraHur + (averageTeraHur * 0.58)
                averageCritLionRod = averageTeraHur + (averageTeraHur * 0.43)
                averageCritSanguine = averageTeraHur + (averageTeraHur * 0.63)

                maxCritTeraHur = maxTeraHur + (maxTeraHur * 0.58)
                maxCritLionRod = maxTeraHur + (maxTeraHur * 0.43)
                maxCritSanguine = maxTeraHur + (maxTeraHur * 0.63)

            }
        }

        val tvMinTeraHur = view.findViewById<TextView>(R.id.min_tera_hur)
        val tvMinCritTeraHur = view.findViewById<TextView>(R.id.min_crit_tera_hur)
        val tvAverageTeraHur = view.findViewById<TextView>(R.id.average_tera_hur)
        val tvAverageCritTeraHur = view.findViewById<TextView>(R.id.average_crit_tera_hur)
        val tvMaxTeraHur = view.findViewById<TextView>(R.id.max_tera_hur)
        val tvMaxCritTeraHur = view.findViewById<TextView>(R.id.max_crit_tera_hur)

        tvMinTeraHur.text = formato.format(minTeraHur)
        tvAverageTeraHur.text = formato.format(averageTeraHur)
        tvMaxTeraHur.text = formato.format(maxTeraHur)

        when (weaponModel) {
            0 -> {
                tvMinCritTeraHur.text = formato.format(minCritTeraHur)
                tvAverageCritTeraHur.text = formato.format(averageCritTeraHur)
                tvMaxCritTeraHur.text = formato.format(maxCritTeraHur)
            }

            1 -> {
                tvMinCritTeraHur.text = formato.format(minCritLionRod)
                tvAverageCritTeraHur.text = formato.format(averageCritLionRod)
                tvMaxCritTeraHur.text = formato.format(maxCritLionRod)
            }

            else -> {
                tvMinCritTeraHur.text = formato.format(minCritSanguine)
                tvAverageCritTeraHur.text = formato.format(averageCritSanguine)
                tvMaxCritTeraHur.text = formato.format(maxCritSanguine)
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
            else -> {
                minDmgThunder + (minDmgThunder * 0.55)
            }
        }

        val maxBaseDmgThunder = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgThunder = (maxBaseDmgThunder * energyResistanceInt) / 100.0
        val maxCritThunder = when (weaponModel) {
            0 -> maxDmgThunder + (maxDmgThunder * 0.5)
            1 -> maxDmgThunder + (maxDmgThunder * 0.35)
            else -> {
                maxDmgThunder + (maxDmgThunder * 0.55)
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
            else -> {
                minDmgGfb + (minDmgGfb * 0.55)
            }
        }

        val maxBaseDmgGfb = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgGfb = (maxBaseDmgGfb * fireResistanceInt) / 100.0
        val maxCritGfb = when (weaponModel) {
            0 -> maxDmgGfb + (maxDmgGfb * 0.5)
            1 -> maxDmgGfb + (maxDmgGfb * 0.35)
            else -> {
                maxDmgGfb + (maxDmgGfb * 0.55)
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
            else -> {
                minDmgStShower + (minDmgStShower * 0.55)
            }
        }

        val maxBaseDmgStShower = (levelInt * 0.2) + (magicLevelInt * 2.6) + 16
        val maxDmgStShower = (maxBaseDmgStShower * earthResistaceInt) / 100.0
        val maxCritStShower = when (weaponModel) {
            0 -> maxDmgStShower + (maxDmgStShower * 0.5)
            1 -> maxDmgStShower + (maxDmgStShower * 0.35)
            else -> {
                maxDmgStShower + (maxDmgStShower * 0.55)
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
            else -> {
                minDmgAva + (minDmgAva * 0.55)
            }
        }

        val maxBaseDmgAva = (levelInt * 0.2) + (magicLevelInt * 3) + 18
        val maxDmgAva = (maxBaseDmgAva * iceResistanceInt) / 100.0
        val maxCritAva = when (weaponModel) {
            0 -> maxDmgAva + (maxDmgAva * 0.5)
            1 -> maxDmgAva + (maxDmgAva * 0.35)
            else -> {
                maxDmgAva + (maxDmgAva * 0.55)
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