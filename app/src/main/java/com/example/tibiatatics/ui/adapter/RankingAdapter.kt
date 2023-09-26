package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tibiatatics.R
import com.example.tibiatatics.model.HighscoreListModel

class RankingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rankCharacter: List<HighscoreListModel> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.ranking_character_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return rankCharacter.size
    }

    fun updateList(rankModel: List<HighscoreListModel>) {
        this.rankCharacter = rankModel
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(rankCharacter[position])
            }
        }
    }

    class CharacterViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPositionRank = itemView.findViewById<TextView>(R.id.tv_position_rank)
        val tvNameRank = itemView.findViewById<TextView>(R.id.tv_name_rank)
        val tvWorldRank = itemView.findViewById<TextView>(R.id.tv_world_rank)
        val tvLevelRank = itemView.findViewById<TextView>(R.id.tv_level_rank)
        val tvVocationRank = itemView.findViewById<TextView>(R.id.tv_vocation_rank)
        val tvExperienceRank = itemView.findViewById<TextView>(R.id.tv_experience_rank)
        val imgVocation = itemView.findViewById<ImageView>(R.id.img_outfit_rank)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        fun bind(rankStatus: HighscoreListModel) {

            tvPositionRank.text = rankStatus.rank.toString()
            tvNameRank.text = rankStatus.name
            tvWorldRank.text = rankStatus.world
            tvLevelRank.text = rankStatus.level.toString()
            tvVocationRank.text = rankStatus.vocation
            tvExperienceRank.text = rankStatus.value.toString()


            when (rankStatus.vocation) {
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
        }
    }
}