package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.model.DeathsModel


class CharacterDeathsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var death: List<DeathsModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DeathsViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.character_deaths_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return death.size
    }

    fun updateList(deaths: List<DeathsModel>){
        this.death = deaths
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is DeathsViewHolder -> {
                holder.bind(death[position])
            }
        }
    }

    class DeathsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val deathTime = itemView.findViewById<TextView>(R.id.tv_death_time)
        val deathBy = itemView.findViewById<TextView>(R.id.tv_death_by)

        fun bind(deathsModel: DeathsModel) {

            val regex = Regex("(Died|Killed)", RegexOption.IGNORE_CASE)
            val reason = deathsModel.reason?.let { regex.find(it) }

            if (reason != null){
                val index = reason.range.first
                val reasonUntilRegex = deathsModel.reason.substring(0, index).trim()
                val reasonAfterRegex = deathsModel.reason.substring(index).trim()

                deathTime.text = reasonUntilRegex
                deathBy.text = reasonAfterRegex

                val colorResId = if (position % 2 == 0) {
                    R.color.brown
                } else {
                    R.color.light_brown
                }
                itemView.setBackgroundColor(itemView.context.resources.getColor(colorResId))


            }
        }
    }
}