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


class DruidCalcFragment : Fragment() {

    private lateinit var radioGroupSanguineGaloshes: RadioGroup
    private var weaponModel = 0
    private var sanguineGaloshes = 0

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
    private lateinit var tvMinMasFrigo: TextView
    private lateinit var tvMinCritMasFrigo: TextView
    private lateinit var tvAverageMasFrigo: TextView
    private lateinit var tvAverageCritMasFrigo: TextView
    private lateinit var tvMaxMasFrigo: TextView
    private lateinit var tvMaxCritMasFrigo: TextView
    private lateinit var tvMinMasTera: TextView
    private lateinit var tvMinCritMasTera: TextView
    private lateinit var tvAverageMasTera: TextView
    private lateinit var tvAverageCritMasTera: TextView
    private lateinit var tvMaxMasTera: TextView
    private lateinit var tvMaxCritMasTera: TextView
    private lateinit var tvMinTeraHur: TextView
    private lateinit var tvMinCritTeraHur: TextView
    private lateinit var tvAverageTeraHur: TextView
    private lateinit var tvAverageCritTeraHur: TextView
    private lateinit var tvMaxTeraHur: TextView
    private lateinit var tvMaxCritTeraHur: TextView
    private lateinit var tvMinThunder: TextView
    private lateinit var tvMinCritThunder: TextView
    private lateinit var tvAverageThunder: TextView
    private lateinit var tvAverageCritThunder: TextView
    private lateinit var tvMaxThunder: TextView
    private lateinit var tvMaxCritThunder: TextView
    private lateinit var tvMinGfb: TextView
    private lateinit var tvMinCritGfb: TextView
    private lateinit var tvAverageGfb: TextView
    private lateinit var tvAverageCritGfb: TextView
    private lateinit var tvMaxGfb: TextView
    private lateinit var tvMaxCritGfb: TextView
    private lateinit var tvMinCritAva: TextView
    private lateinit var tvMinAva: TextView
    private lateinit var tvAverageAva: TextView
    private lateinit var tvAverageCritAva: TextView
    private lateinit var tvMaxAva: TextView
    private lateinit var tvMaxCritAva: TextView
    private lateinit var tvMinStShower: TextView
    private lateinit var tvMinCritStShower: TextView
    private lateinit var tvAverageStShower: TextView
    private lateinit var tvAverageCritStShower: TextView
    private lateinit var tvMaxStShower: TextView
    private lateinit var tvMaxCritStShower: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_druid_calc, container, false)

        viewsById(view)
        dropMenuWeaponMode()
        sanguineGaloshes(view)

        val btnCalcular = view.findViewById<AppCompatButton>(R.id.btn_ed_calcular)
        btnCalcular.setOnClickListener {
            dmgDruid()
        }

        return view
    }

    private fun viewsById(view: View) {
        tvMinStShower = view.findViewById(R.id.min_ed_stoneshower_rune)
        tvMinCritStShower = view.findViewById(R.id.min_crit_ed_stoneshower_rune)
        tvAverageStShower = view.findViewById(R.id.average_ed_stoneshower_rune)
        tvAverageCritStShower = view.findViewById(R.id.average_crit_ed_stoneshower_rune)
        tvMaxStShower = view.findViewById(R.id.max_ed_stoneshower_rune)
        tvMaxCritStShower = view.findViewById(R.id.max_crit_ed_stoneshower_rune)
        tvMinAva = view.findViewById(R.id.min_ed_avalanche_rune)
        tvMinCritAva = view.findViewById(R.id.min_crit_ed_avalanche_rune)
        tvAverageAva = view.findViewById(R.id.average_ed_avalanche_rune)
        tvAverageCritAva = view.findViewById(R.id.average_crit_ed_avalanche_rune)
        tvMaxAva = view.findViewById(R.id.max_ed_avalanche_rune)
        tvMaxCritAva = view.findViewById(R.id.max_crit_ed_avalanche_rune)
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
        tvMinTeraHur = view.findViewById(R.id.min_tera_hur)
        tvMinCritTeraHur = view.findViewById(R.id.min_crit_tera_hur)
        tvAverageTeraHur = view.findViewById(R.id.average_tera_hur)
        tvAverageCritTeraHur = view.findViewById(R.id.average_crit_tera_hur)
        tvMaxTeraHur = view.findViewById(R.id.max_tera_hur)
        tvMaxCritTeraHur = view.findViewById(R.id.max_crit_tera_hur)
        tvMinMasTera = view.findViewById(R.id.min_mas_tera)
        tvMinCritMasTera = view.findViewById(R.id.min_crit_mas_tera)
        tvAverageMasTera = view.findViewById(R.id.average_mas_tera)
        tvAverageCritMasTera = view.findViewById(R.id.average_crit_mas_tera)
        tvMaxMasTera = view.findViewById(R.id.max_mas_tera)
        tvMaxCritMasTera = view.findViewById(R.id.max_crit_mas_tera)
        actWeaponModel = view.findViewById(R.id.weapon_ed_complete)
        level = view.findViewById(R.id.input_ed_level)
        magicLevel = view.findViewById(R.id.input_ed_magic_level)
        iceResistance = view.findViewById(R.id.input_ed_creature_ice_resistence)
        earthResistance = view.findViewById(R.id.input_ed_creature_earth_resistence)
        energyResistance = view.findViewById(R.id.input_ed_creature_energy_resistence)
        deathResistance = view.findViewById(R.id.input_ed_creature_death_resistence)
        fireResistance = view.findViewById(R.id.input_ed_creature_fire_resistence)
        tvMinSd = view.findViewById(R.id.min_ed_sd_rune)
        tvMinCritSd = view.findViewById(R.id.min_crit_ed_sd_rune)
        tvAverageSd = view.findViewById(R.id.average_ed_sd_rune)
        tvAverageCritSd = view.findViewById(R.id.average_crit_ed_sd_rune)
        tvMaxSd = view.findViewById(R.id.max_ed_sd_rune)
        tvMaxCritSd = view.findViewById(R.id.max_crit_ed_sd_rune)
        tvMinMasFrigo = view.findViewById(R.id.min_mas_frigo)
        tvMinCritMasFrigo = view.findViewById(R.id.min_crit_mas_frigo)
        tvAverageMasFrigo = view.findViewById(R.id.average_mas_frigo)
        tvAverageCritMasFrigo = view.findViewById(R.id.average_crit_mas_frigo)
        tvMaxMasFrigo = view.findViewById(R.id.max_mas_frigo)
        tvMaxCritMasFrigo = view.findViewById(R.id.max_crit_mas_frigo)
    }

    private fun sanguineGaloshes(view: View) {
        radioGroupSanguineGaloshes = view.findViewById(R.id.sanguine_galoshes)

        radioGroupSanguineGaloshes.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)

            if (selectedRadioButton != null) {
                when (selectedRadioButton.id) {
                    R.id.yes_radio_button -> sanguineGaloshes = 1
                    R.id.no_radio_button -> sanguineGaloshes = 0
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

    private fun dropMenuWeaponMode() {

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

        teraHur(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            formato
        )


        granMasTera(
            levelInt,
            magicLevelInt,
            earthResistanceInt,
            formato
        )

        granMasFrigo(
            levelInt,
            magicLevelInt,
            iceResistanceInt,
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

        tvMinAva.text = formato.format(minDmgAva)
        tvMinCritAva.text = formato.format(minCritAva)
        tvAverageAva.text = formato.format(averageAva)
        tvAverageCritAva.text = formato.format(averageCritAva)
        tvMaxAva.text = formato.format(maxDmgAva)
        tvMaxCritAva.text = formato.format(maxCritAva)
    }
}
