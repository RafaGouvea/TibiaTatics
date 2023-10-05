package com.tatics.tibiatatics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tatics.tibiatatics.R
import com.tatics.tibiatatics.remote.WebClient
import kotlinx.coroutines.launch


class NewsDetailFragment : Fragment() {

    private var newsModelWebClient = WebClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        val id = arguments?.getString("id")

        val contentTxt = view.findViewById<TextView>(R.id.tv_news_detail_text_detail)
        val urlTxt = view.findViewById<TextView>(R.id.tv_news_detail_url)
        val titleTxt = view.findViewById<TextView>(R.id.tv_news_title)


        lifecycleScope.launch {
            if (id != null) {
                newsModelWebClient.loadNewsDetailFrom(id).let { newsModelDetail ->
                    urlTxt.text = newsModelDetail?.url
                    titleTxt.text = newsModelDetail?.title
                    contentTxt.text = newsModelDetail?.content
                }
            }
        }
        return view
    }

}
