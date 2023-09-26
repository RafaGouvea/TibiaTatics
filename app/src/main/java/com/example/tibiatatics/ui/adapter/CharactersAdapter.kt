package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.model.OtherCharactersModel

class CharactersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var othersCharacters: List<OtherCharactersModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.all_characters_rv, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return othersCharacters.size
    }

    fun updateList(otherCharacter: List<OtherCharactersModel>){
        this.othersCharacters = otherCharacter
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(othersCharacters[position])
            }
        }
    }

    class CharacterViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val otherCharacterName = itemView.findViewById<TextView>(R.id.other_character_name)
        val otherCharacterWorld = itemView.findViewById<TextView>(R.id.other_character_world)
        val traded = itemView.findViewById<TextView>(R.id.tv_traded)


        fun bind(othersCharactersModel: OtherCharactersModel) {

            otherCharacterName.text = othersCharactersModel.name
            otherCharacterWorld.text = othersCharactersModel.world
            when (othersCharactersModel.traded){
                true -> {
                    traded.text = "Yes"
                    traded.setTextColor(itemView.context.resources.getColor(R.color.red))
                }
                else -> {
                    traded.text = "No"
                    traded.setTextColor(itemView.context.resources.getColor(R.color.green))
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