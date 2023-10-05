package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.GuildsDetailAdapter
import kotlinx.coroutines.launch

class GuildsDetailFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var guildsDetailAdapter: GuildsDetailAdapter
    private lateinit var arrowImageView: ImageView
    private var isAscendingOrder = false
    private lateinit var clickableView: View
    private var nameGuild = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guilds_detail, container, false)

        initRecycleView(view)
        orderView(view)
        dropMenuFilters(view)

        val tvGuildName =  view.findViewById<TextView>(R.id.tv_guild_name)
        val tvGuildDescription = view.findViewById<TextView>(R.id.tv_guild_description)
        val imgGif = view.findViewById<ImageView>(R.id.img_gif_guild)

        val bundle = arguments
        nameGuild = bundle?.getString("guilds").toString()
        Log.i("###", "onCreateView: $bundle")

        lifecycleScope.launch {
            newsModelWebClient.getGuildsDetail(nameGuild)?.guild?.members?.let {
                guildsDetailAdapter.updateList(it)
                Log.i("###", "onCreateView Lifecycle: $bundle")
            }

            newsModelWebClient.getGuildsDetail(nameGuild)?.guild?.let {
                tvGuildName.text = it.name
                tvGuildDescription.text = it.description
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(view.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(it.logo_url)
                    .into(imgGif)
            }

        }

        return view
    }

    private fun initRecycleView(view: View) {
        this.guildsDetailAdapter = GuildsDetailAdapter{

            val playerName = it.name
            if (playerName.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("name", playerName)

                val navController = findNavController()
                val currentDestinationId = navController.currentDestination?.id
                val isOnMenuHome = currentDestinationId == R.id.guildsDetailFragment
                if (isOnMenuHome) {
                    navController.navigate(R.id.action_guildsDetailFragment_to_characterDetail, bundle)
                } else {
                    navController.navigate(R.id.guildsFragments)
                }
            }

        }
        recyclerView = view.findViewById(R.id.activity_list_members_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.guildsDetailAdapter
    }

    private fun orderView(view: View) {
        clickableView = view.findViewById(R.id.clickable_view_guild_detail)
        arrowImageView = view.findViewById(R.id.arrowImageView)

        clickableView.setOnClickListener {

            isAscendingOrder = !isAscendingOrder
            updateArrowImage()
            updateView(isAscendingOrder)

        }
    }

    private fun dropMenuFilters(view: View) {
        val actFilters: AutoCompleteTextView =
            view.findViewById(R.id.filters_detail_guild)
        val listFilters =
            arrayOf(
                "No Filters",
                "Status",
                "Master Sorcerer",
                "Elder Druid",
                "Elite Knight",
                "Royal Paladin"
            )

        val filtersModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listFilters
        )
        actFilters.setAdapter(filtersModelAdapter)

        actFilters.setText("No Filters", false)
        actFilters.setOnClickListener {
            actFilters.showDropDown()
        }

        actFilters.setOnItemClickListener { _, _, position, _ ->
            val selectedVocation = listFilters[position]
            lifecycleScope.launch {
                newsModelWebClient.getGuildsDetail(nameGuild)?.guild?.members?.let {
                    if (selectedVocation == "No Filters") {
                        guildsDetailAdapter.updateList(it)
                    } else if (selectedVocation == "Status") {
                        guildsDetailAdapter.updateListStatus(it)
                    } else {
                        guildsDetailAdapter.filteredList(it, isAscendingOrder, selectedVocation)
                    }
                }
            }
        }
    }

    private fun updateArrowImage() {
        if (isAscendingOrder) {
            arrowImageView.setImageResource(R.drawable.arrow_down)
        } else {
            arrowImageView.setImageResource(R.drawable.arrow_up)
        }
    }

    private fun updateView(ascending: Boolean) {
        guildsDetailAdapter.filteredList(guildsDetailAdapter.guildDetailModel, ascending)
    }

}
