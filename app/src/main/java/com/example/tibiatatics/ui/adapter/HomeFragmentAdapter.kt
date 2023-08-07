package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tibiatatics.R
import com.example.tibiatatics.model.NewsModel

class HomeFragmentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var news: List<NewsModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_rv, parent, false)
        return NewsViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NewsViewHolder -> {
                holder.bind(news[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun updateNewsList(list: List<NewsModel>) {
        this.news = list
        notifyDataSetChanged()
    }
}

class NewsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val txt_title = itemView.findViewById<TextView>(R.id.tv_news_title)
    val txt_detail = itemView.findViewById<TextView>(R.id.tv_news_text_detail)
    val txt_date = itemView.findViewById<TextView>(R.id.tv_news_date)
    val url = itemView.findViewById<TextView>(R.id.tv_news_url)
    val imagemDetail = itemView.findViewById<ImageView>(R.id.img_news_detail)

    fun bind(news: NewsModel) {

        txt_title.text = news.title
        txt_date.text = news.date
        url.text = news.url

        when (txt_title.text) {
            "Fixes and Changes" -> txt_detail.text =
                "With today's server save, we have implemented a few changes and fixes:"

            "Rapid Respawn Weekend" -> txt_detail.text =
                "Sharpen your swords and ready your spells, for hordes of monsters are incoming! Another Rapid Respawn Weekend will soon be upon us!"

            "Game World Merge Announcement" -> txt_detail.text =
                "There are quite a few game world mergers ahead, Tibians!"

            "Double XP and Double Skill Weekend" -> txt_detail.text =
                "Are you looking for an opportunity to level up and improve your skills?"

            "Double XP/Skill and Double Loot Weekend" -> txt_detail.text =
                "Are you looking for an opportunity to level up, improve your skills and gain more loot?"

            "New Retro Hardcore PvP Game Worlds" -> txt_detail.text =
                "Fellow Tibians, new game worlds are about to be launched!"

            else -> txt_detail.text =
                "Check out the latest updates and stay informed on everything."
        }

        val drawableResourceId = when (txt_title.text) {
            "Fixes and Changes" -> R.drawable.news_fixes_243x200
            "Rapid Respawn Weekend" -> R.drawable.rapidrespawn_small
            "Game World Merge Announcement" -> R.drawable.mergegameworldsb
            "Double XP and Double Skill Weekend" -> R.drawable.doublexpnskill_small
            "Double XP/Skill and Double Loot Weekend" -> R.drawable.doublexpnskill_small
            "New Retro Hardcore PvP Game Worlds" -> R.drawable.battleeyeworlds_centered
            else -> R.drawable.warriornewsreader_img_news
        }
        imagemDetail.setImageResource(drawableResourceId)
    }
}



