package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tibiatatics.R
import com.example.tibiatatics.model.WorldDetailModel

class WorldDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var worldDetail: List<WorldDetailModel> = emptyList()
    private var selectedVocation: String = "All Vocation"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.worlds_detail_rv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return worldDetail.size
    }

    fun updateList(list: List<WorldDetailModel>, ascendingOrder: Boolean = false, vocationFilter: String = "All Vocation") {

        val filteredList = if (vocationFilter == "All Vocation") {
            list
        } else {
            list.filter { it.vocation == vocationFilter }
        }
        val sortedList = if (ascendingOrder) {
            filteredList.sortedBy { it.level }
        } else {
            filteredList.sortedByDescending { it.level }
        }
        this.worldDetail = sortedList
        selectedVocation = vocationFilter
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(worldDetail[position])
            }
        }
    }

    class CharacterViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val playerName = itemView.findViewById<TextView>(R.id.tv_player_name)
        val playerLevel = itemView.findViewById<TextView>(R.id.tv_player_level)
        val playerVocation = itemView.findViewById<TextView>(R.id.tv_player_vocation)
        val imgVocation = itemView.findViewById<ImageView>(R.id.img_outfit_worlds)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        fun bind(worldDetailModel: WorldDetailModel) {

            playerName.text = worldDetailModel.name
            playerLevel.text = worldDetailModel.level.toString()
            playerVocation.text = worldDetailModel.vocation

            when (worldDetailModel.vocation) {
                "Master Sorcerer" ->
                    Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load("https://www.tibiawiki.com.br/images/d/da/Outfit_Mage_Male_Addon_3.gif")
                        .into(imgVocation)

                "Elder Druid" ->
                    Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load("https://www.tibiawiki.com.br/images/archive/b/b4/20220707005000%21Outfit_Druid_Male_Addon_3.gif")
                        .into(imgVocation)

                "Elite Knight" ->
                    Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load("https://www.tibiawiki.com.br/images/b/b6/Outfit_Nightmare_Knights_Male_Addon_3.gif")
                        .into(imgVocation)

                else ->
                    Glide.with(itemView.context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load("https://www.tibiawiki.com.br/images/1/1a/Outfit_Ranger_Male_Addon_3.gif")
                        .into(imgVocation)
            }

            val colorResId = if (adapterPosition % 2 == 0) {
                R.color.brown
            } else {
                R.color.light_brown
            }
            itemView.setBackgroundColor(itemView.context.getColor(colorResId))
        }
    }
}