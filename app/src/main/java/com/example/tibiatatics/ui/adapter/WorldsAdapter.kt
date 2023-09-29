package com.example.tibiatatics.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.model.InbuementModel
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.RegularWorldsModel
import com.example.tibiatatics.model.WorldsModel

class WorldsAdapter(
    private val onItemClicked: (RegularWorldsModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var worldsModel: List<RegularWorldsModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.worlds_rv, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NewsViewHolder -> {
                holder.bind(worldsModel[position], onItemClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return worldsModel.size
    }

    fun updateNewsList(list: List<RegularWorldsModel>) {
        this.worldsModel = list
        notifyDataSetChanged()
    }

    fun filterByLocationBr(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.location == "South America"
        }
        notifyDataSetChanged()
    }

    fun filterByLocationEu(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.location == "Europe"
        }
        notifyDataSetChanged()
    }

    fun filterByLocationNa(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.location == "North America"
        }
        notifyDataSetChanged()
    }

    fun filterByOpenPvp(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.pvp_type == "Open PvP"
        }
        notifyDataSetChanged()
    }

    fun filterByOptionalPvp(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.pvp_type == "Optional PvP"
        }
        notifyDataSetChanged()
    }

    fun filterByRetroOpenPvp(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.pvp_type == "Retro Open PvP"
        }
        notifyDataSetChanged()
    }

    fun filterByRetroHardcorePvp(list: List<RegularWorldsModel>) {
        this.worldsModel = list.filter {
            it.pvp_type == "Retro Hardcore PvP"
        }
        notifyDataSetChanged()
    }

    class NewsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvWorldName = itemView.findViewById<TextView>(R.id.tv_world_name)
        val tvPlayersOnline = itemView.findViewById<TextView>(R.id.tv_world_players_online)
        val tvPvpType = itemView.findViewById<TextView>(R.id.tv_world_pvp_type)
        val tvLocation = itemView.findViewById<TextView>(R.id.tv_world_location)
        val tvTransfer = itemView.findViewById<TextView>(R.id.tv_world_transfer_type)


        fun bind(worldsModel: RegularWorldsModel, onItemClicked: (RegularWorldsModel) -> Unit) {

            tvWorldName.text = worldsModel.name
            tvPlayersOnline.text = worldsModel.players_online.toString()
            tvPvpType.text = worldsModel.pvp_type
            tvLocation.text = worldsModel.location
            tvTransfer.text = worldsModel.transfer_type

            val btnReadMore = itemView.findViewById<CardView>(R.id.card_view_worlds)
            btnReadMore.setOnClickListener {
                onItemClicked(worldsModel)
            }
        }
    }
}