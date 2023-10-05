package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.GuildsDetailAdapter
import com.tatics.tibiatatics.ui.adapter.WorldsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorldsFragment : Fragment(), MenuProvider {

    private var newsModelWebClient = WebClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var worldsAdapter: WorldsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_worlds, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setAdapterWorlds(view)
        toolbar(view)

        lifecycleScope.launch {
            newsModelWebClient.loadWorlds()?.let {
                worldsAdapter.updateNewsList(it.regular_worlds)
            }
        }

        return view
    }

    private fun setAdapterWorlds(view: View) {
        this.worldsAdapter = WorldsAdapter {

            val nameWorld = it.name
            val playersOnline = it.players_online
            val location = it.location
            val transfer = it.transfer_type

            val bundle = Bundle()
            bundle.putString("world", nameWorld)
            bundle.putString("playersOnline", playersOnline.toString())
            bundle.putString("location", location)
            bundle.putString("transfer", transfer)

            val navController = findNavController()
            val currentDestinationId = navController.currentDestination?.id
            val isOnMenuHome = currentDestinationId == R.id.worldsFragment
            if (isOnMenuHome) {
                navController.navigate(R.id.action_worldsFragment_to_worldDetailFragment, bundle)
            } else {
                navController.navigate(R.id.worldsFragment)
            }
        }
        recyclerView = view.findViewById(R.id.worlds_list_recycleview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.worldsAdapter
    }

    private fun toolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_worlds)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            it.setOnClickListener { findNavController() }
            requireActivity().onBackPressed()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_worlds_filter, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        recyclerView.smoothScrollToPosition(0)
        return when (menuItem.itemId) {
            R.id.menu_server_br -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByLocationBr(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_server_eu -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByLocationEu(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_server_na -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByLocationNa(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_open_pvp -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByOpenPvp(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_optional_pvp -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByOptionalPvp(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_retro_hardcore_pvp -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByRetroHardcorePvp(it.regular_worlds)
                    }
                }
                true
            }

            R.id.menu_retro_open_pvp -> {
                CoroutineScope(Dispatchers.Main).launch {
                    val result = newsModelWebClient.loadWorlds()
                    result?.let {
                        worldsAdapter.filterByRetroOpenPvp(it.regular_worlds)
                    }
                }
                true
            }

            else -> true
        }
    }
}
