package com.example.tibiatatics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        when (holder){
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

class NewsViewHolder constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title = itemView.findViewById<TextView>(R.id.tv_news_home_title)
    val text = itemView.findViewById<TextView>(R.id.tv_news_home_text)

    fun bind(news: NewsModel){

        title.text = news.news
        text.text = news.url

    }
}

