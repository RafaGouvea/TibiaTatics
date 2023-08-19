package com.example.tibiatatics.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.tibiatatics.R
import com.example.tibiatatics.remote.NewsModelWebClient
import kotlinx.coroutines.launch


class NewsDetailFragment : Fragment() {

    private var newsModelWebClient = NewsModelWebClient()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        lifecycleScope.launch {
            val loadNewsDetailFrom = newsModelWebClient.loadNewsDetailFrom()
            Log.i("###", "onCreateView: $loadNewsDetailFrom ")
        }

        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

}
