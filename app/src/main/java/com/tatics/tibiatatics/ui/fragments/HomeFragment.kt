package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import kotlinx.coroutines.launch
import java.util.Calendar


class HomeFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    private val calendar = Calendar.getInstance()
    private val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    private lateinit var tvRashidCity: TextView
    private lateinit var rashid: ImageView
    private lateinit var bostedCreatureName: TextView
    private lateinit var imgCreature: ImageView
    private lateinit var bostedBossName: TextView
    private lateinit var imgBoss: ImageView
    private lateinit var playersOnline: TextView
    private lateinit var btnSearch: AppCompatButton
    private lateinit var nameCharacterEditText: TextInputEditText
    private lateinit var btnToRank: ImageView
    private lateinit var btnToWorlds: ImageView
    private lateinit var btnToGuilds: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewById(view)
        rashid(view)
        loadItems()

        return view
    }

    private fun viewById(view: View) {
        btnToGuilds = view.findViewById(R.id.img_guilds)
        btnToWorlds = view.findViewById(R.id.img_worlds)
        btnToRank = view.findViewById(R.id.img_highscore)
        nameCharacterEditText = view.findViewById(R.id.tv_search_character_home)
        btnSearch = view.findViewById(R.id.btn_search_home)
        bostedCreatureName = view.findViewById(R.id.bosted_creature_name)
        imgCreature = view.findViewById(R.id.img_creature_bosted)
        bostedBossName = view.findViewById(R.id.tv_boss_name)
        imgBoss = view.findViewById(R.id.iv_boss)
        playersOnline = view.findViewById(R.id.players_online)
    }

    private fun loadItems() {
        lifecycleScope.launch {
            newsModelWebClient.loadBostedCreature().let { creature ->
                bostedCreatureName.text = creature?.name

                creature?.image_url?.let { imageUrl ->
                    Glide.with(requireContext())
                        .load(imageUrl)
                        .into(imgCreature)
                }
            }
            newsModelWebClient.loadBostedBoss().let { boss ->
                bostedBossName.text = boss?.name_boss

                boss?.image_url_boss?.let { imageUrl ->
                    Glide.with(requireContext())
                        .load(imageUrl)
                        .into(imgBoss)
                }
            }

            newsModelWebClient.loadWorlds().let { worlds ->
                playersOnline.text = worlds?.players_online.toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnSearch.setOnClickListener {
            val nameCharacter = nameCharacterEditText.text.toString().trim()
            if (nameCharacter.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("name", nameCharacter)

                val navController = findNavController()
                val currentDestinationId = navController.currentDestination?.id
                val isOnMenuHome = currentDestinationId == R.id.menu_home
                if (isOnMenuHome) {
                    navController.navigate(R.id.action_menu_home_to_characterDetail, bundle)
                } else {
                    navController.navigate(R.id.menu_home)
                }
            }
        }

        btnToRank.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_home
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_home_to_highscoreFragment)
            } else {
                navController.navigate(R.id.menu_home)
            }
        }

        btnToWorlds.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_home
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_home_to_worldsFragment)
            } else {
                navController.navigate(R.id.menu_home)
            }
        }

        btnToGuilds.setOnClickListener {
            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.menu_home
            if (isOnMenuHome) {
                navController.navigate(R.id.action_menu_home_to_guildsFragments)
            } else {
                navController.navigate(R.id.menu_home)
            }
        }

    }

    private fun rashid(view: View) {
        rashid = view.findViewById(R.id.rashid)
        Glide.with(requireContext())
            .load("https://www.tibiawiki.com.br/images/f/f5/Rashid.gif")
            .into(rashid)

        tvRashidCity = view.findViewById(R.id.rashid_city)
        when (dayOfWeek) {
            Calendar.SUNDAY -> tvRashidCity.text = "Carlin"
            Calendar.MONDAY -> tvRashidCity.text = "Svarground"
            Calendar.TUESDAY -> tvRashidCity.text = "Libery Bay"
            Calendar.WEDNESDAY -> tvRashidCity.text = "Port Hope"
            Calendar.THURSDAY -> tvRashidCity.text = "Ankrahmun"
            Calendar.FRIDAY -> tvRashidCity.text = "Darashia"
            Calendar.SATURDAY -> tvRashidCity.text = "Edron"
        }
    }
}
