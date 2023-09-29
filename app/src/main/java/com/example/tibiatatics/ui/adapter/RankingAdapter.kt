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

    private var highscoreListModel: List<HighscoreListModel> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.ranking_character_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return highscoreListModel.size
    }

    fun updateList(highscoreListModels: List<HighscoreListModel>) {
        this.highscoreListModel = highscoreListModels
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(highscoreListModel[position])
            }
        }
    }

    class CharacterViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCategoryName = itemView.findViewById<TextView>(R.id.tv_category_type_name)
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

            fun formatNumberWithMask(input: String): String {

                val cleanedInput = input.replace(".", "")
                if (cleanedInput.isBlank() || !cleanedInput.all { it.isDigit() }) {
                    return input
                }
                val reversedInput = cleanedInput.reversed() // Inverte a string
                val formattedBuilder = StringBuilder()
                for (i in reversedInput.indices) {
                    formattedBuilder.append(reversedInput[i])
                    if ((i + 1) % 3 == 0 && i < reversedInput.length - 1) {
                        formattedBuilder.append(".")
                    }
                }
                return formattedBuilder.reverse().toString()
            }

            tvPositionRank.text = rankStatus.rank.toString()
            tvNameRank.text = rankStatus.name
            tvWorldRank.text = rankStatus.world
            tvLevelRank.text = rankStatus.level.toString()
            tvVocationRank.text = rankStatus.vocation
            tvExperienceRank.text = formatNumberWithMask(rankStatus.value.toString())


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

            when (rankStatus.category){
                "axefighting" -> tvCategoryName.text = "Axe Fighting"
                "clubfighting" -> tvCategoryName.text = "Club Fighting"
                "swordfighting" -> tvCategoryName.text = "Sword Fighting"
                "fistfighting" -> tvCategoryName.text = "Fist Fighting"
                "distancefighting" -> tvCategoryName.text = "Distance Fighting"
                "magiclevel" -> tvCategoryName.text = "Magic Level"
                "shielding" -> tvCategoryName.text = "Shielding"
                "fishing" -> tvCategoryName.text = "Fishing"
                "bosspoints" -> tvCategoryName.text = "Boss Points"
                "charmpoints" -> tvCategoryName.text = "Charm Points"
                "dromescore" -> tvCategoryName.text = "Drome Score"
                "experience" -> tvCategoryName.text = "Experience"
                "goshnarstaint" -> tvCategoryName.text = "Goshnar's Taint"
                "loyaltypoints" -> tvCategoryName.text = "Loyalty Points"
                "achievements" -> tvCategoryName.text = "Achievements"
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