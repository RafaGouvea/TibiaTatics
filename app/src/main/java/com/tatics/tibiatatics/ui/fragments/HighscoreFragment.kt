package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.RankingAdapter
import kotlinx.coroutines.launch


class HighscoreFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var rankingAdapter: RankingAdapter
    private var world = "all"
    private var category = "experience"
    private var vocation = "all"
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscore, container, false)

        rankAdapter(view)
        dropMenuCategoryRank(view)
        dropMenuVocationRank(view)
        dropdMenuWorldRank(view)
        setupPageInputs(view)




        lifecycleScope.launch {
            val characterRank = newsModelWebClient.getRank(world, category, vocation, page)
            characterRank?.let {
                rankingAdapter.updateList(it.highscores.highscore_list)
            }
            setDetailRank(view)
        }
        return view
    }

    private suspend fun setDetailRank(view: View) {
        val btnSearch = view.findViewById<AppCompatButton>(R.id.btn_search_highscore)
        btnSearch.setOnClickListener {
            lifecycleScope.launch {
                val characterRank = newsModelWebClient.getRank(world, category, vocation, page)
                characterRank?.let {
                    rankingAdapter.updateList(it.highscores.highscore_list)
                }
            }
        }
    }

    private fun rankAdapter(view: View) {
        this.rankingAdapter = RankingAdapter()
        recyclerView = view.findViewById(R.id.ranking_character_by)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.rankingAdapter
    }

    private fun dropMenuCategoryRank(view: View) {

        val categoryMap = mapOf(
            "Axe Fighting" to "axefighting",
            "Club Fighting" to "clubfighting",
            "Sword Fighting" to "swordfighting",
            "Fist Fighting" to "fistfighting",
            "Distance Fighting" to "distancefighting",
            "Magic Level" to "magiclevel",
            "Shielding" to "shielding",
            "Fishing" to "fishing",
            "Boss Points" to "bosspoints",
            "Charm Points" to "charmpoints",
            "Drome Score" to "dromescore",
            "Experience" to "experience",
            "Goshnar's Taint" to "goshnarstaint",
            "Loyalty Points" to "loyaltypoints",
            "Achievements" to "achievements"
        )

        val actCategoryRank: AutoCompleteTextView =
            view.findViewById(R.id.tv_input_category)
        val listCategoryRank =
            arrayOf(
                "Axe Fighting",
                "Club Fighting",
                "Sword Fighting",
                "Fist Fighting",
                "Distance Fighting",
                "Magic Level",
                "Shielding",
                "Fishing",
                "Achievements",
                "Boss Points",
                "Charm Points",
                "Drome Score",
                "Experience",
                "Goshnar's Taint",
                "Loyalty Points"
            )

        val categoryModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listCategoryRank
        )
        actCategoryRank.setAdapter(categoryModelAdapter)

        actCategoryRank.setOnItemClickListener { _, _, position, _ ->
            val selectedCategory = listCategoryRank[position]
            category = categoryMap[selectedCategory] ?: "experiencepoints"
        }

        actCategoryRank.setText("Experience", false)
        actCategoryRank.setOnClickListener {
            actCategoryRank.showDropDown()
        }
    }

    private fun dropMenuVocationRank(view: View) {

        val vocationMap = mapOf(
            "Master Sorcerer" to "sorcerer",
            "Elder Druid" to "druid",
            "Royal Paladin" to "paladin",
            "Elite Knight" to "knight"
        )

        val actVocationRank: AutoCompleteTextView =
            view.findViewById(R.id.tv_input_vocation)
        val listVocationRank =
            arrayOf(
                "All",
                "Master Sorcerer",
                "Elder Druid",
                "Royal Paladin",
                "Elite Knight"
            )

        val vocationModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listVocationRank
        )
        actVocationRank.setAdapter(vocationModelAdapter)

        actVocationRank.setOnItemClickListener { _, _, position, _ ->
            val selectedVocation = listVocationRank[position]
            vocation = vocationMap[selectedVocation] ?: "All"
        }

        actVocationRank.setText("All", false)
        actVocationRank.setOnClickListener {
            actVocationRank.showDropDown()
        }
    }

    private fun setupPageInput(view: View, inputId: Int) {
        val actPageRank: AutoCompleteTextView = view.findViewById(inputId)
        val listVocationRank = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val pageModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listVocationRank
        )
        actPageRank.setAdapter(pageModelAdapter)

        actPageRank.setOnItemClickListener { _, _, position, _ ->
            val selectedPage = listVocationRank[position]
            page = selectedPage.toInt()
            updateRankingList()

            if (inputId == R.id.tv_input_page_top) {
                view.findViewById<AutoCompleteTextView>(R.id.tv_input_page_bottom).setText(selectedPage, false)
            } else if (inputId == R.id.tv_input_page_bottom) {
                view.findViewById<AutoCompleteTextView>(R.id.tv_input_page_top).setText(selectedPage, false)
            }
        }

        actPageRank.setText(page.toString(), false)
        actPageRank.setOnClickListener {
            actPageRank.showDropDown()
        }
    }

    private fun updateRankingList() {
        lifecycleScope.launch {
            val pageRank = newsModelWebClient.getRank(world, category, vocation, page)
            pageRank?.let {
                rankingAdapter.updateList(it.highscores.highscore_list)
            }
        }
    }

    private fun setupPageInputs(view: View) {
        setupPageInput(view, R.id.tv_input_page_top)
        setupPageInput(view, R.id.tv_input_page_bottom)
    }

    private fun dropdMenuWorldRank(view: View) {

        val actWorldRank = view.findViewById<AutoCompleteTextView>(R.id.tv_input_world)

        lifecycleScope.launch {
            val loadWorlds = newsModelWebClient.loadWorlds()
            loadWorlds?.let { worldsStatusModel ->
                val listWorldsRank = mutableListOf<String>()
                listWorldsRank.add("All")
                listWorldsRank.addAll(worldsStatusModel.regular_worlds.map { it.name })

                val worldsModelAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listWorldsRank
                )

                actWorldRank.setOnItemClickListener { _, _, position, _ ->
                    world = listWorldsRank[position].lowercase()
                }

                actWorldRank.setAdapter(worldsModelAdapter)
                actWorldRank.setOnClickListener {
                    actWorldRank.showDropDown()
                }
            }
        }
    }
}
