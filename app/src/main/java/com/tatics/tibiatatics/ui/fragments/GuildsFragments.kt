package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.GuildsAdapter
import com.tatics.tibiatatics.ui.viewmodel.GuildsViewModel
import kotlinx.coroutines.launch


class GuildsFragments : Fragment() {

    private val viewModel by viewModels<GuildsViewModel>()
    private var newsModelWebClient = WebClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var guildsAdapter: GuildsAdapter
    private var guildsWorlds = "Antica"
    private lateinit var actGuildsWorlds: AutoCompleteTextView
    private lateinit var btnSearch: AppCompatButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guilds_fragments, container, false)

        actGuildsWorlds = view.findViewById(R.id.input_guild_world)
        btnSearch = view.findViewById(R.id.btn_search_guild_world)

        initRecycleView(view)
        dropMenuGuildsWorld()
        btnOtherWorlds()
        updateGuilds()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.loadGuilds(guildsWorlds)
            viewModel.loadWorlds()
        }
    }

    private fun updateGuilds() {
        viewModel.guildsModelLiveData.observe(viewLifecycleOwner) { listGuildsModel ->
            if (listGuildsModel != null) {
                guildsAdapter.updateNewsList(listGuildsModel)
            }
        }
    }

    private fun dropMenuGuildsWorld() {
        actGuildsWorlds.setText("Antica", false)
        lifecycleScope.launch {
            viewModel.worldsModelLiveData.observe(viewLifecycleOwner) { worldsModel ->
                val listGuildsWorlds = mutableListOf<String>()
                listGuildsWorlds.add("Antica")
                worldsModel?.regular_worlds?.map { it.name }?.let { listGuildsWorlds.addAll(it) }

                val worldsModelAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    listGuildsWorlds
                )

                actGuildsWorlds.setOnItemClickListener { _, _, position, _ ->
                    guildsWorlds = listGuildsWorlds[position].lowercase()
                }

                actGuildsWorlds.setAdapter(worldsModelAdapter)
                actGuildsWorlds.setOnClickListener {
                    actGuildsWorlds.showDropDown()
                }
            }
        }
    }

    private fun btnOtherWorlds() {
        btnSearch.setOnClickListener {
            lifecycleScope.launch {
                viewModel.loadGuilds(guildsWorlds)
            }
        }
    }

    private fun initRecycleView(view: View) {
        this.guildsAdapter = GuildsAdapter {
            val guildName = it.name
            if (guildName.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("guilds", guildName)

                val navController = findNavController()
                val currentDestinationId = navController.currentDestination?.id
                val isOnMenuHome = currentDestinationId == R.id.guildsFragments
                if (isOnMenuHome) {
                    navController.navigate(
                        R.id.action_guildsFragments_to_guildsDetailFragment,
                        bundle
                    )
                } else {
                    navController.navigate(R.id.guildsFragments)
                }
            }
        }
        recyclerView = view.findViewById(R.id.guilds_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.guildsAdapter
    }
}
