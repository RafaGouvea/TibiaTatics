package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.databinding.FragmentCharacterDetailBinding
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.CharacterAchievimentsAdapter
import com.tatics.tibiatatics.ui.adapter.CharacterDeathsAdapter
import com.tatics.tibiatatics.ui.adapter.CharactersAdapter
import kotlinx.coroutines.launch


class CharacterDetailFragment : Fragment() {

    private lateinit var deathsAdapter: CharacterDeathsAdapter
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var characterAchievementsAdapter: CharacterAchievimentsAdapter
    private lateinit var recyclerView: RecyclerView
    private var newsModelWebClient = WebClient()
    private var guildName = ""

    // Declare o objeto de ligação
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use o objeto de ligação para inflar o layout
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        deathRecycleView()
        charactersRecycleView()
        charactersAchievmentsRecycleView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val nameCharacter = arguments?.getString("name").toString()

        binding.characterWorld.setTextColor(view.context.resources.getColor(R.color.blue))

        lifecycleScope.launch {
            val characterInfo = nameCharacter.let { newsModelWebClient.searchCharacter(it) }
            characterInfo?.let { searchModel ->

                guildName = searchModel.character.guild.name.toString()

                deathsAdapter.updateList(searchModel.deaths)
                charactersAdapter.updateList(searchModel.other_characters)
                characterAchievementsAdapter.updateList(searchModel.achievements)

                with(binding) {
                    characterName.text = searchModel.character.name
                    characterTitle.text = searchModel.character.title
                    characterSex.text = searchModel.character.sex
                    characterVocation.text = searchModel.character.vocation
                    characterAchievementsPoints.text = searchModel.character.achievement_points.toString()
                    characterWorld.text = searchModel.character.world
                    characterResidence.text = searchModel.character.residence
                    characterLastLogin.text = searchModel.character.last_login
                    characterComment.text = searchModel.character.comment
                    characterLevel.text = searchModel.character.level.toString()
                }

                val completeGuild = if (searchModel.character.guild.rank != null) {
                    val fullText =
                        "${searchModel.character.guild.rank} of <font color='#0029C1'>$guildName</font>"
                    HtmlCompat.fromHtml(fullText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                } else {
                    ""
                }
                binding.characterGuildMembership.text = completeGuild

                updateVisibility()
            }

            binding.characterGuildMembership.setOnClickListener {
                val guildNameClicked = guildName
                if (guildNameClicked.isNotEmpty()) {
                    val bundle = Bundle()
                    bundle.putString("guilds", guildNameClicked)


                    val navController = findNavController()
                    val currentDestinationId = navController.currentDestination?.id
                    val isOnMenuHome = currentDestinationId == R.id.characterDetail
                    if (isOnMenuHome) {
                        navController.navigate(
                            R.id.action_characterDetail_to_guildsDetailFragment,
                            bundle
                        )
                    } else {
                        navController.navigate(R.id.characterDetail)
                    }
                }
            }

            binding.characterWorld.setOnClickListener {
                val worldNameClicked = binding.characterWorld.text.toString()
                val bundle = Bundle()
                bundle.putString("world", worldNameClicked)


                val navController = findNavController()
                val currentDestinationId = navController.currentDestination?.id
                val isOnMenuHome = currentDestinationId == R.id.characterDetail
                if (isOnMenuHome) {
                    navController.navigate(
                        R.id.action_characterDetail_to_worldDetailFragment,
                        bundle
                    )
                } else {
                    navController.navigate(R.id.characterDetail)
                }
            }
        }
    }

    private fun charactersVisibility() {
        val emptyCharactersView = binding.root.findViewById<CardView>(R.id.card_view_all_characters)
        emptyCharactersView.visibility =
            if (charactersAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    private fun achievementsVisibility() {
        val emptyAchievementsView =
            binding.root.findViewById<CardView>(R.id.card_view_character_achievements)
        emptyAchievementsView.visibility =
            if (charactersAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    private fun deathVisibility() {
        val emptyDeathView = binding.root.findViewById<CardView>(R.id.card_view_character_death)
        emptyDeathView.visibility =
            if (charactersAdapter.itemCount == 0) View.GONE else View.VISIBLE
    }

    private fun updateVisibility() {
        achievementsVisibility()
        charactersVisibility()
        deathVisibility()
    }

    private fun deathRecycleView() {
        this.deathsAdapter = CharacterDeathsAdapter()
        recyclerView = binding.root.findViewById(R.id.death_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.deathsAdapter
    }

    private fun charactersRecycleView() {
        this.charactersAdapter = CharactersAdapter {
            lifecycleScope.launch {
                binding.svDetailCharacter.smoothScrollTo(0, 0)

                newsModelWebClient.searchCharacter(it.name.toString())?.let {

                    deathsAdapter.updateList(it.deaths)
                    charactersAdapter.updateList(it.other_characters)
                    characterAchievementsAdapter.updateList(it.achievements)

                    with(binding) {
                        characterName.text = it.character.name
                        characterTitle.text = it.character.title
                        characterSex.text = it.character.sex
                        characterVocation.text = it.character.vocation
                        characterAchievementsPoints.text =
                            it.character.achievement_points.toString()
                        characterWorld.text = it.character.world
                        characterResidence.text = it.character.residence
                        characterLastLogin.text = it.character.last_login
                        characterComment.text = it.character.comment
                        characterLevel.text = it.character.level.toString()
                    }

                    binding.characterGuildMembership.text = (if (it.character.guild.rank != null) {
                        "${it.character.guild.rank} of the ${it.character.guild.name}"
                    } else {
                        ""
                    }).toString()

                    updateVisibility()
                }
            }
        }
        recyclerView = binding.root.findViewById(R.id.character_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.charactersAdapter
    }

    private fun charactersAchievmentsRecycleView() {
        this.characterAchievementsAdapter = CharacterAchievimentsAdapter()
        recyclerView = binding.root.findViewById(R.id.character_achievements_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.characterAchievementsAdapter
    }
}
