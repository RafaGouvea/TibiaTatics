package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.ui.adapter.RankingAdapter
import com.tatics.tibiatatics.ui.viewmodel.HighscoreViewModel
import kotlinx.coroutines.launch


class HighscoreFragment : Fragment() {

    private val viewModel by viewModels<HighscoreViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var rankingAdapter: RankingAdapter
    private var world = "all"
    private var category = "experience"
    private var vocation = "all"
    private var page = 1
    private lateinit var btnSearch: AppCompatButton
    private lateinit var actCategoryRank: AutoCompleteTextView
    private lateinit var actVocationRank: AutoCompleteTextView
    private lateinit var tvInputPageBottom: AutoCompleteTextView
    private lateinit var tvInputPageTop: AutoCompleteTextView
    private lateinit var actWorldRank: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscore, container, false)

        viewById(view)
        rankAdapter(view)
        dropMenuCategoryRank()
        dropMenuVocationRank()
        dropdMenuWorldRank()
        setupPageInputs(view)
        loadCharacterRank()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getRank(world, category, vocation, page)
            viewModel.loadWorlds()
        }
    }

    private fun viewById(view: View) {
        btnSearch = view.findViewById(R.id.btn_search_highscore)
        actCategoryRank = view.findViewById(R.id.tv_input_category)
        actVocationRank = view.findViewById(R.id.tv_input_vocation)
        tvInputPageBottom = view.findViewById(R.id.tv_input_page_bottom)
        tvInputPageTop = view.findViewById(R.id.tv_input_page_top)
        actWorldRank = view.findViewById(R.id.tv_input_world)
    }

    private fun loadCharacterRank() {
        viewModel.rankLiveData.observe(viewLifecycleOwner) { rankModel ->
            rankModel?.highscores?.highscore_list?.let { listHighscore ->
                rankingAdapter.updateList(listHighscore)
            }
        }

        setDetailRank()
    }

    private fun setDetailRank() {
        btnSearch.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getRank(world, category, vocation, page)
            }
        }
    }

    private fun rankAdapter(view: View) {
        this.rankingAdapter = RankingAdapter()
        recyclerView = view.findViewById(R.id.ranking_character_by)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.rankingAdapter
    }

    private fun dropMenuCategoryRank() {

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

    private fun dropMenuVocationRank() {
        val vocationMap = mapOf(
            "Master Sorcerer" to "sorcerer",
            "Elder Druid" to "druid",
            "Royal Paladin" to "paladin",
            "Elite Knight" to "knight"
        )

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
                tvInputPageBottom.setText(selectedPage, false)
            } else if (inputId == R.id.tv_input_page_bottom) {
                tvInputPageTop.setText(selectedPage, false)
            }
        }

        actPageRank.setText(page.toString(), false)
        actPageRank.setOnClickListener {
            actPageRank.showDropDown()
        }
    }

    private fun updateRankingList() {
        lifecycleScope.launch {
            viewModel.getRank(world, category, vocation, page)
        }
    }

    private fun setupPageInputs(view: View) {
        setupPageInput(view, R.id.tv_input_page_top)
        setupPageInput(view, R.id.tv_input_page_bottom)
    }

    private fun dropdMenuWorldRank() {
        viewModel.worldsModelLiveData.observe(viewLifecycleOwner) { loadWorlds ->
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
