package com.tatics.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.model.models.AchievementsModels


class CharacterAchievimentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var achieviments: List<AchievementsModels> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DeathsViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.character_achievements_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return achieviments.size
    }

    fun updateList(achievement: List<AchievementsModels>){
        this.achieviments = achievement
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is DeathsViewHolder -> {
                holder.bind(achieviments[position])
            }
        }
    }

    class DeathsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameAchiements = itemView.findViewById<TextView>(R.id.tv_achieviment_name)
        val firstDifficult = itemView.findViewById<ImageView>(R.id.img_first_difficult)
        val secondDifficult = itemView.findViewById<ImageView>(R.id.img_second_difficult)
        val thirdDifficult = itemView.findViewById<ImageView>(R.id.img_third_difficult)
        val img_secret = itemView.findViewById<ImageView>(R.id.img_secret)

        fun bind(achievimentsModels: AchievementsModels) {

            nameAchiements.text = achievimentsModels.name

            when (achievimentsModels.grade){
                1 -> {
                    firstDifficult.setImageResource(R.drawable.achievement)
                }
                2 -> {
                    firstDifficult.setImageResource(R.drawable.achievement)
                    secondDifficult.setImageResource(R.drawable.achievement)
                }
                3 -> {
                    firstDifficult.setImageResource(R.drawable.achievement)
                    secondDifficult.setImageResource(R.drawable.achievement)
                    thirdDifficult.setImageResource(R.drawable.achievement)
                }
            }

            when (achievimentsModels.secret){
                true -> {
                    img_secret.visibility = View.VISIBLE
                }
                else -> {
                    img_secret.visibility = View.INVISIBLE
                }
            }

            val colorResId = if (position % 2 == 0) {
                R.color.brown
            } else {
                R.color.light_brown
            }
            itemView.setBackgroundColor(itemView.context.resources.getColor(colorResId))
        }
    }
}