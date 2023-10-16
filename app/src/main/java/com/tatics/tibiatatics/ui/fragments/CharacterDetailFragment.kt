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

    private lateinit var name: TextView
    private lateinit var title: TextView
    private lateinit var sex: TextView
    private lateinit var vocation: TextView
    private lateinit var achievementsPoins: TextView
    private lateinit var world: TextView
    private lateinit var residence: TextView
    private lateinit var lastLogin: TextView
    private lateinit var comment: TextView
    private lateinit var guild: TextView
    private lateinit var level: TextView
    private lateinit var scrollView: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)

        name = view.findViewById(R.id.character_name)
        title = view.findViewById(R.id.character_title)
        sex = view.findViewById(R.id.character_sex)
        vocation = view.findViewById(R.id.character_vocation)
        achievementsPoins = view.findViewById(R.id.character_achievements_points)
        world = view.findViewById(R.id.character_world)
        residence = view.findViewById(R.id.character_residence)
        lastLogin = view.findViewById(R.id.character_last_login)
        comment = view.findViewById(R.id.character_comment)
        guild = view.findViewById(R.id.character_guild_membership)
        level = view.findViewById(R.id.character_level)
        scrollView = view.findViewById(R.id.sv_detail_character)

        deathRecycleView(view)
        charactersRecycleView(view)
        charactersAchievmentsRecycleView(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val nameCharacter = arguments?.getString("name").toString()

        world.setTextColor(view.context.resources.getColor(R.color.blue))

        lifecycleScope.launch {
            val characterInfo = nameCharacter.let { newsModelWebClient.searchCharacter(it) }
            characterInfo?.let { searchModel ->

                guildName = searchModel.character.guild.name.toString()

                deathsAdapter.updateList(searchModel.deaths)
                charactersAdapter.updateList(searchModel.other_characters)
                characterAchievementsAdapter.updateList(searchModel.achievements)

                name.text = searchModel.character.name
                title.text = searchModel.character.title
                sex.text = searchModel.character.sex
                vocation.text = searchModel.character.vocation
                achievementsPoins.text = searchModel.character.achievement_points.toString()
                world.text = searchModel.character.world
                residence.text = searchModel.character.residence
                lastLogin.text = searchModel.character.last_login
                comment.text = searchModel.character.comment
                level.text = searchModel.character.level.toString()


                val completeGuild = if (searchModel.character.guild.rank != null) {
                    val fullText =
                        "${searchModel.character.guild.rank} of <font color='#0029C1'>$guildName</font>"
                    HtmlCompat.fromHtml(fullText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                } else {
                    ""
                }
                guild.text = completeGuild

                deathVisibility(view)
                achievementsVisibility(view)
                charactersVisibility(view)
            }

            guild.setOnClickListener {
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

            world.setOnClickListener {
                val worldNameClicked = world.text.toString()
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

    private fun charactersVisibility(view: View) {
        val emptyCharactersView = view.findViewById<CardView>(R.id.card_view_all_characters)
        if (charactersAdapter.itemCount == 0) {
            emptyCharactersView.visibility = View.GONE
        } else {
            emptyCharactersView.visibility = View.VISIBLE
        }
    }

    private fun achievementsVisibility(view: View) {
        val emptyAchievementsView =
            view.findViewById<CardView>(R.id.card_view_character_achievements)
        Log.i("###", "achievementsVisibility: ${characterAchievementsAdapter.itemCount}")
        if (characterAchievementsAdapter.itemCount == 0) {
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
        this.charactersAdapter = CharactersAdapter {

            lifecycleScope.launch {
                scrollView.smoothScrollTo(0, 0)

                newsModelWebClient.searchCharacter(it.name.toString())?.let {

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
