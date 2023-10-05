package com.tatics.tibiatatics.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.model.models.MembersMod
import com.tatics.tibiatatics.model.models.WorldDetailModel

class GuildsDetailAdapter(private val onItemClicked: (MembersMod) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var guildDetailModel: List<MembersMod> = emptyList()
    private var selectedVocation: String = "No Filters"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return guildsDetailViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.guild_detail_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return guildDetailModel.size
    }

    fun updateList(list: List<MembersMod>) {
        this.guildDetailModel = list
        notifyDataSetChanged()
    }

    fun updateListStatus(list: List<MembersMod>) {
        this.guildDetailModel = list.filter { it.status == "online" }
        notifyDataSetChanged()
    }

    fun filteredList(
        list: List<MembersMod>,
        ascendingOrder: Boolean = false,
        vocationFilter: String = "No Filters"
    ) {

        val filteredList = if (vocationFilter == "No Filters") {
            list
        } else {
            list.filter { it.vocation == vocationFilter }
        }
        val sortedList = if (ascendingOrder) {
            filteredList.sortedBy { it.level }
        } else {
            filteredList.sortedByDescending { it.level }
        }
        this.guildDetailModel = sortedList
        selectedVocation = vocationFilter
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is guildsDetailViewHolder -> {
                holder.bind(guildDetailModel[position], onItemClicked)
            }
        }
    }

    class guildsDetailViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val guildPlayerName = itemView.findViewById<TextView>(R.id.tv_guild_player_name)
        val guildPlayerLevel = itemView.findViewById<TextView>(R.id.tv_guild_player_level)
        val guildPlayerVocation = itemView.findViewById<TextView>(R.id.tv_guild_player_vocation)
        val guildPlayerRank = itemView.findViewById<TextView>(R.id.tv_guild_player_rank)
        val guildPlayerTitle = itemView.findViewById<TextView>(R.id.tv_guild_player_title)
        val guildPlayerStatus = itemView.findViewById<TextView>(R.id.tv_guild_player_status)
        val guildPlayerJoined = itemView.findViewById<TextView>(R.id.tv_guild_player_joined)
        val imgVocation = itemView.findViewById<ImageView>(R.id.img_outfit_guild_player)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        fun bind(members: MembersMod, onItemClicked: (MembersMod) -> Unit) {

            guildPlayerName.setTextColor(itemView.context.resources.getColor(R.color.blue))

            guildPlayerName.text = members.name
            guildPlayerLevel.text = members.level.toString()
            guildPlayerVocation.text = members.vocation
            guildPlayerRank.text = members.rank
            guildPlayerTitle.text = members.title
            guildPlayerStatus.text = members.status
            guildPlayerJoined.text = members.joined

            when (members.vocation) {
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

            when (members.status) {
                "online" -> {
                    guildPlayerStatus.setTextColor(itemView.context.resources.getColor(R.color.green))
                }
                else -> {
                    guildPlayerStatus.setTextColor(itemView.context.resources.getColor(R.color.red))
                }
            }

            guildPlayerName.setOnClickListener {
                onItemClicked(members)
            }
        }
    }
}
