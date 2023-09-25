package com.example.tibiatatics.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.WebClient
import com.example.tibiatatics.ui.adapter.CharacterAchievimentsAdapter
import com.example.tibiatatics.ui.adapter.CharacterDeathsAdapter
import com.example.tibiatatics.ui.adapter.CharactersAdapter
import kotlinx.coroutines.launch


class CharacterDetailFragment : Fragment() {

    private lateinit var deathsAdapter: CharacterDeathsAdapter
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var characterAchievementsAdapter: CharacterAchievimentsAdapter
    private lateinit var recyclerView: RecyclerView
    private var newsModelWebClient = WebClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)

        deathRecycleView(view)
        charactersRecycleView(view)
        charactersAchievmentsRecycleView(view)


        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val nameCharacter = arguments?.getString("name").toString()

        val name = view.findViewById<TextView>(R.id.character_name)
        val title = view.findViewById<TextView>(R.id.character_title)
        val sex = view.findViewById<TextView>(R.id.character_sex)
        val vocation = view.findViewById<TextView>(R.id.character_vocation)
        val achievementsPoins = view.findViewById<TextView>(R.id.character_achievements_points)
        val world = view.findViewById<TextView>(R.id.character_world)
        val residence = view.findViewById<TextView>(R.id.character_residence)
        val lastLogin = view.findViewById<TextView>(R.id.character_last_login)
        val comment = view.findViewById<TextView>(R.id.character_comment)
        val guild = view.findViewById<TextView>(R.id.character_guild_membership)
        val level = view.findViewById<TextView>(R.id.character_level)

        lifecycleScope.launch {
            val characterInfo = nameCharacter.let { newsModelWebClient.searchCharacter(it) }
            characterInfo?.let {

                deathsAdapter.updateList(it.deaths)
                charactersAdapter.updateList(it.other_characters)
                characterAchievementsAdapter.updateList(it.achievements)

                name.text = it.character.name
                title.text = it.character.title
                sex.text = it.character.sex
                vocation.text = it.character.vocation
                achievementsPoins.text = it.character.achievement_points.toString()
                world.text = it.character.world
                residence.text = it.character.residence
                lastLogin.text = it.character.last_login
                comment.text = it.character.comment
                level.text = it.character.level.toString()

                guild.text = (if (it.character.guild.rank != null) {
                    "${it.character.guild.rank} of the ${it.character.guild.name}"
                } else {
                    ""
                }).toString()

                deathVisibility(view)
                achievementsVisibility(view)
                charactersVisibility(view)

            }
        }
    }

    private fun charactersVisibility(view: View) {
        val emptyCharactersView = view.findViewById<CardView>(R.id.card_view_all_characters)
        if (charactersAdapter.itemCount == 0) {
            emptyCharactersView.visibility = View.GONE
        } else {
            emptyCharactersView.visibility = View.VISIBLE
        }
    }

    private fun achievementsVisibility(view: View){
        val emptyAchievementsView = view.findViewById<CardView>(R.id.card_view_character_achievements)
        Log.i("###", "achievementsVisibility: ${characterAchievementsAdapter.itemCount}")
        if (characterAchievementsAdapter.itemCount == 0){
            emptyAchievementsView.visibility = View.GONE
        } else {
            emptyAchievementsView.visibility = View.VISIBLE
        }
    }

    private fun deathVisibility(view: View) {
        val emptyDeathView = view.findViewById<CardView>(R.id.card_view_character_death)
        if (deathsAdapter.itemCount == 0) {
            emptyDeathView.visibility = View.GONE
        } else {
            emptyDeathView.visibility = View.VISIBLE
        }
    }

    private fun deathRecycleView(view: View) {
        this.deathsAdapter = CharacterDeathsAdapter()
        recyclerView = view.findViewById(R.id.death_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.deathsAdapter
    }

    private fun charactersRecycleView(view: View) {
        this.charactersAdapter = CharactersAdapter()
        recyclerView = view.findViewById(R.id.character_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.charactersAdapter
    }

    private fun charactersAchievmentsRecycleView(view: View) {
        this.characterAchievementsAdapter = CharacterAchievimentsAdapter()
        recyclerView = view.findViewById(R.id.character_achievements_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.characterAchievementsAdapter
    }

}
