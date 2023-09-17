package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.WebClient
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class HomeFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bostedCreatureName = view.findViewById<TextView>(R.id.bosted_creature_name)
        val imgCreature = view.findViewById<ImageView>(R.id.img_creature_bosted)

        val bostedBossName = view.findViewById<TextView>(R.id.tv_boss_name)
        val imgBoss = view.findViewById<ImageView>(R.id.iv_boss)


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
        }
        return view
    }
}
