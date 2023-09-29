package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.WebClient
import com.example.tibiatatics.ui.adapter.WorldDetailAdapter
import kotlinx.coroutines.launch


class WorldDetailFragment : Fragment() {

    private var nameWorld = ""
    private var newsModelWebClient = WebClient()
    private lateinit var recyclerView: RecyclerView
    private lateinit var worldsDetailAdapter: WorldDetailAdapter
    private lateinit var arrowImageView: ImageView
    private var isAscendingOrder = false
    private lateinit var clickableView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_world_detail, container, false)

        initRecycleView(view)
        dropMenuFilterVocation(view)

        orderView(view)

        val worldName = view.findViewById<TextView>(R.id.tv_world_name)
        val worldLocation = view.findViewById<TextView>(R.id.tv_world_location)
        val worldPlayers = view.findViewById<TextView>(R.id.tv_world_players_online)
        val worldTransfer = view.findViewById<TextView>(R.id.tv_world_transfer_type)

        val bundle = arguments
        if (bundle != null) {
            nameWorld = bundle.getString("world").toString()
            val playersOnline = bundle.getString("playersOnline")
            val location = bundle.getString("location")
            val transfer = bundle.getString("transfer")

            worldName.text = nameWorld
            worldLocation.text = location
            worldPlayers.text = playersOnline
            worldTransfer.text = transfer

        }

        lifecycleScope.launch {
            newsModelWebClient.getWorldDetail(nameWorld)?.let {
                worldsDetailAdapter.updateList(it)
            }
        }

        return view
    }

    private fun initRecycleView(view: View) {
        this.worldsDetailAdapter = WorldDetailAdapter()
        recyclerView = view.findViewById(R.id.rv_world_detail)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this.worldsDetailAdapter
    }

    private fun orderView(view: View) {
        clickableView = view.findViewById(R.id.clickable_view)
        arrowImageView = view.findViewById(R.id.arrowImageView)

        clickableView.setOnClickListener {

            isAscendingOrder = !isAscendingOrder
            updateArrowImage()
            updateView(isAscendingOrder)

        }
    }

    private fun dropMenuFilterVocation(view: View) {
        val actFilterVocation: AutoCompleteTextView =
            view.findViewById(R.id.actv_filter_vocations)
        val listFilterVocation =
            arrayOf(
                "All Vocation",
                "Master Sorcerer",
                "Elder Druid",
                "Elite Knight",
                "Royal Paladin"
            )

        val weaponModelAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listFilterVocation
        )
        actFilterVocation.setAdapter(weaponModelAdapter)

        actFilterVocation.setText("All Vocation", false)
        actFilterVocation.setOnClickListener {
            actFilterVocation.showDropDown()
        }

        actFilterVocation.setOnItemClickListener { _, _, position, _ ->
            val selectedVocation = listFilterVocation[position]
            lifecycleScope.launch {
                newsModelWebClient.getWorldDetail(nameWorld)?.let {
                    worldsDetailAdapter.updateList(it, isAscendingOrder, selectedVocation)
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
        worldsDetailAdapter.updateList(worldsDetailAdapter.worldDetail, ascending)
    }

}
