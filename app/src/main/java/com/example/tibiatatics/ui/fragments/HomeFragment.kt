package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.WebClient
import com.google.android.material.textfield.TextInputEditText
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        rashid(view)

        bostedCreatureName = view.findViewById(R.id.bosted_creature_name)
        imgCreature = view.findViewById(R.id.img_creature_bosted)
        bostedBossName = view.findViewById(R.id.tv_boss_name)
        imgBoss = view.findViewById(R.id.iv_boss)
        playersOnline = view.findViewById(R.id.players_online)

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

            newsModelWebClient.loadPlayersOnline().let { worlds ->
                playersOnline.text = worlds?.players_online.toString()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn_search = view.findViewById<AppCompatButton>(R.id.btn_search_home)
        val nameCharacterEditText = view.findViewById<TextInputEditText>(R.id.tv_search_character_home)

        btn_search.setOnClickListener {
            val nameCharacter = nameCharacterEditText.text.toString().trim()
            if (nameCharacter.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("name", nameCharacter)
                findNavController().navigate(R.id.action_menu_home_to_characterDetail, bundle)
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
