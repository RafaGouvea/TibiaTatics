package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import com.tatics.tibiatatics.ui.adapter.GuildsDetailAdapter
import com.tatics.tibiatatics.ui.viewmodel.GuildsDetailViewModel
import kotlinx.coroutines.launch

class GuildsDetailFragment : Fragment() {

    private val viewModel by viewModels<GuildsDetailViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var guildsDetailAdapter: GuildsDetailAdapter
    private lateinit var arrowImageView: ImageView
    private var isAscendingOrder = false
    private lateinit var clickableView: View
    private var nameGuild = ""
    private lateinit var tvGuildName: TextView
    private lateinit var tvGuildDescription: TextView
    private lateinit var imgGif: ImageView
    private lateinit var actFilters: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guilds_detail, container, false)

        val bundle = arguments
        nameGuild = bundle?.getString("guilds").toString()

        viewById(view)
        initRecycleView(view)
        orderView(view)
        dropMenuFilters()
        loadGuild()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.loadGuildsDetail(nameGuild)
        }
    }

    private fun viewById(view: View) {
        tvGuildName = view.findViewById(R.id.tv_guild_name)
        tvGuildDescription = view.findViewById(R.id.tv_guild_description)
        imgGif = view.findViewById(R.id.img_gif_guild)
        actFilters = view.findViewById(R.id.filters_detail_guild)
    }

    private fun loadGuild() {
        viewModel.guildsDetailLiveData.observe(viewLifecycleOwner) { guildDetailModel ->

            guildDetailModel?.guild?.members?.let { guildsDetailAdapter.updateList(it) }

            lifecycleScope.launch {
                tvGuildName.text = guildDetailModel?.guild?.name
                tvGuildDescription.text = guildDetailModel?.guild?.description
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                view?.let { imgLoader ->
                    Glide.with(imgLoader.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(guildDetailModel?.guild?.logo_url)
                        .into(imgGif)
                }
            }
        }
    }

    private fun initRecycleView(view: View) {
        this.guildsDetailAdapter = GuildsDetailAdapter {
            val playerName = it.name
            if (playerName.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("name", playerName)

                val navController = findNavController()
                val currentDestinationId = navController.currentDestination?.id
                val isOnMenuHome = currentDestinationId == R.id.guildsDetailFragment
                if (isOnMenuHome) {
                    navController.navigate(
                        R.id.action_guildsDetailFragment_to_characterDetail,
                        bundle
                    )
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

    private fun dropMenuFilters() {
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
            viewModel.guildsDetailLiveData.observe(viewLifecycleOwner) { guildDetailModel ->
                when (selectedVocation) {
                    "No Filters" -> {
                        guildDetailModel?.guild?.members?.let { listMembersModel ->
                            guildsDetailAdapter.updateList(listMembersModel)
                        }
                    }

                    "Status" -> {
                        guildDetailModel?.guild?.members?.let { listMembersModel ->
                            guildsDetailAdapter.updateListStatus(listMembersModel)
                        }
                    }

                    else -> {
                        guildDetailModel?.guild?.members?.let { listMembersModel ->
                            guildsDetailAdapter.filteredList(
                                listMembersModel, isAscendingOrder, selectedVocation
                            )
                        }
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
