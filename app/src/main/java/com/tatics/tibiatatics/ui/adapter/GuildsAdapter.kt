package com.tatics.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.model.models.GuildsModel

class GuildsAdapter(
    private val onItemClicked: (GuildsModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var guildsModel: List<GuildsModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.guilds_rv, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NewsViewHolder -> {
                holder.bind(guildsModel[position], onItemClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return guildsModel.size
    }

    fun updateNewsList(list: List<GuildsModel>) {
        this.guildsModel = list
        notifyDataSetChanged()
    }

    class NewsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val guildName = itemView.findViewById<TextView>(R.id.tv_guild_name)
        val guildDescription = itemView.findViewById<TextView>(R.id.tv_guild_description)
        val guildLogo = itemView.findViewById<ImageView>(R.id.img_gif_guild)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)


        fun bind(guildsModel: GuildsModel, onItemClicked: (GuildsModel) -> Unit) {

            guildName.text = guildsModel.name
            guildDescription.text = guildsModel.description

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(guildsModel.logo_url)
                .into(guildLogo)

            val guildsBtn = itemView.findViewById<ConstraintLayout>(R.id.guilds)
            guildsBtn.setOnClickListener {
                onItemClicked(guildsModel)
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
